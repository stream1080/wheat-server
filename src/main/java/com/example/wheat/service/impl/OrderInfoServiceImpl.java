package com.example.wheat.service.impl;

import com.example.wheat.entity.*;
import com.example.wheat.enums.OrderStatusEnum;
import com.example.wheat.enums.PaymentTypeEnum;
import com.example.wheat.enums.ProductStatusEnum;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.mapper.OrderInfoMapper;
import com.example.wheat.mapper.OrderItemMapper;
import com.example.wheat.mapper.ProductMapper;
import com.example.wheat.mapper.ShippingMapper;
import com.example.wheat.service.CartService;
import com.example.wheat.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.service.ProductService;
import com.example.wheat.service.ShippingService;
import com.example.wheat.vo.OrderItemVo;
import com.example.wheat.vo.OrderVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public ResponseVo<OrderVo> create(Integer uid, Integer shippingId) {

        //收货地址校验(总之要查出来的)
        Shipping shipping = shippingMapper.selectByUidAndShippingId(uid,shippingId);

        if (shipping == null) {
            return ResponseVo.error(ResponseEnum.SHIPPING_NOT_EXIST);
        }
        //获取购物车，校验(是否有商品库存)
        List<Cart> cartList = cartService.listForCart(uid).stream()
                .filter(Cart::getSelected)
                .collect (Collectors.toList());
        if (CollectionUtils.isEmpty(cartList)) {
            return ResponseVo.error(ResponseEnum.CART_SELECTED_IS_EMPTY);
        }

        //获取cartList里的productIds
        Set<Integer> productIdSet = cartList.stream()
                .map(Cart::getId)
                .collect(Collectors.toSet());
        List<Product> productList = productMapper.selectByProductIdSet (productIdSet);
        Map<Integer,Product> map = productList.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        List<OrderItem> orderItemList = new ArrayList<>();
        Long orderNo = generateOrderNo(); //生成订单Id
        for (Cart cart : cartList) {
            //根据productId查数据库
            Product product = map.get(cart.getId());
            if (product == null) {
                return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST,
                        "商品不存在。productId = " + cart.getId());
            }
            //校验商品上下架状态
            if (!ProductStatusEnum.ON_SALE.getCode().equals(product.getStatus())) {
                return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE,
                        "商品不是在售状态" + product.getName());
            }

            //库存是否充足
            if (product.getStock() < cart.getQuantity()) {
                return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR,
                        "库存不正确" + product.getName());
            }

            OrderItem orderItem = buildOrderItem(uid, orderNo, cart.getQuantity(), product);
            orderItemList.add(orderItem);

            //减库存
            product.setStock(product.getStock() - cart.getQuantity());
            int row = productMapper.updateById(product) ;
            if(row<=0){
                return ResponseVo.error(ResponseEnum.ERROR);
            }
        }

        OrderInfo orderInfo = build0rder(uid, orderNo, shippingId, orderItemList);

        int rowFor0rder = orderInfoMapper.insert(orderInfo);
        if ( rowFor0rder <= 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        //TODO 需要优化  循环写入 05-10-5
        for ( OrderItem orderItem : orderItemList) {
            orderItemMapper.insert(orderItem) ;
        }

//        int rowForOrderItem = orderItemMapper.batchInsert(orderItemList);
//        if ( rowFor0rderItem <= 0) {
//            return ResponseVo.error(ResponseEnum.ERROR) ;
//        }

        //更新购物车(选中的商品)
        //Redis有事务(打包命令)，不能回滚
        for (Cart cart : cartList) {
            cartService.delete(uid,cart.getId());
        }

        //构造orderInfoVo;
        OrderVo orderVo = build0rderInfoVo(orderInfo,orderItemList , shipping);
        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage (pageNum,pageSize);
        List<OrderInfo> orderList = orderInfoMapper.selectByUid(uid);
        Set<Long> orderNoSet = orderList.stream()
                .map(OrderInfo::getOrderNo)
                .collect(Collectors.toSet());

        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet) ;
        Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream()
                . collect (Collectors.groupingBy(OrderItem::getOrderNo));
        Set<Integer> shippingIdSet = orderList . stream()
                .map(OrderInfo::getShippingId)
                .collect (Collectors. toSet());
        List<Shipping> shippingList = shippingMapper . selectByIdSet (shippingIdSet);
        Map<Integer, Shipping> shippingMap = shippingList. stream()
                .collect(Collectors.toMap(Shipping::getId,shipping -> shipping));

        List<OrderVo> orderVoList = new ArrayList<>( );
        for (OrderInfo order : orderList) {
            OrderVo orderVo = build0rderInfoVo(order,
                    orderItemMap.get(order.getOrderNo()),
                    shippingMap.get(order.getShippingId()));
            orderVoList.add(orderVo);
        }
        PageInfo pageInfo = new PageInfo(orderList);
        pageInfo.setList(orderVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<OrderVo> detail(Integer uid, Long orderNo) {
        OrderInfo order = orderInfoMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(uid)) {
            return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST) ;
        }
        Set <Long> orderNoSet = new HashSet<>( );
        orderNoSet. add(order.getOrderNo() );
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet);
                Shipping shipping = shippingMapper.selectById(order.getShippingId());
                OrderVo orderVo = build0rderInfoVo(order,orderItemList,shipping);
        return ResponseVo.success (orderVo);
    }

    @Override
    public ResponseVo<OrderVo> cancel(Integer uid, Long orderNo) {
        OrderInfo order = orderInfoMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(uid)){
            return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST);
        }
        //只有[未付款]订单可以取消，看具体公司业务
        if (!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            return ResponseVo.error(ResponseEnum.ORDER_STATUS_ERROR) ;
        }
        order. setStatus(OrderStatusEnum.CANCELED.getCode());
        order.setCloseTime(new Date());
        int row = orderInfoMapper.updateById(order);
        if(row <= 0){
            return ResponseVo.error(ResponseEnum.ERROR) ;
        }
        return ResponseVo.success();
    }

    private OrderVo build0rderInfoVo(OrderInfo orderInfo, List<OrderItem> orderItemList, Shipping shipping) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(orderInfo,orderVo);
        List<OrderItemVo> OrderItemVoList = orderItemList.stream().map(e -> {
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtils.copyProperties(e,orderItemVo) ;
            return orderItemVo;
        }).collect(Collectors.toList());
        orderVo.setOrderItemVoList(OrderItemVoList);
        if (shipping != null) {
            orderVo.setShippingId(shipping.getId());
            orderVo.setShippingVo(shipping);
        }
        return orderVo;
    }

    private OrderInfo build0rder(Integer uid,
                             Long orderNo,
                             Integer shippingId,
                             List<OrderItem> orderItemList
                            ){
        OrderInfo order = new OrderInfo();
        order.setOrderNo(orderNo) ;
        order.setUserId(uid);
        order.setShippingId(shippingId);
        BigDecimal payment = orderItemList.stream()
                .map(OrderItem::getTotalprice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        order.setPayment( payment);
        order.setPaymentType(PaymentTypeEnum.PAY_ONLINE.getCode());
        order.setPostage(0);
        order.setStatus (OrderStatusEnum.NO_PAY.getCode());
        return order;
    }


    /**
     * 目前简单处理，使用时间戳+随机数
     * 企业级：分布式唯一Id/主键
     * @return
     */
    private Long generateOrderNo() {
        return System.currentTimeMillis() + new Random().nextInt(999);
    }

    private OrderItem buildOrderItem(Integer uid, Long orderNo, Integer quantity,Product product){
        OrderItem item = new OrderItem();
        item.setUserId(uid);
        item.setOrderNo( orderNo) ;
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setProductImage(product.getMainImage());
        item.setCurrentUnitPrice(product.getPrice());
        item.setQuantity(quantity);
        item.setTotalprice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        return item;
    }

}

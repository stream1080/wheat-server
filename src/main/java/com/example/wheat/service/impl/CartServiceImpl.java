package com.example.wheat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.entity.Cart;
import com.example.wheat.entity.Product;
import com.example.wheat.entity.Shipping;
import com.example.wheat.enums.ProductStatusEnum;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.CartUptadtForm;
import com.example.wheat.mapper.ProductMapper;
import com.example.wheat.mapper.ShippingMapper;
import com.example.wheat.service.CartService;
import com.example.wheat.service.ShippingService;
import com.example.wheat.vo.CartProductVo;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;
import com.google.gson.Gson;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
public class CartServiceImpl implements CartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    private Integer quantity = 1;


    @Override
    public ResponseVo<CartVo> add(Integer uid ,CartAddForm cartAddForm) {
        Product product = productMapper.selectById(cartAddForm.getProductId());
        //商品是否存在
        if (product == null) {
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }
        //商品是否在售
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        //商品库存是否充足
        if (product.getStock() <= 0) {
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        //写入redis
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        String value = opsForHash.get(redisKey,String.valueOf(product.getId()));
        Cart cart;
        if (StringUtils.isEmpty(value)) {
            //没有就新增
            cart = new Cart(product.getId(),quantity,cartAddForm.getSelected());
        }else{
            //已有数量加一
            cart = gson.fromJson(value,Cart.class);
            cart.setQuantity(cart.getQuantity()+quantity);
        }
        opsForHash.put(redisKey, String.valueOf(product.getId()), gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        Map<String,String> entries = opsForHash.entries(redisKey);

        Boolean selectAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();

        for (Map.Entry<String,String> entry : entries.entrySet()){
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(),Cart.class);

            //TODO 需要优化，使用MySQL里面的in
            Product product = productMapper.selectById(productId);
            if (product != null) {
                CartProductVo cartProductVo = new CartProductVo(productId,
                        cart.getQuantity(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getMainImage(),
                        product.getPrice(),
                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        product.getStatus(),
                        product.getStock(),
                        cart.getSelected()
                );
                cartProductVoList.add(cartProductVo);

                cart.setSelected(true);

                if (!cart.getSelected()){
                    selectAll = false;
                }
                //计算总价，只计算选中的
                if (cart.getSelected()){
                    cartTotalPrice.add(cartProductVo.getTotalPrice());
                }
            }
            cartTotalQuantity += cart.getQuantity();
        }
        //有一个没有选中，就不叫全选
        cartVo.setSelectedAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);


        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUptadtForm cartUptadtForm) {
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        String value = opsForHash.get(redisKey,String.valueOf(productId));

        if (StringUtils.isEmpty(value)) {
            //没有该商品，报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_STOCK_ERROR);
        }
        //已有，修改内容
        Cart cart = gson.fromJson(value,Cart.class);
        if (cartUptadtForm.getQuantity() != null
                && cartUptadtForm.getQuantity() >= 0) {
            cart.setQuantity(cartUptadtForm.getQuantity());
        }
        if (cartUptadtForm != null) {
            cart.setSelected(cartUptadtForm.getSelected());
        }
        opsForHash.put(redisKey, String.valueOf(productId), gson.toJson(cart));
        return list(uid);
    }
}

package com.example.wheat.service.impl;

import com.example.wheat.entity.Product;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.mapper.ProductMapper;
import com.example.wheat.service.CategoryService;
import com.example.wheat.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ProductDetailVo;
import com.example.wheat.vo.ProductVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Pagination;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.wheat.enums.ProductStatusEnum.DELETE;
import static com.example.wheat.enums.ProductStatusEnum.OFF_SALE;
import static com.example.wheat.enums.ResponseEnum.PARAM_ERROR;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        if (categoryId != null) {
            categoryService.findSubCategoryId(categoryId,categoryIdSet);
            categoryIdSet.add(categoryId);
        }

        //分页查询
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e,productVo);
                    return  productVo;
                })
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productVoList);
        return ResponseVo.success(pageInfo);
    }


    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        String productJson = stringRedisTemplate.opsForValue().get(String.valueOf(productId));
        if(StringUtils.isEmpty(productJson)){
            ProductDetailVo productDetailVo = detailFromDb(productId);
            String s = gson.toJson(productDetailVo);
            stringRedisTemplate.opsForValue().set(String.valueOf(productId),s,5, TimeUnit.MINUTES);
            System.out.println("查数据库");
            return ResponseVo.success(productDetailVo);
        }

        System.out.println("查缓存");
        ProductDetailVo productDetailVo = gson.fromJson(productJson,ProductDetailVo.class);
        return ResponseVo.success(productDetailVo);
    }


    /**
     * 从数据库中查询商品详情
     * @param productId
     * @return
     */
    public ProductDetailVo detailFromDb(Integer productId) {
        Product product = productMapper.selectById(productId);

//        只对确定性的条件做判断
        if (product.getStatus().equals(OFF_SALE.getCode())
            || product.getStatus().equals(DELETE.getCode())){
            throw new RuntimeException("商品已下架或已删除");
//            return ResponseVo.error(PARAM_ERROR);
        }

        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo);

        //敏感数据处理,库存大于100.就显示100
        productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
        return productDetailVo;
    }
}

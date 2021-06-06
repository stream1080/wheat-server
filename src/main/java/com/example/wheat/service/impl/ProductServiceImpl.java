package com.example.wheat.service.impl;

import com.example.wheat.entity.Product;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.mapper.ProductMapper;
import com.example.wheat.service.CategoryService;
import com.example.wheat.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.vo.ProductDetailVo;
import com.example.wheat.vo.ProductVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.control.Pagination;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Product product = productMapper.selectById(productId);

        //只对确定性的条件做判断
        if (product.getStatus().equals(OFF_SALE.getCode())
            || product.getStatus().equals(DELETE.getCode())){
            return ResponseVo.error(PARAM_ERROR);
        }

        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo);

        //敏感数据处理,库存大于100.就显示100
        productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
        return ResponseVo.success(productDetailVo);
    }
}

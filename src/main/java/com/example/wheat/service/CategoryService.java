package com.example.wheat.service;

import com.example.wheat.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询商品三级分类
     * @return
     */
    ResponseVo<List<CategoryVo>> selectAll();

    /**
     * 查询子目录
     * @param id
     * @param resultSet
     */
    void findSubCategoryId(Integer id, Set<Integer> resultSet);
}

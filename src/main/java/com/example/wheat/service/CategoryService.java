package com.example.wheat.service;

import com.example.wheat.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ResponseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface CategoryService extends IService<Category> {

    ResponseVo<List<CategoryVo>> selectAll();
}

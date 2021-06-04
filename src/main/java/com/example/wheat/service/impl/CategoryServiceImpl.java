package com.example.wheat.service.impl;

import com.example.wheat.entity.Category;
import com.example.wheat.mapper.CategoryMapper;
import com.example.wheat.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

package com.example.wheat.service.impl;

import com.example.wheat.entity.Category;
import com.example.wheat.mapper.CategoryMapper;
import com.example.wheat.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ResponseVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.example.wheat.conts.WheatConst.ROOT_PARENT_ID;

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

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Gson gson = new Gson();



    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        String categoryJson = stringRedisTemplate.opsForValue().get("category");
        //如果缓存中没有，查数据库
        if (StringUtils.isEmpty(categoryJson)) {
            List<CategoryVo> categoryVoList = selectAllFromDb();
            System.out.println("查数据库");
            if (StringUtils.isEmpty(categoryVoList)) {
                //库中没有此数据，存入一个空值,过期时间为5分钟,解决缓存穿透问题
                stringRedisTemplate.opsForValue().set("category","",5, TimeUnit.MINUTES);
            }
            return ResponseVo.success(categoryVoList);
        }
        List<CategoryVo> categoryVoList = gson.fromJson(categoryJson,new TypeToken<List<CategoryVo>>(){}.getType());
        System.out.println("查缓存");
        return ResponseVo.success(categoryVoList);
    }


    public List<CategoryVo> selectAllFromDb() {
        //加本地锁，解决缓存击穿
        synchronized (this){
            //得到锁后，应该再去缓存中确定一次，没有才需要继续查询
            String categoryJson = stringRedisTemplate.opsForValue().get("category");
            if (!StringUtils.isEmpty(categoryJson)) {
//                如果缓存不为空，则之间返回
                List<CategoryVo> categoryVoList = gson.fromJson(categoryJson,new TypeToken<List<CategoryVo>>(){}.getType());
                return categoryVoList;
            }
            List<Category> categories = categoryMapper.selectList(null);
            List<CategoryVo> categoryVoList = new ArrayList<>();
            for(Category category : categories){
                if(category.getParentId().equals((ROOT_PARENT_ID))){
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category,categoryVo);
                    categoryVoList.add(categoryVo);
                }
            }

//        //lambda + stream 查询根目录
//        List<CategoryVo> categoryVoList = categories.stream()
//                .filter(e -> e.getParentId().equals(ROOT_PARENT_ID))
//                .map(e -> category2CategoryVo(e))
//                .collect(Collectors.toList());

            //查询子目录
            findSubCategory(categoryVoList,categories);

            //查到结果后将结果序列化，写入缓存，并设置一个随机的过期时间，解决缓存雪崩问题
            //生成5-15之间的一个随机数,设置缓存随机在5-15分钟内过期
            Random random = new Random();
            int randomNum = random.nextInt(10)+5;
            stringRedisTemplate.opsForValue().set("category",gson.toJson(categoryVoList),randomNum,TimeUnit.MINUTES);

            return categoryVoList;
        }
    }


    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        List<Category> categories = categoryMapper.selectList(null);
        findSubCategoryId(id,resultSet,categories);
    }
    private void findSubCategoryId(Integer id, Set<Integer> resultSet,List<Category> categories) {
        for (Category category : categories){
            if (category.getParentId().equals(id)) {
                resultSet.add(category.getId());
                findSubCategoryId(category.getId(), resultSet, categories);
            }
        }
    }

    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories) {
        for (CategoryVo categoryVo : categoryVoList){
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for (Category category : categories){
                //如果查到内容，设置subCategory，继续往下查；
                if (categoryVo.getId().equals(category.getParentId())){
                   CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoryVoList.add(subCategoryVo);
                }

                //排序，数值越大，优先级越高
                subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                categoryVo.setSubCategories(subCategoryVoList);

                findSubCategory(subCategoryVoList,categories);
            }
        }
    }

    private CategoryVo category2CategoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }

}

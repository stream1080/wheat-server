package com.example.wheat.service.impl;

import com.example.wheat.entity.Shipping;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.form.ShippingForm;
import com.example.wheat.mapper.ShippingMapper;
import com.example.wheat.service.ShippingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class ShippingServiceImpl extends ServiceImpl<ShippingMapper, Shipping> implements ShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties (form, shipping);
        shipping.setUserId(uid);
        int row = shippingMapper.insert(shipping);
        if(row==0){
            return ResponseVo. error(ResponseEnum.ERROR);
        }
        Map<String,Integer> map = new HashMap<>( );
        map.put ("shippingId", shipping.getId());
        return ResponseVo. success (map);
    }

    @Override
    public ResponseVo delete(Integer uid, ShippingForm form) {
        return null;
    }

    @Override
    public ResponseVo update(Integer uid, Integer ShippingId, ShippingForm form) {
        return null;
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        return null;
    }
}

package com.example.wheat.service;

import com.example.wheat.entity.Shipping;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.form.ShippingForm;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface ShippingService extends IService<Shipping> {

    /**
     * 添加收货地址
     * @param uid
     * @param form
     * @return
     */
    ResponseVo<Map<String,Integer>> add(Integer uid, ShippingForm form);

    /**
     * 删除收货地址
     * @param uid
     * @param form
     * @return
     */
    ResponseVo delete(Integer uid, ShippingForm form);

    /**
     * 更新收货地址
     * @param uid
     * @param ShippingId
     * @param form
     * @return
     */
    ResponseVo update(Integer uid, Integer ShippingId , ShippingForm form);

    /**
     * 查询收货地址
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer uid,Integer pageNum,Integer pageSize);

}

package com.example.wheat.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderCreateForm {

    @ApiModelProperty(value = "收货地址表id")
    private Integer shippingId;

    @ApiModelProperty(value = "实际付款金额,单位是元")
    private BigDecimal payment;

    @ApiModelProperty(value = "支付类型,1-在线支付")
    private Integer paymentType;

    @ApiModelProperty(value = "运费,单位是元")
    private Integer postage;

    @ApiModelProperty(value = "订单状态:0-已取消,10-未付款")
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;

}

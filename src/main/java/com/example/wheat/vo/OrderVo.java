package com.example.wheat.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.wheat.entity.OrderItem;
import com.example.wheat.entity.Shipping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVo {

    private Integer id;

    private Long orderNo;

    private BigDecimal payment;

    private Integer paymentType;

    @ApiModelProperty(value = "运费,单位是元")
    private Integer postage;

    @ApiModelProperty(value = "订单状态:0-已取消,10-未付款")
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;

    @ApiModelProperty(value = "发货时间")
    private Date sendTime;

    @ApiModelProperty(value = "交易完成时间")
    private Date endTime;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closeTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private List<OrderItemVo> orderItemVoList;

    private Integer shippingId;

    private Shipping shippingVo;

}

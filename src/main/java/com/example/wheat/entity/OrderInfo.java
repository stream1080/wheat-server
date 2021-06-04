package com.example.wheat.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderInfo对象", description="")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private Long orderNo;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

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

    @ApiModelProperty(value = "发货时间")
    private Date sendTime;

    @ApiModelProperty(value = "交易完成时间")
    private Date endTime;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closeTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}

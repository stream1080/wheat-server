package com.example.wheat.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItemVo {

    @ApiModelProperty(value = "订单id")
    private Long orderNo;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品图片地址")
    private String productImage;

    @ApiModelProperty(value = "生成订单的价格")
    private BigDecimal currentUnitPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer quantity;

    @ApiModelProperty(value = "商品总价,单位是元")
    private BigDecimal totalprice;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

package com.example.wheat.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CartProductVo {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "购买数量")
    private Integer quantity; //购买数量

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品副标题")
    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private BigDecimal totalPrice; //总价=quantity * price

    private Integer status;

    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    @ApiModelProperty(value = "商品是否选中")
    private Boolean selected;
}

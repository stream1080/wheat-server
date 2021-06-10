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

    private Integer id;

    private Integer quantity; //购买数量

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private BigDecimal totalPrice; //总价=quantity * price

    private Integer status;

    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    @ApiModelProperty(value = "商品是否选中")
    private Boolean selected;

    public CartProductVo(Integer id, Integer quantity, String name, String subtitle, String mainImage, BigDecimal price, BigDecimal totalPrice, Integer status, Integer stock, Boolean selected) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.price = price;
        this.totalPrice = totalPrice;
        this.status = status;
        this.stock = stock;
        this.selected = selected;
    }
}

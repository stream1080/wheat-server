package com.example.wheat.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cart {

    private Integer id;

    private Integer quantity; //购买数量

    private Boolean selected;

    public Cart() {
    }

    public Cart(Integer id, Integer quantity, Boolean selected) {
        this.id = id;
        this.quantity = quantity;
        this.selected = selected;
    }
}

package com.example.wheat.entity;

import lombok.Data;


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

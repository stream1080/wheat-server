package com.example.wheat.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartAddForm {

    @NotNull
    private Integer productId;

    @NotNull
    private Boolean selected = true;

}

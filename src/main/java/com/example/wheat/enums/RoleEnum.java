package com.example.wheat.enums;

import lombok.Getter;

import javax.management.relation.Role;

@Getter
public enum RoleEnum {
    ADMIN(0),

    CUSTOMER(1),

    ;

    Integer code;
    RoleEnum (Integer code){
        this.code = code;
    }


}

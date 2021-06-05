package com.example.wheat.form;


import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserForm {

    //@NotEmple //判断空
    //@NotNull //判断null
    //@NotBlank //判断空格

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码, MD5加密")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "邮箱地址")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String phone;
}

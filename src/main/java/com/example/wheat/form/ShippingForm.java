package com.example.wheat.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ShippingForm {


    @NotBlank
    @ApiModelProperty(value = "收货姓名")
    private String receiverName;

    @NotBlank
    @ApiModelProperty(value = "收货固定电话")
    private String receiverPhone;

//    @NotBlank
    @ApiModelProperty(value = "收货移动电话")
    private String receiverMobile;

    @NotBlank
    @ApiModelProperty(value = "省份")
    private String receiverProvince;

    @NotBlank
    @ApiModelProperty(value = "城市")
    private String receiverCity;

    @NotBlank
    @ApiModelProperty(value = "区/县")
    private String receiverDistrict;

    @NotBlank
    @ApiModelProperty(value = "详细地址")
    private String receiverAddress;

    @NotBlank
    @ApiModelProperty(value = "邮编")
    private String receiverZip;

}

package com.example.wheat.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVo {

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分类id,对应category表的id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品副标题")
    private String subtitle;

    @ApiModelProperty(value = "产品主图，ur1相对地址")
    private String mainImage;

    @ApiModelProperty(value = "价格,单位元保留两位小数")
    private BigDecimal price;

    @ApiModelProperty(value = "商品状态：1-在售,2-下架,3-删除")
    private Integer status;

}

package com.example.wheat.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CategoryVo {
    @ApiModelProperty(value = "类别Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父类别id,0表示根节点")
    private Integer parentId;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty(value = "排序编号,同类展示顺序,数值由小到大")
    private Integer sortOrder;

    @ApiModelProperty(value = "子类目")
    private List<CategoryVo> subCategories;

}

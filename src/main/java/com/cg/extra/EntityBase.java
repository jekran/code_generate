//package com.cg.extra;
//
//import com.baomidou.mybatisplus.annotation.*;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
///**
// * @ClassName EntityBase
// * @Description 实体基类
// */
//@Getter
//@Setter
//public class EntityBase implements Serializable {
//
//    @ApiModelProperty(value = "序列")
//    @TableId(value = "id", type = IdType.ASSIGN_ID)
//    private String id;
//
//    @ApiModelProperty(value = "说明")
//    private String description;
//
//    @ApiModelProperty(value = "创建时间")
//    @TableField(fill = FieldFill.INSERT)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime createDateTime;
//
//    @ApiModelProperty(value = "创建人")
//    private String createName;
//
//    @ApiModelProperty(value = "修改时间")
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime modifyDateTime;
//
//    @ApiModelProperty(value = "修改人")
//    private String modifyName;
//
//    @ApiModelProperty(value = "删除状态（0：未删除，1：删除）")
//    @TableLogic
//    private Boolean isDelete;
//
//    @ApiModelProperty(value = "排序")
//    private Integer sorting;
//
//    @Version
//    @ApiModelProperty(hidden = true,value = "版本号")
//    private Integer version;
//
//}
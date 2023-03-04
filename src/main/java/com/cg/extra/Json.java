package com.cg.extra;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 返回页面json类
 *
 *
 */
@Data
@NoArgsConstructor
//@ApiModel(value = "返回实体类")
public class Json<T> {
    /**
     * 是否成功
     */
//    @ApiModelProperty(name = "success", value = "是否成功")
    private Boolean success = false;
    /**
     * 状态码
     */
//    @ApiModelProperty(name = "code", value = "状态码")
    private String code = "0";
    /**
     * 提示消息
     */
//    @ApiModelProperty(name = "msg", value = "提示消息")
    private String msg = "";
    /**
     * 返回内容
     */
//    @ApiModelProperty(name = "obj", value = "返回内容")
    private T obj = null;
    /**
     * 错误
     */
//    @ApiModelProperty(name = "errorMessage", value = "错误")
    private String errorMessage = "";
    /**
     * 时间
     */
//    @ApiModelProperty(name = "timestamp", value = "时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime timestamp = null;


    /**
     * 构造器，统一格式参数返回
     *
     * @param returnCode: 状态码
     * @return
     *
     */
    public Json(ReturnCode returnCode) {
        //判断是否成功状态
        this.success = returnCode == ReturnCode.成功 ? true : false;
        //状态码
        this.code = returnCode.getState();
        //返回内容
        this.msg = returnCode.getName();
        //错误
        this.errorMessage = returnCode == ReturnCode.成功 ? null : returnCode.getName();
        //时间
        this.timestamp = LocalDateTime.now();
    }


    /**
     * 构造器，统一格式参数返回
     *
     * @param returnCode: 状态码
     * @param msg:      参数
     * @return
     *
     */
    public Json(ReturnCode returnCode, String msg) {
        //判断是否成功状态
        this.success = returnCode == ReturnCode.成功 ? true : false;
        //状态码
        this.code = returnCode.getState();
        //返回内容
        this.msg = returnCode.getName();
        //参数
        this.obj = returnCode == ReturnCode.成功 ? (T) msg : null;
        //错误
        this.errorMessage = returnCode == ReturnCode.成功 ? null : msg;
        //时间
        this.timestamp = LocalDateTime.now();
    }


    /**
     * 构造器，统一格式参数返回
     *
     * @param returnCode: 状态码
     * @param obj:        参数
     * @return
     *
     */
    public Json(ReturnCode returnCode, T obj, String error) {
        //判断是否成功状态
        this.success = returnCode == ReturnCode.成功 ? true : false;
        //状态码
        this.code = returnCode.getState();
        //返回内容
        this.msg = returnCode.getName();
        //参数
        this.obj = obj;
        //错误
        this.errorMessage = returnCode == ReturnCode.成功 ? null : error;
        //时间
        this.timestamp = LocalDateTime.now();
    }


    /**
     * 构造器，统一格式参数返回
     *
     * @param returnCode: 异常
     * @param obj:        参数
     * @return
     *
     */
    public Json(ReturnCode returnCode, T obj) {
        //判断是否成功状态
        this.success = returnCode == ReturnCode.成功 ? true : false;
        //状态码
        this.code = returnCode.getState();
        //返回内容
        this.msg = returnCode.getName();
        //参数
        this.obj = obj;
        //错误
        this.errorMessage = returnCode == ReturnCode.成功 ? null : returnCode.getName();
        //时间
        this.timestamp = LocalDateTime.now();
    }


    /**
     * 拿出list结果集
     *
     * @param j:
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     *
     */
    public static List<Map<String, Object>> getList(Json j) {
        if (j.getObj() != null) {
            List<Map<String, Object>> list = (List) j.getObj();
            return list;
        } else {
            return Lists.newArrayList();
        }
    }


    /**
     * 拿出list结果集
     *
     * @param j:
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     *
     */
    public static List<Map<String, Object>> getPage(Json j) {
        if (j.getObj() != null) {
            Map<String, Object> map = (Map) j.getObj();
            if (map.get("records") != null) {
                List<Map<String, Object>> list = (List) map.get("records");
                return list;
            } else {
                return Lists.newArrayList();
            }
        } else {
            return Lists.newArrayList();
        }
    }


    /**
     * 拿出对象结果集
     *
     * @param j:
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     *
     */
    public static Map<String, Object> get(Json j) {
        if (j.getObj() != null) {
            Map<String, Object> map = (Map) j.getObj();
            return map;
        } else {
            return Maps.newHashMap();
        }
    }
}

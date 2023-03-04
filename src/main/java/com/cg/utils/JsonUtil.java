package com.cg.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * json转换工具类
 *
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 对静态变量进行统一参数设置
     */
    static {
        //序列化日期时是否以timestamps输出
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置可用单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //设置实体无属性和json串属性对应时不会出错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //对象为null不进行序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //设置时间格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @return ObjectMapper        返回类型
     * @throws
     * @Title: getJsonMapper    方法名
     * @Description: TODO        执行内容:返回ObjectMapper
     */
    public static ObjectMapper getJsonMapper() {
        return mapper;
    }

    /**
     * @param value
     * @param basicClass
     * @return T        返回类型
     * @throws
     * @Title: java    方法名
     * @Description: 执行内容:将json转换为对象
     */
    public static <T> T bean(String value, Class<T> basicClass) {
        //判断参数为空直径返回null
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            //将json转换为java类
            return mapper.readValue(value, basicClass);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    /**
     * @param value
     * @param classType
     * @return T        返回类型
     * @throws
     * @Title: java    方法名
     * @Description: 执行内容:将json转换为对象
     */
    public static <T> List<T> list(String value, Class<T> classType) {
        //判断参数为空直径返回null
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);
            //将json转换为java类
            return mapper.readValue(value, javaType);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @param value
     * @return String        返回类型
     * @throws
     * @Title: json    方法名
     * @Description: 执行内容:将对象转为json
     */
    public static String json(Object value) {
        try {
            //将java类转换为json
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}

package com.cg.extra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止重复
 * @author cqry
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PreventRepeat {
    /**
     * 限制时间
     * 单位：秒
     */
    int restrictTime() default 60;

    /**
     * 限制时是否包含请求参数
     */
    boolean includeParam() default false;
}

//package com.cg.extra;
//
//import cn.hutool.crypto.digest.DigestUtil;
//import cn.hutool.json.JSONUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.lang.reflect.UndeclaredThrowableException;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
///**
// * 防重复提交切面
// */
//@Slf4j
//@Aspect
//@Component
//public class PreventRepeatAspect {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    /**
//     * 对所有LoginRequired的注解类实现切点
//     */
//    @Pointcut("within(@com.cg.extra.RequestRequired *)")
//    public void pointcut() {
//
//    }
//
//    /**
//     * 前置增强
//     */
//    @Before("pointcut()")
//    public void before(JoinPoint point) {
//        //请求头
//        HttpServletRequest request = getHttpServletRequest();
//        //取出用户
//        String token = getToken(request);
//        //判断token不为空
//        if (StringUtils.isNotBlank(token)) {
//            //接口幂等
//            doPreventRepeat(point, token, true);
//        }
//    }
//
//
//    /**
//     * 后置增强
//     */
//    @After("pointcut()")
//    public void after(JoinPoint joinPoint) {
//        HttpServletRequest request = getHttpServletRequest();
//        //取出用户
//        String token = getToken(request);
//        //判断token不为空
//        if (StringUtils.isNotBlank(token)) {
//            //后置删除幂等缓存
//            doPreventRepeat(joinPoint, token, false);
//        }
//    }
//
//
//    /**
//     * 异常增强
//     */
//    @AfterThrowing(value = "pointcut()", throwing = "e")
//    public void afterThrow(JoinPoint joinPoint, Throwable e) {
//        HttpServletRequest request = getHttpServletRequest();
//        //取出用户
//        String token = getToken(request);
//        //判断token不为空 幂等异常不删除
//        boolean isRepeatEx = e instanceof PreventRepeatException;
//        if (e instanceof UndeclaredThrowableException) {
//            isRepeatEx = ((UndeclaredThrowableException) e).getUndeclaredThrowable() instanceof PreventRepeatException;
//        }
//        if (StringUtils.isNotBlank(token) && !isRepeatEx) {
//            //异常删除幂等缓存
//            doPreventRepeat(joinPoint, token, false);
//        }
//    }
//
//
//    private HttpServletRequest getHttpServletRequest() {
//        //获得请求
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        return attributes.getRequest();
//    }
//
//
//    private String getToken(HttpServletRequest request) {
//        return request.getHeader("token");
//    }
//
//
//    public void doPreventRepeat(JoinPoint joinPoint, String token, boolean before) {
//        //获得执行方法
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        //判断方法是否加了注解启用
//        boolean preventRepeat = method.isAnnotationPresent(PreventRepeat.class);
//        if (preventRepeat) {
//            PreventRepeat annotation = method.getAnnotation(PreventRepeat.class);
//            int time = annotation.restrictTime();
//            boolean includeParam = annotation.includeParam();
//            String key = getKey(joinPoint, method, token, includeParam);
//            if (before) {
//                //日志打印
//                String userName = "张三";
//                Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(key, token, time, TimeUnit.SECONDS);
//                if (Objects.isNull(b) || !b) {
//                    log.info("用户{}" + joinPoint.getSignature().getName() + "重复请求", userName);
//                    //抛出异常
//                    throw new PreventRepeatException("服务正在处理中，请勿重复提交");
//                }
//            } else {
//                stringRedisTemplate.delete(key);
//            }
//        }
//    }
//
//
//    /**
//     * 生成方法标记：采用数字签名算法SHA1对方法签名字符串加签
//     *
//     * @param args
//     * @return
//     */
//    private String getMethodSign(Object... args) {
//        StringBuilder sb = new StringBuilder();
//        for (Object arg : args) {
//            sb.append(toString(arg));
//        }
//        return DigestUtil.sha1Hex(sb.toString());
//    }
//
//
//    private String toString(Object arg) {
//        if (Objects.isNull(arg)) {
//            return "null";
//        }
//        if (arg instanceof Number) {
//            return arg.toString();
//        }
//        return JSONUtil.toJsonStr(arg);
//    }
//
//
//    public String getKey(JoinPoint joinPoint, Method method, String token, boolean includeParam) {
//        String className = joinPoint.getTarget().getClass().getName();
//        String key = token + "-" + className + method.getName();
//        if (includeParam) {
//            key += getMethodSign(method, joinPoint.getArgs());
//        }
//        return key;
//    }
//}

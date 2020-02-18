package com.wordbreak.aop;

import com.google.common.collect.Lists;
import com.wordbreak.annotation.HandleParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
//@Component
//@Aspect
public class AspectParamCheck {

    @Around("@annotation(com.wordbreak.annotation.HandleParams)")
    public Object paramCheck(ProceedingJoinPoint point) throws Throwable {
        log.info("进入参数判断");
        MethodSignature signature = (MethodSignature) point.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = point.getArgs();
        Class<?>[] parameterTypes = signature.getParameterTypes();
        HandleParams handleParams = signature.getMethod().getAnnotation(HandleParams.class);
        String[] excludeParams = handleParams.excludeParams();
        List<String> excludeParamList = Lists.newArrayList(excludeParams);
        String location = point.getTarget().toString() + "." + signature.getMethod().getName();
        for (int i = 0; i < args.length; i++) {
            if (handleParams.notBlank()) {
                notBlank(location, excludeParamList, parameterNames[i], args[i], parameterTypes[i].getName());
            } else if (handleParams.notNull()) {
                notNull(location, excludeParamList, parameterNames[i], args[i], parameterTypes[i].getName());
            }
        }
        return point.proceed();
    }

    private void notNull(String location, List<String> excludeParamList, String paramName, Object value, String paramType) {
        // 如果该参数不在排除范围内，则进行校验
        if (!excludeParamList.contains(paramName)) {
            if (value == null) {
                throw new RuntimeException(location + " Error : [" + paramName + "] is Null ,Type is [" + paramType + "]");
            }
        }
    }

    private void notBlank(String location, List<String> excludeParamList, String paramName, Object value, String paramType) {
        // 如果该参数不在排除范围内，则进行校验
        if (!excludeParamList.contains(paramName)) {
            if (value == null || StringUtils.isBlank(value.toString())) {
                throw new RuntimeException(location + " Error : [" + paramName + "] is Blank ,Type is [" + paramType + "]");
            }
        }
    }
}

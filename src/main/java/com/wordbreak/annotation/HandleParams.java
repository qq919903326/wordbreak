package com.wordbreak.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleParams {


    /**
     * 不需要检验的参数的名称
     * 默认情况下，会检查该接口的所有参数值
     */
    String[] excludeParams() default {};


    /**
     * notNull
     * notBlank
     * 两个只会处理一个，notBlank 的优先级更高，
     * 如果notBlank 为 true 则不进行 notNull 逻辑处理，因为 notNull 是 notBlank 的子内容
     * 如果只使用 notNull 判断，则应该设置 notBlank 为 false ，notNull 为true
     */
    boolean notNull() default false;

    boolean notBlank() default true;
}


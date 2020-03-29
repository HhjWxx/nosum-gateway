package cn.nosum.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Activate {

    /**
     * 所属的组，一般是类名
     */
    String group() default "";

    /**
     * 是否激活
     */
    boolean value() default false;

    /**
     * 用于控制链路排序
     */
    int order() default 0;

}
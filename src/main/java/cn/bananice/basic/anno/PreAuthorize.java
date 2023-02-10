package cn.bananice.basic.anno;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})//注解能作用在方法上、类上
@Retention(RetentionPolicy.RUNTIME)//可以通过反射读取注解
@Inherited//可以被继承
@Documented//可以被javadoc工具提取成文档，可以不加
public @interface PreAuthorize {
    String value();
    String name();
}
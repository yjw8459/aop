package yjw8459.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//클래스에 붙일 어노테이션
@Retention(RetentionPolicy.RUNTIME)// 런타임 시점에 살아있음.
public @interface ClassAop {
}

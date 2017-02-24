package com.zqn.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 通知类
 */
@Component
@Aspect
public class MyAspect {
    @Pointcut("execution(* com.zqn.service..*.*(..))")
    public void pt(){}

    @Before("pt()")
    public void beforeAdvice() {
        System.out.println("前置通知");
    }
    @AfterReturning(value = "pt()",returning="result")
    public void afterAdvice(Object result) {
        System.out.println("后置通知 : " + result);
    }
    @AfterThrowing(value = "pt()", throwing = "ex")
    public void exceptionAdvice(Exception ex) {
        System.out.println("异常通知 :" + ex.getMessage());
    }
    @After("pt()")
    public void finallyAdvice() {
        System.out.println("最终通知");
    }



}

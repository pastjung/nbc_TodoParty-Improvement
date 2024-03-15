package com.study.todoparty.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAop {

    @Pointcut("execution(* com.study.todoparty.controller.HomeController.*(..))")
    private void cut() {}

    @Before("cut()")
    public void beforeUserMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("User Service 메서드 실행 전: " + methodName);
    }

    @AfterReturning(value = "cut()", returning = "result")
    public void afterUserMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("User Service 메서드 실행 후: " + methodName);
    }
}
package com.study.todoparty.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommentAop {

    @Pointcut("execution(* com.study.todoparty.controller.CommentController.*(..))")
    private void cut() {}


    @Before("cut()")
    public void beforeCommentMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Comment Service 메서드 실행 전: " + methodName);
    }

    @AfterReturning(value = "cut()", returning = "result")
    public void afterCommentMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Comment Service 메서드 실행 후: " + methodName);
    }
}
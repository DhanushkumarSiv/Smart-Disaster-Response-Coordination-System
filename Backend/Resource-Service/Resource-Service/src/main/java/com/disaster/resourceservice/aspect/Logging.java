package com.disaster.resourceservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Logging {

    @Before("execution(* com.disaster.resourceservice.service.*.*(..))")
    public void serviceLogBefore(JoinPoint joinPoint) {
        log.info("----> [Entering Method] : {}", joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.disaster.resourceservice.service.*.*(..))")
    public void serviceLogAfter(JoinPoint joinPoint) {
        log.info("----> [Exiting Method] : {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.disaster.resourceservice.service.*.*(..))", returning = "result")
    public void serviceLogAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("----> [Method called] : {}  [returned] : {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "execution(* com.disaster.resourceservice.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("----> [Exception]  {} : {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }
}

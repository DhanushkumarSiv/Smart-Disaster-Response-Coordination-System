package com.disaster.alertservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Logging {

    @Before("execution(* om.disaster.alertservice.service.*.*(..))")
    public void serviceLogBefore(JoinPoint joinPoint) {
        log.info("----> [Entering Method] : {}", joinPoint.getSignature().toShortString());
    }

    @After("execution(* om.disaster.alertservice.service.*.*(..))")
    public void serviceLogAfter(JoinPoint joinPoint) {
        log.info("----> [Exiting Method] : {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* om.disaster.alertservice.service.*.*(..))", returning = "result")
    public void serviceLogAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("----> [Method called] : {}  [returned] : {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "execution(* om.disaster.alertservice.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("----> [Exception]  {} : {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }
}

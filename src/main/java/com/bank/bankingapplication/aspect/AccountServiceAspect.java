package com.bank.bankingapplication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AccountServiceAspect {

    // @Around advice for all methods in AccountService
    @Around("execution(* com.bank.bankingapplication.service.AccountService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Log method start
        log.info(":::::::::: Starting method: " + joinPoint.getSignature());

        long startTime = System.currentTimeMillis();

        // Proceed to the actual method
        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;

        // Log method end and time taken
        log.info(" ::::::::: Finished method: " + joinPoint.getSignature() + " in " + timeTaken + " ms");

        return result; // Return the method result
    }
}


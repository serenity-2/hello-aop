package com.jzjr.aspectaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class WebLogAcpect {
    private Logger logger = LoggerFactory.getLogger(WebLogAcpect.class);

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.jzjr.aspectaop..*.*(..))")
    public void webLog(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("before running");
    }

    /**
     * 后置通知@AfterReturning：在某连接点之后执行的通知，通常在一个匹配的方法返回的时候执行
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("doAfterReturning running");
    }

    @Around("webLog()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around running");
        Object proceed = proceedingJoinPoint.proceed();
    }

    /**
     * 后置最终通知@After：当某连接点退出时执行的通知（不论是正常返回还是异常退出）
     * @param joinPoint
     */
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint){
       log.info("after running");
    }



}

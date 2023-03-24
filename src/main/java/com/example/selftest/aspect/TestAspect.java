//package com.example.selftest.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class TestAspect {
//    private final Logger logger = LoggerFactory.getLogger(TestAspect.class);
//
//    // 切入点描述 这个是controller包的切入点
//    @Pointcut("execution(* com.example.selftest.controller.*(..))")
//    // 签名，可以理解成这个切入点的一个名称
//    public void controllerLog() {
//    }
//
//    // 切入点描述 这个是controller包的切入点
//    @Pointcut("execution(* com.example.selftest.service.*(..))")
//    // 签名，可以理解成这个切入点的一个名称
//    public void serviceLog() {
//    }
//
//    // 在切入点的方法run之前要干的
//    @Before("controllerLog()")
//    public void doBefore(JoinPoint joinPoint) {
//        System.out.println("%%%=>Controller层切面");
//    }
//
//    // 在切入点的方法run之前要干的
//    @Before("serviceLog()")
//    public void doServiceLog(JoinPoint joinPoint) {
//        System.out.println("###=>Service层切面");
//    }
//}

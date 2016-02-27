package com.bap.erp.aspects;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AuthenticationAspect {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AuthenticationAspect.class);

    @Before("execution(* com.bap.erp.ws..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
//        log.info("logBefore() is running!");
//        log.info("AuthenticationAspect : " + joinPoint.getSignature().getName());

    }

}

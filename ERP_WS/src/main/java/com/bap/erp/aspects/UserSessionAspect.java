package com.bap.erp.aspects;

import com.bap.erp.commons.entities.AbstractEntity;
import com.bap.erp.ws.clients.CPanelClient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class UserSessionAspect {

    @Autowired
    private CPanelClient cPanelClient;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserSessionAspect.class);

    @Before("execution(* com.bap.erp.servicios..*.persist*(..))")
    public void logBeforePersistEntity(JoinPoint joinPoint) {

        log.info("logBefore() is running! " + cPanelClient);
        //log.info("AuthenticationAspect : " + joinPoint.getSignature().getName() + " ::::: cPanelClient.user: " + cPanelClient.getUser());

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            log.info(arg.getClass());
            if (arg instanceof AbstractEntity) {
                log.info(arg.getClass() + " is instance of AbstractEntity");
                log.info("Setting Fecha/Usuario Alta for : " + arg.getClass());
                setUsuarioFechaAlta(arg);

            } else {
                log.info("This is not a default Entity, let's see if has Entity attributes");
                List<AbstractEntity> listOfEntities = getEntitiesFromPojo(arg);
            }
        }
    }

    List<AbstractEntity> getEntitiesFromPojo(Object arg) {
        log.info("Hey, we have a fucking POJO::: " + arg.getClass().getName());

        //Let's get all field from a class and see if it has an AbstractEntity
        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {

            try {
                if (field.getType().isInterface()) {
                    log.info("List ==> Field:: " + field.getName() + " : " + field.getType());
                    Field f = arg.getClass().getDeclaredField(field.getName());
                    log.info("List ==> Field:: " + field.getName() + " : " + field.getType() + " method: " + "get" + toUpperCaseFirstChar(f.getName()));

                    ArrayList<AbstractEntity> list = (ArrayList<AbstractEntity>)invokeMethod(arg, "get" + toUpperCaseFirstChar(f.getName()), null, null, false);
                    log.info(list);
                    for (AbstractEntity element : list) {
                        setUsuarioFechaAlta(element);
                    }

                } else {
                    Object newInstance = field.getType().newInstance();
                    log.info("POJO field:: " + newInstance.getClass());
                    if (newInstance instanceof AbstractEntity) {

                        Field f = arg.getClass().getDeclaredField(field.getName());
                        log.info("AbstractEntity ==> Field:: " + field.getName() + " : " + field.getType() + " method: " + "get" + toUpperCaseFirstChar(f.getName()));

                        Object o = invokeMethod(arg, "get" + toUpperCaseFirstChar(f.getName()), null, null, false);

                        log.info("Value: " + o);
                        log.info("Setting Fecha/Usuario Alta for : " + o.getClass());
                        setUsuarioFechaAlta(o);

                    }
                }

            } catch (InstantiationException ex) {
                Logger.getLogger(UserSessionAspect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UserSessionAspect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(UserSessionAspect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UserSessionAspect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList<AbstractEntity>();
    }

    private void setUsuarioFechaAlta(Object arg) {

        Class[] paramDate = new Class[1];
        paramDate[0] = Date.class;

        Class[] paramString = new Class[1];
        paramString[0] = String.class;

        try {
//            /**
//             * *****Setting fechaAlta******
//             */
//            //Getting the method
//            method = arg.getClass().getSuperclass().getDeclaredMethod("setFechaAlta", paramDate);
//            //Executing the method
//            Object o = method.invoke(arg, new Date());
//            log.info("Setting FechaAlta = " + new Date());
//
//            /**
//             * *****Setting fechaAlta******
//             */
//            //Getting the method
//            method = arg.getClass().getSuperclass().getDeclaredMethod("setUsuarioAlta", paramString);
//            //Executing the method
//            o = method.invoke(arg, cPanelClient.getUser());
//            log.info("Setting UsuarioAlta = " + cPanelClient.getUser());

            invokeMethod(arg, "setFechaAlta", paramDate, new Date(), true);
            
            log.info("Setting UsuarioAlta = " + cPanelClient.getUser());
            invokeMethod(arg, "setUsuarioAlta", paramString, cPanelClient.getUser(), true);
            log.info("Set FechaAlta & UsuarioAlta::  " + new Date() + " / " + cPanelClient.getUser());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object invokeMethod(Object arg, String methodName, Class[] params, Object value, boolean fromSuperClass) throws Exception {
        try {
            log.info("Invoking for: " + arg.getClass() + " method: " + methodName + " ::: fromSuperClass: " + fromSuperClass);
            //Getting the method
            Method method = null;
            if (fromSuperClass) {
                method = arg.getClass().getSuperclass().getDeclaredMethod(methodName, params);
            } else {
                method = arg.getClass().getDeclaredMethod(methodName, params);
            }
            //Executing the method
            Object o;
            if (value == null) {
                o = method.invoke(arg);
            } else {
                o = method.invoke(arg, value);
            }

            return o;

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw e;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String toUpperCaseFirstChar(String string) {
        Character c = string.charAt(0);
        string = c.toString().toUpperCase() + string.substring(1, string.length());
        return string;
    }

}

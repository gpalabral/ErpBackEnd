package com.bap.erp.commons.utils;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

public class ObjectUtils {

    private static org.apache.log4j.Logger log = Logger.getLogger(ObjectUtils.class);

    public static void printObjectState(final Serializable o) {
        if (o != null) {
            log.info("====================OBJECT STATE (" + o.getClass().getName() + ")=========================");
            Method[] metodos = o.getClass().getMethods();
            for (int i = 0; i < metodos.length; i++) {
                String metodo = metodos[i].getName();
                if ((metodo.indexOf("is") == 0 || metodo.indexOf("get") == 0) && metodo.indexOf("getClass") != 0) {
                    //System.out.println(">> " + metodo + " = " + String.valueOf(invocarMetodo(o, metodo)));
                    log.info(">> " + metodo.substring(3, metodo.length()) + ": \t\t --> " + String.valueOf(invocarMetodo(o, metodo)));
                    log.info("-----------------------------------------------------------------------------------------------------");
                }
            }
            log.info("====================OBJECT STATE (" + o.getClass().getName() + ")=========================");
        } else if (o == null) {
            throw new IllegalStateException("El objeto es nulo");
        }
    }    
        

    public static void printObjectState(final Serializable o, final String label) {
        log.info("Printing Object State: " + label);
        printObjectState(o);
    }

    private static Object invocarMetodo(final Object object, final String methodName) {
        try {
            Method method = object.getClass().getMethod(methodName);
            return method.invoke(object);
        } catch (Exception e) {
            throw new IllegalStateException("Error al invocar el metodo: " + object + "." + methodName);
        }

    }

}

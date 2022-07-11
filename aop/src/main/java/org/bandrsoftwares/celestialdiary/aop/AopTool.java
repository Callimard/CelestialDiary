package org.bandrsoftwares.celestialdiary.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
@SuppressWarnings("unchecked")
public class AopTool {

    private AopTool() {
    }

    public static <T> T extractSpecificParameter(JoinPoint jp, Class<? extends Annotation> annotationClass) {
        Object[] args = jp.getArgs();
        Parameter[] parameters = extractJoinPointMethodParameter(jp);
        if (parameters.length < 1) {
            throw new AopMethodSignatureException("Method does not have parameter");
        } else {
            int index = searchParameter(parameters, annotationClass);
            if (index < 0) {
                throw new AopMethodSignatureException("Method does not have parameter annotated with the annotation " + annotationClass);
            } else {
                try {
                    return (T) args[index];
                } catch (ClassCastException e) {
                    throw new AopMethodSignatureException(e);
                }
            }
        }
    }

    public static Parameter[] extractJoinPointMethodParameter(JoinPoint jp) {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        return method.getParameters();
    }

    private static int searchParameter(Parameter[] parameters, Class<? extends Annotation> annotationClass) {
        int index = 0;
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(annotationClass)) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }
}

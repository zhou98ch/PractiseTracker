package com.example.server.aspect;

import com.example.server.annotation.AutoFill;
import constant.AutoFillConstant;
import context.UserContext;
import enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * custom aspect, implement the public fields auto fill logic
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * cut point: all function under the "mapper" package with annotation of "@AutoFill"
     */
    @Pointcut("execution(* com.example.server.mapper.*.*(..)) && @annotation(com.example.server.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * before advice, update insert before the SQL execution
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("start to autofill public fields...");

        //1. get the database operation type on the intercepted method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//function object signature
        //Achtung: here needs to (downward) cast the signature to MethodSignature to get the method object
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//retrieve the annotation object on the intercepted method
        OperationType operationType = autoFill.value();//obtain the database operation type

        //2. retrieve the entity object from the intercepted method's arguments
        Object[] args = joinPoint.getArgs();
        if(args.length == 0 || args == null){
            return;
        }
        Object entity = args[0];

        //3. prepare the data to be assigned

        LocalDate now = LocalDate.now(); //TODO update to LocalDateTime
        Long currentId = UserContext.getCurrentId();

        //4. acoording to the database operation type, assign values to the corresponding fields using reflection
        if(operationType == OperationType.INSERT){
            //assign values to the 3 public fields
            try{
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDate.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDate.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);

                //assign value by reflection
                setCreateTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(operationType == OperationType.UPDATE){
            //assign values to the 1 public fields
            try{
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                //assign value by reflection
                setUpdateTime.invoke(entity, now);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
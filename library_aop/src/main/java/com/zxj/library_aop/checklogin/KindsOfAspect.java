package com.zxj.library_aop.checklogin;

import android.os.Trace;
import android.util.Log;

import com.zxj.common.utils.LogUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class KindsOfAspect {

    /**
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* android.app.Activity.**(..))")
    public void method(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getThis().getClass().getSimpleName();
        LogUtils.e("TAG", "class:" + className + " method:" + methodSignature.getName());
    }

    @Before("execution(* android.app.Activity+.onCreate(..))")
    public void before(JoinPoint joinPoint) {
        Trace.beginSection(joinPoint.getSignature().toString());
        LogUtils.e("time",""+System.currentTimeMillis());
    }

    @After("execution(* android.app.Activity+.onCreate(..))")
    public void after() {
        Trace.endSection();
        LogUtils.e("time",""+System.currentTimeMillis());
    }

}

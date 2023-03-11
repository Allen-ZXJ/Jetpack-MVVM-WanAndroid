package com.zxj.library_aop.checklogin;

import android.os.Trace;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zxj.common.utils.Constraint;
import com.zxj.common.utils.CookieUtils;
import com.zxj.common.utils.LogUtils;
import com.zxj.library_aop.checklogin.annotation.CheckLogin;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 遇到的坑：
 * 1.我用的是org.aspectj:aspectjrt，注解需要为运行时期，RUNTIME级，否则报zipException(因为Around等都是runtime级别的)
 * 2.修改后报错必须改正和clean掉整个工程再编译，否则改正后编译还是会报错
 */
@Aspect
public class CheckLoginAspect {

    @Pointcut("execution(" +//执行语句
            "@com.zxj.library_aop.checklogin.annotation.CheckLogin" +//注解筛选
            " * " + //类路径,*为任意路径
            "*" +   //方法名,*为任意方法名
            "(..)" +//方法参数,'..'为任意个任意类型参数
            ")" +
            " && " +//并集
            "@annotation(checkLogin)"//注解筛选,这里主要用于下面方法的'CheckLogin'参数获取
    )
    public void pointcutCheckLogin(CheckLogin checkLogin) {

    }

    @Around("pointcutCheckLogin(checkLogin)")
    public Object aroundCheckLogin(ProceedingJoinPoint joinPoint, final CheckLogin checkLogin) throws Throwable {
        LogUtils.d("TAG", "登录校验");
        if (CookieUtils.isExpired()) {
            ARouter.getInstance().build(Constraint.LOGIN_PATH).navigation();
            Log.i("TAG", "未登录");
            return null;
        } else {
            LogUtils.d("TAG", "aroundCheckLogin: " + "   已登录");
            return joinPoint.proceed();
        }
    }



}


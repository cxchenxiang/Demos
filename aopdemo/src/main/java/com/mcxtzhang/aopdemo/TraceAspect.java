package com.mcxtzhang.aopdemo;

import android.text.TextUtils;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Field;

//这是我写的切面类
@Aspect
public class TraceAspect {

    private static final String TAG = "zxt/Aspectj";





    //@Pointcut("execution(* *..checkAspectJ(..)) && args(a,b)")
    @Pointcut("get(* *..isDebug)")
    public void createPoint(String s) {

    }

    @Before("createPoint()")
    public void logAfter(JoinPoint joinPoint,String s) {
        Log.d(TAG, "Before!!!  :" + joinPoint.toShortString()+"---------- s:"+s);
 /*       if (MainActivity.token.equals("a break originToken")){
            MainActivity.token = "a fixed Token";
        }*/
        Object o= null;//获取对象
        try {
            o =MainActivity.class.newInstance();
            Field f=MainActivity.class.getField("token");//根据key获取参数
            String now = (String) f.get(o);
            if (TextUtils.isEmpty(now)){
                Log.d(TAG, "now is empty and set");
                f.set(o, "a fixed Token");
            }else {
                Log.d(TAG, "now is :"+now);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

/*    @After("createPoint(a,b)")
    public void logAfter(JoinPoint joinPoint, int a, int b) {
        Log.d(TAG, "After!!!ddd:" + joinPoint.toShortString() + "----" + a + "---" + b);
    }

    @Before("createPoint(a,b)")
    public void logBefore(JoinPoint joinPoint, int a, int b) {
        Log.d(TAG, "Before!!!:" + joinPoint.toShortString() + "----" + a + "---" + b);
    }

    @Around("createPoint(a,b)")
    public Object logAround(ProceedingJoinPoint joinPoint, int a, int b) {
        Log.d(TAG, "Around!!!!:" + joinPoint.toShortString() + "----" + a + "---" + b);
        try {
            return joinPoint.proceed(new Object[]{a, b});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }*/
}
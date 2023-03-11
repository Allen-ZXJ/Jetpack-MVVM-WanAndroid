package com.zxj.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtils{
    private static ThreadPoolExecutor mExecutor;
    private static ScheduledThreadPoolExecutor mScheduleExecutor;
    public static void initThreadPool(){
        if(mExecutor == null){
           mExecutor = new ThreadPoolExecutor(5,10,4, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        }
        if(mScheduleExecutor == null){
            mScheduleExecutor = new ScheduledThreadPoolExecutor(5);
        }
    }
    public static void execute(Runnable work){
          mExecutor.execute(work);
    }
    public static void executeDelayWork(Runnable work,long delay,TimeUnit unit){
        mScheduleExecutor.schedule(work,delay,unit);
//        mScheduleExecutor.scheduleWithFixedDelay(work,10,delay,unit);
    }
    public static void executePeriodWork(Runnable work,long delay,long period,TimeUnit timeUnit){
        mScheduleExecutor.scheduleAtFixedRate(work,delay,period,timeUnit);
    }

}

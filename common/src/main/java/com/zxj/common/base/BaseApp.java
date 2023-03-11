package com.zxj.common.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;
import com.zxj.common.utils.LogUtils;
import com.zxj.common.utils.ThreadUtils;

public class BaseApp extends Application {
    String TAG = "BaseApp";
    public static Context context;
    public static Boolean isDebug = true;
    public static Boolean isLogin = false;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LogUtils.d(TAG,"App Created");
        String rootDir = MMKV.initialize(this);
        LogUtils.d(TAG,"MMKV在 " + rootDir);
        ThreadUtils.initThreadPool();
        if(isDebug){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

    }
}

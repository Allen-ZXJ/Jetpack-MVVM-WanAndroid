package com.zxj.wanandroid;
import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;
import com.zxj.common.utils.LogUtils;
import com.zxj.common.utils.ThreadUtils;

public class MyApp extends Application {
    String TAG = "MyApp";
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG,"App Created" + this);
        String rootDir = MMKV.initialize(this);
        LogUtils.d(TAG,"MMKV在 " + rootDir);
        ThreadUtils.initThreadPool();
//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog();     // 打印日志
//            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }
}

package com.zxj.common.utils;

import android.util.Log;

import com.tencent.mmkv.MMKV;

public class LogUtils {
    public static final int VERBOSE  = 1;
    public static final int DEBUG    = 2;
    public static final int INFO     = 3;
    public static final int WARN     = 4;
    public static final int ERROR    = 5;
    public static final int NOTHING  = 6;
    public static int level = VERBOSE;

    public static void v(String tag,String message){
        if(level <= VERBOSE){
            Log.v(tag, message);
        }
    }

    public static void d(String tag,String message){
        if(level <= DEBUG){
            Log.d(tag, message);
        }
    }

    public static void i(String tag,String message){
        if(level <= INFO){
            Log.i(tag, message);
        }
    }

    public static void w(String tag,String message){
        if(level <= WARN){
            Log.w(tag, message);
        }
    }

    public static void e(String tag,String message){
        if(level <= ERROR){
            Log.e(tag, message);
        }
    }

}

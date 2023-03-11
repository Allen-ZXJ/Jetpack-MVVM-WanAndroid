package com.zxj.common.utils;

import android.os.Parcelable;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonUtils {
    private GsonUtils(){
        if(gson == null){
            gson = new Gson();
        }
    }
    private volatile static GsonUtils instance;
    private static Gson gson;

    public static GsonUtils getInstance() {
        if(instance == null){
            synchronized (GsonUtils.class){
                if(instance == null){
                    instance = new GsonUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 除List外数据类型的调用
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T simplefromJson (String json,Class<T> clazz){
        T obj;
        if(json != null){
            obj = gson.fromJson(json, clazz);
            return obj;
        }
        return null;

    }

    /**
     * List和Map反序列化需要借助TypeToken
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> ArrayList<T> listFromJson (String json,Class<T> clazz){
         T obj;
         if(json != null){
             Type type = new TypeToken<ArrayList<T>>() {
             }.getType();
             return gson.fromJson(json,type);
         }
         return null;
    }


    /**
     *  序列化成json，任何数据都一样操作
      * @param obj
     * @param <T>
     * @return
     */
    public <T> String toJson(T obj){
        if(obj != null){
            return gson.toJson(obj);
        }
        return null;

    }
}

package com.zxj.common.network;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.zxj.common.utils.Constraint;
import com.zxj.common.utils.CookieUtils;
import com.zxj.common.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class MCookieJar implements CookieJar {
    MMKV kv;
    private static Gson gson = new Gson();
    private MCookieJar(){}
    private static volatile MCookieJar instance;
    public static MCookieJar getInstance(){
        if(instance == null){
            synchronized (MCookieJar.class){
                if(instance == null){
                    instance = new MCookieJar();
                }
            }
        }
        return instance;
    }

    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
        kv = MMKV.defaultMMKV();
        String key =  httpUrl.host() + "/" + kv.decodeString(Constraint.Login_USER_NAME);
        Log.d("key", "loadForRequest: " + key + "    " + kv.decodeLong(Constraint.COOKIE_EXPIRE_TIME));
        String cookiesJson = kv.decodeString(key);
        if(cookiesJson == null || CookieUtils.isExpired()) {
            Log.d("CookieJar", "CookiesJson为null ");
            return new ArrayList<Cookie>();
        }
        Log.d("cookiesJson",  cookiesJson);
        CookiesStore cookiesStore = gson.fromJson(cookiesJson, CookiesStore.class);
        Log.d("CookieJar", "loadForRequest: " + cookiesStore.getCookies().size());
        return cookiesStore.getCookies();
    }

    @Override
    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
        int cnt = list.size();
        LogUtils.d("CookieList"," " + cnt + "   " + list.get(0));
        if(cnt < 4) return; //测试过接口，返回的cookie不是4就是5，或者1
        String userName = list.get(cnt-2).value();
        String key = httpUrl.host() + "/" +userName;
        Log.d("key", "saveFromResponse:    " + key);
        long expire = list.get(cnt-1).expiresAt();
        LogUtils.d("CookieStore", "返回的cookie： " + list.size() + "    " + list);
        LogUtils.d("CookieName", "saveFromResponse: " + list.get(cnt-2).value());
        if(kv.decodeString(key) == null || CookieUtils.isExpired()){
            //若是第一次请求，或者cookie过期了，则保存cookie到本地
                saveCookies(cnt,key,list);
        }
        kv.encode(Constraint.COOKIE_EXPIRE_TIME,expire);
        kv.encode(Constraint.Login_USER_NAME,userName);

    }

    private boolean saveCookies(int cookieCnt,String key,List<Cookie> list){
        kv = MMKV.defaultMMKV();
        boolean isSuccess = false;
        if(cookieCnt == 4){
            String cookies = gson.toJson(new CookiesStore(list));
                    isSuccess = kv.encode(key, cookies);
            LogUtils.d("CookieStore","Cookie数为4是否保存成功："+isSuccess + '\n' + "cookies is " + cookies);
        }
        if(cookieCnt == 5){
            List<Cookie> cookieList = new ArrayList<>();
            for(int i = 1; i < 5; i++){
                cookieList.add(list.get(i));
            }
            String cookies = gson.toJson(new CookiesStore(cookieList));
                   isSuccess = kv.encode(key, cookies);
            LogUtils.d("CookieStore","Cookie数为5是否保存成功："+isSuccess + '\n' + "cookies is " + cookies);
        }
        return isSuccess;
    }
}

package com.zxj.common.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zxj.common.utils.NetWorkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static volatile ApiRetrofit mApiRetrofit;
    private Context mContext;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private final String BASE_URL = "https://www.wanandroid.com/";
    private final String INTERCEPTOR = "Interceptor";

    //配置缓存拦截器，暂时不用，后续会修改
    private Interceptor mCacheInterceptor = new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable(mContext)) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetworkAvailable(mContext)) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    //日志拦截器，简单使用
    private Interceptor mLogInterceptor = chain -> {
        Log.d(INTERCEPTOR, "-----------Request Start-------- " + System.currentTimeMillis());
        Response response = chain.proceed(chain.request());
        Log.d(INTERCEPTOR,"--------Request End--------------" + System.currentTimeMillis());
        return response;
    };

    //头部拦截器，后续可能要用
    private Interceptor mHeaderInterceptor = chain -> {
        //add header
        return chain.proceed(chain.request());
    };
    public static ApiRetrofit getInstance(){
        if(mApiRetrofit == null){
            synchronized (ApiRetrofit.class){
                if(mApiRetrofit == null){
                    mApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return mApiRetrofit;
    }

    public static ApiRetrofit getInstance(Context context){
        if(mApiRetrofit == null){
            synchronized (ApiRetrofit.class){
                if(mApiRetrofit == null){
                    mApiRetrofit = new ApiRetrofit(context);
                }
            }
        }
        return mApiRetrofit;
    }

    //不配置缓存
    private ApiRetrofit(){
        mClient = new OkHttpClient.Builder()
                .callTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .cookieJar(MCookieJar.getInstance())
                .addInterceptor(mLogInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //配置缓存
    private ApiRetrofit(Context context){
        mContext = context;
        mClient = new OkHttpClient.Builder()
                  .callTimeout(10,TimeUnit.SECONDS)
                  .connectTimeout(10,TimeUnit.SECONDS)
                  .readTimeout(20, TimeUnit.SECONDS)
                  .writeTimeout(20,TimeUnit.SECONDS)
                  .cookieJar(MCookieJar.getInstance())
                  .addInterceptor(mCacheInterceptor)
                  .addInterceptor(mLogInterceptor)
                  .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T> Object getApiService(Class<T> target){
        return mRetrofit.create(target);
    }


}

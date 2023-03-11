package com.zxj.web;

import androidx.appcompat.app.AppCompatActivity;

import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.base.mvvm.BaseViewModel;

import java.lang.ref.WeakReference;

public class WebViewModel extends BaseViewModel<WebRepository> {
    private WeakReference<AppCompatActivity> context;
    public void registerContext(AppCompatActivity activity){
        context = new WeakReference<>(activity);
    }

    public WeakReference<AppCompatActivity> getContext() {
        return context;
    }

    public void onBackPressed(){
        context.get().finish();
    }
}

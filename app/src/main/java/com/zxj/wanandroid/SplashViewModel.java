package com.zxj.wanandroid;
import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.zxj.common.base.mvvm.BaseViewModel;

import java.lang.ref.WeakReference;

public class SplashViewModel extends BaseViewModel {
    private MutableLiveData<Integer> numText;
    private WeakReference<Activity> mContext;
    public void registerContext(Activity activity){
         mContext = new WeakReference<>(activity);
    }
    public MutableLiveData<Integer> getNumText() {
        if(numText == null){
            numText = new MutableLiveData<>();
            numText.setValue(4);
        }
        return numText;
    }

    public void lanuch(){
        MainActivity.launch(mContext.get());
        mContext.get().finish();
    }
}


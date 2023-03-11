package com.zxj.login.ui.viewmodel;

import androidx.fragment.app.FragmentActivity;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.login.ResultCallBack;
import com.zxj.login.repository.RegisterRepository;

import java.lang.ref.WeakReference;

public class RegisterViewModel extends BaseViewModel<RegisterRepository> {
    private WeakReference<FragmentActivity> context;
    public void registerContext(FragmentActivity activity){
        context = new WeakReference<>(activity);
    }
    public void register(String userName, String password, String repassword, ResultCallBack callBack){
        model.register(userName,password,repassword,callBack);
    }
}

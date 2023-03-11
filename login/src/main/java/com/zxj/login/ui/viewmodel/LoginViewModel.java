package com.zxj.login.ui.viewmodel;

import androidx.fragment.app.FragmentActivity;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.login.ResultCallBack;
import com.zxj.login.repository.LoginRepository;

import java.lang.ref.WeakReference;

public class LoginViewModel extends BaseViewModel<LoginRepository> {
    private WeakReference<FragmentActivity> context;
    public void registerContext(FragmentActivity activity){
        context = new WeakReference<>(activity);
    }
    public void login(String userName, String password, ResultCallBack callBack){
        model.login(userName,password,callBack);

    }



}

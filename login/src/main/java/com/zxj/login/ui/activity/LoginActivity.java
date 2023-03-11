package com.zxj.login.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.login.R;
import com.zxj.login.databinding.AcLoginMainBinding;
import com.zxj.login.ui.fragment.RegisterFragment;

@Route(path = "/login/activity")
public class LoginActivity extends BaseMvvmActivity<AcLoginMainBinding, BaseViewModel> {
    Fragment fragment;
    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        super.afterOnCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_login_main;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

}
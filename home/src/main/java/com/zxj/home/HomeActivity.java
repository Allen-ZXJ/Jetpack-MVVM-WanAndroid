package com.zxj.home;

import android.os.Bundle;

import com.zxj.common.base.BaseActivity;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.home.databinding.AcHomeBinding;

public class HomeActivity extends BaseMvvmActivity<AcHomeBinding, BaseViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.ac_home;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
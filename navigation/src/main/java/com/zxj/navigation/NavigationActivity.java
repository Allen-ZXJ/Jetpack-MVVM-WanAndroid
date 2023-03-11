package com.zxj.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.navigation.databinding.AcNavigationBinding;

public class NavigationActivity extends BaseMvvmActivity<AcNavigationBinding, BaseViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.ac_navigation;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
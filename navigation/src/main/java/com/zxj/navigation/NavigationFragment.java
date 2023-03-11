package com.zxj.navigation;

import android.animation.StateListAnimator;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.utils.LogUtils;
import com.zxj.navigation.databinding.FgNavigationBinding;
import com.zxj.navigation.navi.NaviFragment;
import com.zxj.navigation.system.SystemFragment;


public class NavigationFragment extends BaseMvvmFragment<FgNavigationBinding, BaseViewModel> {
    private FragmentPagerAdapter adapter;
    private Fragment[] fragments = new Fragment[2];
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initViews() {
        LogUtils.d("NavigationFragment","initViews");

//


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_navigation;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }
}

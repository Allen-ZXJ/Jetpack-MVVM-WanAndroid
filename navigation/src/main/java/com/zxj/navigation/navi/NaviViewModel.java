package com.zxj.navigation.navi;

import androidx.lifecycle.MutableLiveData;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.bean.NavArticles;

import java.util.ArrayList;

public class NaviViewModel extends BaseViewModel<NaviRepository> {
    private MutableLiveData<ArrayList<NavArticles>> navItems;
    public NaviViewModel(){
        navItems = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<NavArticles>> getNavItems() {
        return navItems;
    }

    public void getNavData(NaviFragment.NavDataCallBack callBack){
        model.getNavData(callBack);
    }
}

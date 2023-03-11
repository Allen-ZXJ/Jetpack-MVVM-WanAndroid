package com.zxj.navigation.navi;

import android.widget.Adapter;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.NavArticles;
import com.zxj.common.utils.LogUtils;
import com.zxj.navigation.R;
import com.zxj.navigation.databinding.FgNaviBinding;
import com.zxj.navigation.navi.adapter.NaviItemAdapter;
import com.zxj.navigation.system.SystemFragment;

import java.util.ArrayList;

public class NaviFragment extends BaseMvvmFragment<FgNaviBinding,NaviViewModel> {

    private NaviItemAdapter adapter;


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initViews() {
        LogUtils.d("NaviFragment","initViews");
        mbind.rvFgNavi.setLayoutManager(new LinearLayoutManager(getActivity()));
        mbind.setEventHandler(new EventHandler());
        mViewModel.getNavItems().observe(getActivity(),items->{
            adapter = new NaviItemAdapter(items);
            mbind.rvFgNavi.setAdapter(adapter);
        });
        mViewModel.getNavData(new NavDataCallBack() {
            @Override
            public void onSuccess(ArrayList<NavArticles> data) {
                mViewModel.getNavItems().setValue(data);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_navi;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }

    protected interface NavDataCallBack{
       void onSuccess(ArrayList<NavArticles> data);
       void onFailed(String msg);
    }

    public class EventHandler{
        public void onNavigate(){
            NavHostFragment.findNavController(NaviFragment.this).navigate(R.id.action_naviFragment_to_systemFragment);
        }
    }
}

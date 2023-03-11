package com.zxj.navigation.system;

import android.util.Log;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.SystemTree;
import com.zxj.navigation.R;
import com.zxj.navigation.databinding.FgSystemBinding;
import com.zxj.navigation.system.adapter.SystemTreeAdapter;

import java.util.ArrayList;

public class SystemFragment extends BaseMvvmFragment<FgSystemBinding,SystemViewModel> {
     SystemTreeAdapter mRvAdapter;
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initViews() {
        mbind.rvFgSystem.setLayoutManager(new LinearLayoutManager(getActivity()));
        mbind.setEventHandler(new EventHandler());
        mViewModel.getSystemTree().observe(getActivity(),systemTrees -> {
               if(systemTrees != null){
                   mRvAdapter = new SystemTreeAdapter(mViewModel.getSystemTree().getValue());
                   mbind.rvFgSystem.setAdapter(mRvAdapter);
               }
        });
        mViewModel.getSystemTrees(new SystemDataCallBack() {
            @Override
            public void onSuccess(ArrayList<SystemTree> data) {
                mViewModel.getSystemTree().setValue(data);
            }

            @Override
            public void onFailed(String message) {
                Toast.makeText(BaseApp.context,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_system;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }

    protected interface SystemDataCallBack{
        void onSuccess(ArrayList<SystemTree> data);
        void onFailed(String message);
    }
    public class EventHandler{
        public void onPressBack(){
            NavHostFragment.findNavController(SystemFragment.this).navigateUp();
        }
    }
}

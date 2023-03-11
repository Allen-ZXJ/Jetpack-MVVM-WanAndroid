package com.zxj.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.zxj.common.base.BaseApp;
import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.ProjectClassify;
import com.zxj.project.concrete.ConcreteProjectFg;
import com.zxj.project.databinding.FgProjectBinding;

import java.util.ArrayList;

public class ProjectFragment extends BaseMvvmFragment<FgProjectBinding,ProjectViewModel> {
    private ArrayList<ProjectClassify> classifies;
    private FragmentPagerAdapter mVpAdapter;
    private boolean IS_INIT = false;
    private boolean IS_UPDATE = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initViews() {
        mViewModel.getClassifies().observe(getActivity(),classifies -> {
            if(classifies != null){
                this.classifies = classifies;
                mVpAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
                    @NonNull
                    @Override
                    public Fragment getItem(int position) {
                        return new ConcreteProjectFg(classifies.get(position).getId());
                    }

                    @Override
                    public int getCount() {
                        return classifies.size();
                    }

                    @Nullable
                    @Override
                    public CharSequence getPageTitle(int position) {
                        return classifies.get(position).getName();
                    }
                };
                mbind.vpProject.setAdapter(mVpAdapter);
                mbind.tbProject.setTabMode(TabLayout.MODE_SCROLLABLE);
                mbind.tbProject.setupWithViewPager(mbind.vpProject);

            }
        });

        mViewModel.getClassify(new ProjectCallBack() {
            @Override
            public void onSuccess(ArrayList<ProjectClassify> classifies) {
                IS_UPDATE = true;
                mViewModel.getClassifies().setValue(classifies);
            }

            @Override
            public void onFailed(String message) {
                Toast.makeText(BaseApp.context,message,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_project;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }

}

package com.zxj.project.concrete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.project.ProjectViewModel;
import com.zxj.project.R;
import com.zxj.project.concrete.paging.ProjectAdapter;
import com.zxj.project.databinding.FgConcreteProjectBinding;

public class ConcreteProjectFg extends BaseMvvmFragment<FgConcreteProjectBinding, ConcreteViewModel> {
    private int cid;
    private ProjectAdapter adapter;
    private boolean IS_INIT = false;
    private boolean IS_UPDATE = false;
    public ConcreteProjectFg(int id){
        cid = id;
    }

    @Override
    protected void initViews() {
        mViewModel.setCid(cid);
        adapter = new ProjectAdapter();
        mbind.rvConcreteProject.setLayoutManager(new LinearLayoutManager(getContext()));
        mbind.rvConcreteProject.setAdapter(adapter);
        mViewModel.getProjects().observe(getActivity(),projects->{
            if(projects != null){
                adapter.submitList(projects);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_concrete_project;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }
}

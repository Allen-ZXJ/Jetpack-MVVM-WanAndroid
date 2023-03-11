package com.zxj.navigation.system.concrete;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.navigation.BR;
import com.zxj.navigation.R;
import com.zxj.navigation.databinding.AcSystemConcreteBinding;
import com.zxj.navigation.system.concrete.paging.SystemArticleAdapter;

public class SystemArticleActivity extends BaseMvvmActivity<AcSystemConcreteBinding,SystemArticleViewModel> {
    private int SYSTEM_ID;
    private String SYSTEM_NAME;
    private SystemArticleAdapter adapter;
    @Override
    public void initViews() {
        fetchData();
        adapter = new SystemArticleAdapter();
        mViewModel.registerContext(this);
        mViewModel.setSelfTitle(SYSTEM_NAME);
        mViewModel.setSystemId(SYSTEM_ID);
        mbind.rvConcreteSystem.setLayoutManager(new LinearLayoutManager(this));
        mbind.rvConcreteSystem.setAdapter(adapter);
        mViewModel.getArticles().observe(this,articles -> {
            if(articles != null){
                adapter.submitList(articles);
            }
        });

    }

    @Override
    public void initEvents() {
        super.initEvents();
    }

    private void fetchData(){
        Intent intent = getIntent();
        if(intent != null){
            SYSTEM_ID =  intent.getIntExtra("systemID", 0);
            SYSTEM_NAME = intent.getStringExtra("systemName");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_system_concrete;
    }

    @Override
    protected int getVariableId() {
        return BR.article;
    }
}

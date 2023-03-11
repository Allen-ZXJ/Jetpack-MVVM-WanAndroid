package com.zxj.mine.mycollect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.mine.R;
import com.zxj.mine.databinding.AcMineMycollectBinding;
import com.zxj.mine.mycoin.MyCoinActivity;
import com.zxj.mine.mycollect.paging.CollectListAdapter;

public class MyCollectActivity extends BaseMvvmActivity<AcMineMycollectBinding,MyCollectViewModel> {
    CollectListAdapter mAdapter;
    @Override
    public void initViews() {
        mAdapter = new CollectListAdapter();
        mbind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mbind.recyclerView.setAdapter(mAdapter);
        mViewModel.getArticles().observe(this,collectArticles -> {
            mAdapter.submitList(collectArticles);
        });
    }

    @Override
    public void initEvents() {
        super.initEvents();
    }

    @Override
    public void beforeOnCreate() {
        super.beforeOnCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        super.afterOnCreate(savedInstanceState);
        mViewModel.registerContext(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_mine_mycollect;
    }

    @Override
    protected int getVariableId() {
        return BR.myCollectViewModel;
    }
    public static void launch(Context context){
        context.startActivity(new Intent(context, MyCollectActivity.class));
    }
}

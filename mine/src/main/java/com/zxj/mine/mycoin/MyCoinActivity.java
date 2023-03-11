package com.zxj.mine.mycoin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tencent.mmkv.MMKV;
import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.common.bean.CoinListItem;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.CoinApi;
import com.zxj.common.utils.GsonUtils;
import com.zxj.common.utils.LogUtils;
import com.zxj.common.utils.MMkvUtils;
import com.zxj.mine.BR;
import com.zxj.mine.R;
import com.zxj.mine.databinding.AcMineMycoinBinding;
import com.zxj.mine.mycoin.paging.CoinListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCoinActivity extends BaseMvvmActivity<AcMineMycoinBinding,MyCoinViewModel> {
    private CoinListAdapter adapter;
    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        super.afterOnCreate(savedInstanceState);
        LogUtils.d("CoinList",BaseListData.class.getCanonicalName() + "    " + BaseListData.class.getName());
        mViewModel.registerContext(this);
    }

    @Override
    public void initViews() {
        mbind.tvMyCoin.setText( MMkvUtils.getMyCoin().getCoinCount());
        mbind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CoinListAdapter();
        mbind.recyclerView.setAdapter(adapter);
        mViewModel.getmCoinList().observe(this, coinListItems -> {
                adapter.submitList(coinListItems);
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
    protected int getLayoutId() {
        return R.layout.ac_mine_mycoin;
    }

    @Override
    protected int getVariableId() {
        return BR.mycoinviewmodel;
    }

    public static void launch(Context context){
        context.startActivity(new Intent(context, MyCoinActivity.class));
    }

}

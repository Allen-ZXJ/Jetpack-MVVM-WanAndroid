package com.zxj.mine.mycoin;

import androidx.lifecycle.LiveData;

import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zxj.common.bean.CoinListItem;
import com.zxj.common.ui.viewmodel.BaseTopBarViewModel;
import com.zxj.mine.mycoin.paging.CoinListDataSource;
import com.zxj.mine.mycoin.paging.CoinListDataSourceFactory;


public class MyCoinViewModel extends BaseTopBarViewModel<MyCoinRepository> {
    private LiveData<PagedList<CoinListItem>> mCoinList;
    public MyCoinViewModel(){
        super();
        setTitle("我的积分");
        initPagedList();
    }

    public LiveData<PagedList<CoinListItem>> getmCoinList() {
        return mCoinList;
    }

    private void initPagedList() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(CoinListDataSource.PER_PAGE)
                .setPageSize(CoinListDataSource.PER_PAGE)
                .setPrefetchDistance(2)
                .setMaxSize(65536 * CoinListDataSource.PER_PAGE)
                .build();
         mCoinList = new LivePagedListBuilder<>(new CoinListDataSourceFactory(), config).build();
    }





}

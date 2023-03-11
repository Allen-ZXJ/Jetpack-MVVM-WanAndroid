package com.zxj.mine.mycoin.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.zxj.common.bean.CoinListItem;

public class CoinListDataSourceFactory extends DataSource.Factory<Integer, CoinListItem> {
    @NonNull
    @Override
    public DataSource<Integer, CoinListItem> create() {
        return new CoinListDataSource();
    }
}

package com.zxj.common.utils;

import android.util.Log;

import com.tencent.mmkv.MMKV;
import com.zxj.common.bean.Coin;

public class MMkvUtils {

    private final static MMKV mmkv = MMKV.defaultMMKV();

    public static boolean saveMyCoin(Coin coin){
        return mmkv.encode("MyCoin", coin);
    }
    public static Coin getMyCoin(){
        return mmkv.decodeParcelable("MyCoin",Coin.class);
    }


}

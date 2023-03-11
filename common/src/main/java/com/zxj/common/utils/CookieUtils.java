package com.zxj.common.utils;

import com.tencent.mmkv.MMKV;

public class CookieUtils {
     public static boolean isExpired(){
         MMKV kv = MMKV.defaultMMKV();
         long expire = kv.decodeLong(Constraint.COOKIE_EXPIRE_TIME);
         return expire == 0 || expire - System.currentTimeMillis() <= 0;
     }
}

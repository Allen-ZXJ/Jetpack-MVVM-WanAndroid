package com.zxj.login;

import com.zxj.common.bean.UserInfo;

public interface ResultCallBack {
     void onSuccess(UserInfo info);
     void onFailed(String message);
}

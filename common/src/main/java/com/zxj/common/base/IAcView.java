package com.zxj.common.base;

import android.os.Bundle;

public interface IAcView {
   void initViews();
   void initEvents();
   void beforeOnCreate();
   void afterOnCreate(Bundle savedInstanceState);
}

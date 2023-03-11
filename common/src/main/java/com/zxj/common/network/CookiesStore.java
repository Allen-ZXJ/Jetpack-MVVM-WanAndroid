package com.zxj.common.network;

import java.util.List;
import okhttp3.Cookie;

public class CookiesStore  {
   private List<Cookie> cookies;
   public CookiesStore(List<Cookie> cookies){
      this.cookies = cookies;
   }

   public List<Cookie> getCookies() {
      return cookies;
   }
}

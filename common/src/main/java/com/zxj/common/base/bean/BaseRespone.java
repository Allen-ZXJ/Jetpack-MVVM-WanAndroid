package com.zxj.common.base.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BaseRespone<T> {

   private int errorCode;

   private String errorMsg;

   private T data;

   public int getErrorCode() {
      return errorCode;
   }

   public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
   }

   public String getErrorMsg() {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public T getData() {
      return data;
   }

   public void setData(T data) {
      this.data = data;
   }

   @NonNull
   @Override
   public String toString() {
      return "data = { " + data + "   }"
              +  "  " + errorCode + "  " + errorMsg;
   }
}

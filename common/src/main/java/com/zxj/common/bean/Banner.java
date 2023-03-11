package com.zxj.common.bean;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Banner implements Parcelable {

   private String desc;
   private int id;
   private String imagePath;
   private int isVisible;
   private int order;
   private String title;
   private int type;
   private String url;

   protected Banner(Parcel in) {
      desc = in.readString();
      id = in.readInt();
      imagePath = in.readString();
      isVisible = in.readInt();
      order = in.readInt();
      title = in.readString();
      type = in.readInt();
      url = in.readString();
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(desc);
      dest.writeInt(id);
      dest.writeString(imagePath);
      dest.writeInt(isVisible);
      dest.writeInt(order);
      dest.writeString(title);
      dest.writeInt(type);
      dest.writeString(url);
   }

   @Override
   public int describeContents() {
      return 0;
   }

   public static final Creator<Banner> CREATOR = new Creator<Banner>() {
      @Override
      public Banner createFromParcel(Parcel in) {
         return new Banner(in);
      }

      @Override
      public Banner[] newArray(int size) {
         return new Banner[size];
      }
   };

   public void setDesc(String desc) {
      this.desc = desc;
   }
   public String getDesc() {
      return desc;
   }

   public void setId(int id) {
      this.id = id;
   }
   public int getId() {
      return id;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }
   public String getImagePath() {
      return imagePath;
   }

   public void setIsVisible(int isVisible) {
      this.isVisible = isVisible;
   }
   public int getIsVisible() {
      return isVisible;
   }

   public void setOrder(int order) {
      this.order = order;
   }
   public int getOrder() {
      return order;
   }

   public void setTitle(String title) {
      this.title = title;
   }
   public String getTitle() {
      return title;
   }

   public void setType(int type) {
      this.type = type;
   }
   public int getType() {
      return type;
   }

   public void setUrl(String url) {
      this.url = url;
   }
   public String getUrl() {
      return url;
   }

   @NonNull
   @Override
   public String toString() {
      return id + "   " + imagePath + "    "+ order;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Banner banner = (Banner) o;
      return id == banner.id &&
              isVisible == banner.isVisible &&
              order == banner.order &&
              type == banner.type &&
              Objects.equals(desc, banner.desc) && Objects.equals(imagePath, banner.imagePath) && Objects.equals(title, banner.title) && Objects.equals(url, banner.url);
   }

   @Override
   public int hashCode() {
      return Objects.hash(desc, id, imagePath, isVisible, order, title, type, url);
   }
}
package com.zxj.common.bean;

/**
 * Copyright 2023 bejson.com
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Auto-generated: 2023-03-02 19:55:49
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CoinListItem implements Parcelable {

    private int coinCount;
    private long date;
    private String desc;
    private long id;
    private String reason;
    private int type;
    private long userId;
    private String userName;

    protected CoinListItem(Parcel in) {
        coinCount = in.readInt();
        date = in.readLong();
        desc = in.readString();
        id = in.readLong();
        reason = in.readString();
        type = in.readInt();
        userId = in.readLong();
        userName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(coinCount);
        dest.writeLong(date);
        dest.writeString(desc);
        dest.writeLong(id);
        dest.writeString(reason);
        dest.writeInt(type);
        dest.writeLong(userId);
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CoinListItem> CREATOR = new Creator<CoinListItem>() {
        @Override
        public CoinListItem createFromParcel(Parcel in) {
            return new CoinListItem(in);
        }

        @Override
        public CoinListItem[] newArray(int size) {
            return new CoinListItem[size];
        }
    };

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }
    public int getCoinCount() {
        return coinCount;
    }

    public void setDate(long date) {
        this.date = date;
    }
    public long getDate() {
        return date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "CoinListItem{" +
                "coinCount=" + coinCount +
                ", date=" + date +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                ", reason='" + reason + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * 必须重写equals和hashcode，方便paging的差分做判断
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinListItem item = (CoinListItem) o;
        return  id == item.id
                && userId == item.userId
                && Objects.equals(desc, item.desc)
                && Objects.equals(reason, item.reason)
                && Objects.equals(userName, item.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinCount, date, desc, id, reason, type, userId, userName);
    }
}

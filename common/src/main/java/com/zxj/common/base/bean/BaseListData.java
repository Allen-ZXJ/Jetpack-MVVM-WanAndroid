package com.zxj.common.base.bean;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class BaseListData<T> {

    private int curPage;
    private ArrayList<T> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;



    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getCurPage() {
        return curPage;
    }

    public void setData(ArrayList<T> datas) {
        this.datas = datas;

    }
    public ArrayList<T> getData() {
        return datas;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getOffset() {
        return offset;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
    public boolean getOver() {
        return over;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getPageCount() {
        return pageCount;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "BaseListData{" +
                "curPage=" + curPage +
                ", datas=" + datas +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}

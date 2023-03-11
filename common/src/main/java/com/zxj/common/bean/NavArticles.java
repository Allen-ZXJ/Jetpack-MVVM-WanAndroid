package com.zxj.common.bean;

import java.util.ArrayList;
import java.util.Objects;

public class NavArticles {
    private ArrayList<HomeArticle> articles;
    private int cid;
    private String name;

    public ArrayList<HomeArticle> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<HomeArticle> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NavArticles{" +
                "articles=" + articles +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NavArticles that = (NavArticles) o;
        return cid == that.cid && Objects.equals(articles, that.articles) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articles, cid, name);
    }
}

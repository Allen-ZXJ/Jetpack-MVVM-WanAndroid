package com.zxj.common.bean;

/**
 * Copyright 2023 bejson.com
 */

import java.util.ArrayList;

/**
 * Auto-generated: 2023-03-09 11:14:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SystemTree {

    private ArrayList<HomeArticle> articleList;
    private String author;
    private ArrayList<SystemTree> children;
    private int courseId;
    private String cover;
    private String desc;
    private int id;
    private String lisense;
    private String lisenseLink;
    private String name;
    private int order;
    private int parentChapterId;
    private int type;
    private boolean userControlSetTop;
    private int visible;
    public void setArticleList(ArrayList<HomeArticle> articleList) {
        this.articleList = articleList;
    }
    public ArrayList<HomeArticle> getArticleList() {
        return articleList;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setChildren(ArrayList<SystemTree> children) {
        this.children = children;
    }
    public ArrayList<SystemTree> getChildren() {
        return children;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public int getCourseId() {
        return courseId;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getCover() {
        return cover;
    }

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

    public void setLisense(String lisense) {
        this.lisense = lisense;
    }
    public String getLisense() {
        return lisense;
    }

    public void setLisenseLink(String lisenseLink) {
        this.lisenseLink = lisenseLink;
    }
    public String getLisenseLink() {
        return lisenseLink;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return order;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }
    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }
    public boolean getUserControlSetTop() {
        return userControlSetTop;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
    public int getVisible() {
        return visible;
    }

}

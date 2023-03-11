package com.zxj.project;

import com.zxj.common.bean.ProjectClassify;

import java.util.ArrayList;

public interface ProjectCallBack{
 void onSuccess(ArrayList<ProjectClassify> classifies);
 void onFailed(String message);
}
package com.zxj.project.concrete.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.zxj.common.bean.HomeArticle;

public class ProjectDataSourceFactory extends DataSource.Factory<Integer, HomeArticle> {
   int cid;
   public ProjectDataSourceFactory(int cid){
      this.cid = cid;
   }
   @NonNull
   @Override
   public DataSource<Integer, HomeArticle> create() {
      return new ProjectDataSource(cid);
   }
}

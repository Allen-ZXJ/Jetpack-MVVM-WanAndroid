package com.zxj.common.bean;

import java.util.Objects;

public class CollectArticle {

   private String author;
   private int chapterId;
   private String chapterName;
   private int courseId;
   private String desc;
   private String envelopePic;
   private long id;
   private String link;
   private String niceDate;
   private String origin;
   private int originId;
   private long publishTime;
   private String title;
   private long userId;
   private int visible;
   private int zan;
   public void setAuthor(String author) {
      this.author = author;
   }
   public String getAuthor() {
      return author;
   }

   public void setChapterId(int chapterId) {
      this.chapterId = chapterId;
   }
   public int getChapterId() {
      return chapterId;
   }

   public void setChapterName(String chapterName) {
      this.chapterName = chapterName;
   }
   public String getChapterName() {
      return chapterName;
   }

   public void setCourseId(int courseId) {
      this.courseId = courseId;
   }
   public int getCourseId() {
      return courseId;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }
   public String getDesc() {
      return desc;
   }

   public void setEnvelopePic(String envelopePic) {
      this.envelopePic = envelopePic;
   }
   public String getEnvelopePic() {
      return envelopePic;
   }

   public void setId(long id) {
      this.id = id;
   }
   public long getId() {
      return id;
   }

   public void setLink(String link) {
      this.link = link;
   }
   public String getLink() {
      return link;
   }

   public void setNiceDate(String niceDate) {
      this.niceDate = niceDate;
   }
   public String getNiceDate() {
      return niceDate;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }
   public String getOrigin() {
      return origin;
   }

   public void setOriginId(int originId) {
      this.originId = originId;
   }
   public int getOriginId() {
      return originId;
   }

   public void setPublishTime(long publishTime) {
      this.publishTime = publishTime;
   }
   public long getPublishTime() {
      return publishTime;
   }

   public void setTitle(String title) {
      this.title = title;
   }
   public String getTitle() {
      return title;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }
   public long getUserId() {
      return userId;
   }

   public void setVisible(int visible) {
      this.visible = visible;
   }
   public int getVisible() {
      return visible;
   }

   public void setZan(int zan) {
      this.zan = zan;
   }
   public int getZan() {
      return zan;
   }

   @Override
   public String toString() {
      return "CollectArticle{" +
              "author='" + author + '\'' +
              ", chapterId=" + chapterId +
              ", chapterName='" + chapterName + '\'' +
              ", courseId=" + courseId +
              ", desc='" + desc + '\'' +
              ", envelopePic='" + envelopePic + '\'' +
              ", id=" + id +
              ", link='" + link + '\'' +
              ", niceDate='" + niceDate + '\'' +
              ", origin='" + origin + '\'' +
              ", originId=" + originId +
              ", publishTime=" + publishTime +
              ", title='" + title + '\'' +
              ", userId=" + userId +
              ", visible=" + visible +
              ", zan=" + zan +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CollectArticle that = (CollectArticle) o;
      return chapterId == that.chapterId && courseId == that.courseId && id == that.id && originId == that.originId && publishTime == that.publishTime && userId == that.userId && visible == that.visible && zan == that.zan && Objects.equals(author, that.author) && Objects.equals(chapterName, that.chapterName) && Objects.equals(desc, that.desc) && Objects.equals(envelopePic, that.envelopePic) && Objects.equals(link, that.link) && Objects.equals(niceDate, that.niceDate) && Objects.equals(origin, that.origin) && Objects.equals(title, that.title);
   }

   @Override
   public int hashCode() {
      return Objects.hash(author, chapterId, chapterName, courseId, desc, envelopePic, id, link, niceDate, origin, originId, publishTime, title, userId, visible, zan);
   }
}

package com.ttd.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wt on 2018/7/17.
 */

public class Article extends BaseItemBean{

    private String apklink;
    private String author;
    private int chapterid;
    private String chaptername;
    private boolean collect;
    private int courseid;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String  nicedate;
    private String origin;
    private String projectlink;
    private long publishTime;
    private int superchapterid;
    private String superchaptername;
    private List<Tags> tags;
    private String title;
    private int type;
    private int userid;
    private int visible;
    private int zan;

    public String getApklink() {
        return apklink;
    }

    public void setApklink(String apklink) {
        this.apklink = apklink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNicedate() {
        return nicedate;
    }

    public void setNicedate(String nicedate) {
        this.nicedate = nicedate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectlink() {
        return projectlink;
    }

    public void setProjectlink(String projectlink) {
        this.projectlink = projectlink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperchapterid() {
        return superchapterid;
    }

    public void setSuperchapterid(int superchapterid) {
        this.superchapterid = superchapterid;
    }

    public String getSuperchaptername() {
        return superchaptername;
    }

    public void setSuperchaptername(String superchaptername) {
        this.superchaptername = superchaptername;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public class Tags implements Serializable {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

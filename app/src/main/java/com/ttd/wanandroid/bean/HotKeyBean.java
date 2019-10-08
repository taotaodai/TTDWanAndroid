package com.ttd.wanandroid.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author wt
 * createDate 2018/8/22
 */
public class HotKeyBean implements Serializable{
    @SerializedName("data")
    private List<HotKey> hotKeyList;

    public List<HotKey> getHotKeyList() {
        return hotKeyList;
    }

    public class HotKey{
        private int id;
        private String link;
        private String name;
        private int order;
        private int visible;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
    }
}

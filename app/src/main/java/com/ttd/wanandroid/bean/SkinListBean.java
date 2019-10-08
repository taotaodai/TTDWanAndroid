package com.ttd.wanandroid.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by wt on 2018/7/10.
 */
@XStreamAlias("SkinListBean")
public class SkinListBean {
    @XStreamImplicit(itemFieldName = "Skin")
    private List<Skin> skins;

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

    public class Skin{
        @XStreamAsAttribute()
        @XStreamAlias("name")
        private String name;
        @XStreamAsAttribute()
        @XStreamAlias("color")
        private String color;
        @XStreamAsAttribute()
        @XStreamAlias("file")
        private String file;
        @XStreamAsAttribute()
        @XStreamAlias("alias")
        private String alias;

        private boolean isCurrentSkin;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public boolean isCurrentSkin() {
            return isCurrentSkin;
        }

        public void setCurrentSkin(boolean currentSkin) {
            isCurrentSkin = currentSkin;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}

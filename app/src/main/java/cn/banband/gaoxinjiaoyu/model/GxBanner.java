package cn.banband.gaoxinjiaoyu.model;

import cn.banband.global.model.HWModel;

public class GxBanner implements HWModel {
    private String position;
    private String image;
    private String link_type;
    private int link_id;
    private String url;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public int getLink_id() {
        return link_id;
    }

    public void setLink_id(int link_id) {
        this.link_id = link_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String[] replaceKeyFromPropertyName() {
        return new String[0];
    }
}

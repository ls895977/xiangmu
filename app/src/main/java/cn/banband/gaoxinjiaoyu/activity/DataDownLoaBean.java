package cn.banband.gaoxinjiaoyu.activity;

import cn.banband.global.model.HWModel;

public class DataDownLoaBean implements HWModel {
    private int id;
    private String title;
    private String file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    private String downloads;

    @Override
    public String[] replaceKeyFromPropertyName() {
        return new String[0];
    }
}

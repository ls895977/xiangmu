package cn.banband.gaoxinjiaoyu.model;

import cn.banband.global.model.HWModel;

public class GxUser implements HWModel {
    private int id=2;
    private String phone;
    private String nickname;
    private String head;
    private int sex;
    private int register_time;
    private int last_login_time;
    private String token="334e220f8a5435e60a5cce2408a074ed";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRegister_time() {
        return register_time;
    }

    public void setRegister_time(int register_time) {
        this.register_time = register_time;
    }

    public int getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(int last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String[] replaceKeyFromPropertyName() {
        return new String[0];
    }
}

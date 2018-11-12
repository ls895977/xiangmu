package cn.banband.global;

import cn.banband.gaoxinjiaoyu.model.GxUser;

public class HWCommon {
    public static String base_url = "http://zhitong.xjkss.com/api/";
    public static String api_sign_key = "34ff0589da51bd741d12e091a5126d86";

    public static String  app_name = "gaoxinjiaoyu";
    public static String response_msg = "info";
    public static String response_status = "status";
    public static String response_result = "data";

    private static boolean logined=true;
    private static GxUser loginUser;

    public static boolean isLogined() {
        return logined;
    }

    public static void setLogined(boolean logined) {
        HWCommon.logined = logined;
    }

    public static GxUser getLoginUser() {
        loginUser = new GxUser();
        return loginUser;
    }

    public static void setLoginUser(GxUser loginUser) {
        HWCommon.loginUser = loginUser;
    }
}

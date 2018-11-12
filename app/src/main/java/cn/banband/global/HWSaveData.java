package cn.banband.global;

import android.content.Context;
import android.content.SharedPreferences;

import cn.banband.gaoxinjiaoyu.model.GxUser;

public class HWSaveData {

    static SharedPreferences mShare = null;

    private static SharedPreferences getShared(Context context){
        if(mShare==null){
            mShare = context.getSharedPreferences(HWCommon.app_name,Context.MODE_PRIVATE);
        }
        return mShare;
    }

    public static boolean getValue(Context context,String key,boolean defaultValue){
        return getShared(context).getBoolean(key,defaultValue);
    }

    public static boolean getValue(Context context,String key){
        return getShared(context).getBoolean(key,false);
    }

    public static void putValue(Context context,String key, int val){
        SharedPreferences.Editor editor = getShared(context).edit();
        editor.putInt(key, val);
        editor.commit();
    }

    public static  void putValue(Context context, String key, float val){
        SharedPreferences.Editor editor = getShared(context).edit();
        editor.putFloat(key, val);
        editor.commit();
    }

    public static void putValue(Context context, String key, boolean val){
        SharedPreferences.Editor editor = getShared(context).edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

    public static void putValue(Context context , String key , String val){
        SharedPreferences.Editor editor = getShared(context).edit();
        editor.putString(key, val);
        editor.commit();
    }

    public static String getString(Context context , String key){
        return getShared(context).getString(key, "");
    }

    public static void saveUser(Context context , String key , GxUser user){

        SharedPreferences.Editor editor = getShared(context).edit();
        editor.putInt("uid", user.getId());
        editor.putString("phone",user.getPhone());

        editor.commit();
    }

    public static GxUser loadUser(Context context){

        GxUser user = new GxUser();
        user.setId(getShared(context).getInt("uid", -1));
        user.setPhone(getShared(context).getString("phone", null));

        return user;
    }

    public static void clearUser(Context context){
        SharedPreferences.Editor editor = getShared(context).edit();
        editor.putString("uid", "-1");
        editor.putString("username", null);
        editor.commit();
    }
}

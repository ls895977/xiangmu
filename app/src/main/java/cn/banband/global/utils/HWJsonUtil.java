package cn.banband.global.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HWJsonUtil {
    public static <T> T fromJsonToModel(JSONObject json, Class<T> pojo) throws Exception {
        HashMap<String, String> propertyMap = null;
        Object[] objs = null;
        Method m = null;

        try {
            m = pojo.getDeclaredMethod("replaceKeyFromPropertyName", new Class[0]);
        } catch (NoSuchMethodException var13) {
            ;
        }

        try {
            if(m != null) {
                propertyMap = new HashMap();
                String[] properts = (String[])m.invoke(pojo.newInstance(), (Object[])objs);
                if(properts != null && properts.length % 2 == 0) {
                    for(int i = 0; i < properts.length; i += 2) {
                        propertyMap.put(properts[i], properts[i + 1]);
                    }
                }
            }
        } catch (SecurityException var16) {
            var16.printStackTrace();
        } catch (IllegalAccessException var17) {
            var17.printStackTrace();
        } catch (IllegalArgumentException var18) {
            var18.printStackTrace();
        } catch (InvocationTargetException var19) {
            var19.printStackTrace();
        } catch (InstantiationException var20) {
            var20.printStackTrace();
        }

        Field[] fields = pojo.getDeclaredFields();
        T t = pojo.newInstance();
        Field[] var10 = fields;
        int var9 = fields.length;

        for(int var8 = 0; var8 < var9; ++var8) {
            Field field = var10[var8];
            field.setAccessible(true);
            String name = field.getName();
            if(propertyMap != null && propertyMap.keySet().contains(name)) {
                name = (String)propertyMap.get(name);
            }

            try {
                json.get(name);
            } catch (Exception var15) {
                continue;
            }

            if(json.get(name) != null && !HWStringUtils.isEmpty(json.getString(name))) {
                try {
                    if(!field.getType().equals(Long.class) && !field.getType().equals(Long.TYPE)) {
                        if(field.getType().equals(String.class)) {
                            field.set(t, json.getString(name));
                        } else if(!field.getType().equals(Float.class) && !field.getType().equals(Float.TYPE)) {
                            if(!field.getType().equals(Double.class) && !field.getType().equals(Double.TYPE)) {
                                if(!field.getType().equals(Integer.class) && !field.getType().equals(Integer.TYPE)) {
                                    if(field.getType().equals(Date.class)) {
                                        field.set(t, Long.valueOf(Date.parse(json.getString(name))));
                                    } else if(field.getType().equals(JSONArray.class)) {
                                        field.set(t, new JSONArray(json.getString(name)));
                                    } else if(field.getType().equals(JSONObject.class)) {
                                        field.set(t, json.getJSONObject(name));
                                    }
                                } else {
                                    field.set(t, Integer.valueOf(Integer.parseInt(json.getString(name))));
                                }
                            } else {
                                field.set(t, Double.valueOf(Double.parseDouble(json.getString(name))));
                            }
                        } else {
                            field.set(t, Float.valueOf(Float.parseFloat(json.getString(name))));
                        }
                    } else {
                        field.set(t, Long.valueOf(Long.parseLong(json.getString(name))));
                    }
                } catch (Exception var14) {
                    ;
                }
            }
        }

        return t;
    }

    public static <T> List<T> fromJsonArrayToList(JSONArray jsonArr, Class<T> pojo) throws Exception {
        if(jsonArr == null) {
            return null;
        } else {
            int len = jsonArr.length();
            List<T> list = new ArrayList(len);

            for(int i = 0; i < len; ++i) {
                JSONObject json = jsonArr.getJSONObject(i);
                T t = fromJsonToModel(json, pojo);
                list.add(t);
            }

            return list;
        }
    }

    public static <T> List<T> fromJsonArrayToList(List jsonArr, Class<T> pojo) throws Exception {
        if(jsonArr == null) {
            return null;
        } else {
            int len = jsonArr.size();
            List<T> list = new ArrayList(len);

            for(int i = 0; i < len; ++i) {
                JSONObject json = (JSONObject)jsonArr.get(i);
                T t = fromJsonToModel(json, pojo);
                list.add(t);
            }

            return list;
        }
    }


}

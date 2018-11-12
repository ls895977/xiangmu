package cn.banband.global.http;

import com.alibaba.fastjson.JSONArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.banband.global.HWCommon;
import cn.banband.global.utils.HWEncrypt;
import cz.msebera.android.httpclient.Header;
import cn.banband.gaoxinjiaoyu.model.GxUser;

public class HWHttpRequest {
    private static String getRequestUrl(String url){
        return HWCommon.base_url + url;
    }

    /**
     * @Description: POST回调
     * @param url	请求URL
     * @param params		请求参数
     * @return void    返回类型
     * @throws
     */
    public static void post(String url , RequestParams params , JsonHttpResponseHandler responseHandler){
        url = getRequestUrl(url);

        AsyncHttpClient client = new AsyncHttpClient();

        if (params == null){
            params = new RequestParams();
        }

        if (HWCommon.isLogined()){
            GxUser user = HWCommon.getLoginUser();
            params.put("user_id" , user.getId());
            params.put("token","fd1642800dd620dcb5508b781bdce45b");
        }else{
            params.put("user_id" , "");
            params.put("token","");
        }

        try {

            Map<String ,String> paramsMap = convertRequestParamsToMap(params);
            String jsonstr = JSONArray.toJSONString(paramsMap);
            String signstr = HWEncrypt.MD5(HWCommon.api_sign_key + jsonstr);

            params = new RequestParams();
            params.put("apisign",signstr);
            params.put("data",jsonstr);

            client.post(url, params, responseHandler);

        } catch (Exception e) {
            e.printStackTrace();
            responseHandler.onFailure(-1 , new Header[]{},new Throwable(e.getMessage()) , new JSONObject());
        }
    }

    private static Map<String ,String> convertRequestParamsToMap(RequestParams params){
        Map<String ,String> paramsMap = new HashMap<String ,String>();
        if(params!=null){
            try {
                String[] items = params.toString().split("&");
                if (items!=null){
                    for(String keyValue : items ){
                        String[] keyValues = keyValue.split("=");
                        if (keyValues!=null && keyValues.length == 2){
                            paramsMap.put(keyValues[0] , keyValues[1]);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paramsMap;
    }

    private static Map<String,String> ksort(Map<String,String> map){
        Map<String,String> sortmap = new HashMap<>();
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);//排序。
        for(String k : keys){
            String v = map.get(k);
            sortmap.put(k,v);
        }
        return sortmap;
    }



}

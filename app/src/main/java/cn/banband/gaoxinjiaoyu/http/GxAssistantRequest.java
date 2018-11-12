package cn.banband.gaoxinjiaoyu.http;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cn.banband.global.HWCommon;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWHttpRequest;
import cn.banband.global.http.HWJsonHttpResponseHandler;
import cn.banband.global.http.HWSuccessListener;
import cn.banband.global.utils.HWJsonUtil;
import cn.banband.gaoxinjiaoyu.model.GxBanner;
import cz.msebera.android.httpclient.Header;

public class GxAssistantRequest {

    public static void fileUpload(String content, String ext, final HWSuccessListener successListener, final HWFailuredListener failuredListener){

        RequestParams params = new RequestParams();
        params.put("content",content);
        params.put("ext",ext);

        HWHttpRequest.post("Assistant/fileUpload ", params, new HWJsonHttpResponseHandler(successListener,failuredListener){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);

                    if(status==1) {
                        JSONObject resultJson = response.getJSONObject(HWCommon.response_result);
                        String url = resultJson.getString("url");
                        successListener.onRespone(msg, url);
                    }else{
                        failuredListener.onRespone(msg, status);
                    }

                } catch (Exception e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void sendSms(String phone, int type, final HWSuccessListener successListener, final HWFailuredListener failuredListener){

        RequestParams params = new RequestParams();
        params.put("phone",phone);
        params.put("type",type);

        //无返回数据，只需要检查状态时。
        HWHttpRequest.post("Assistant/sendSms",params,new HWJsonHttpResponseHandler(successListener,failuredListener));
    }

    public static void service(int type, final HWSuccessListener successListener, final HWFailuredListener failuredListener){
        RequestParams params = new RequestParams();
        params.put("type",type);
        HWHttpRequest.post("Assistant/service", params, new HWJsonHttpResponseHandler(successListener,failuredListener){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);
                    if(status==1) {
                        JSONObject resultJson = response.getJSONObject(HWCommon.response_result);
                        String url = resultJson.getString("url");
                        successListener.onRespone(msg, url);
                    }else{
                        failuredListener.onRespone(msg, status);
                    }

                } catch (Exception e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void banners(int type,final HWSuccessListener successListener, final HWFailuredListener failuredListener){

        RequestParams params = new RequestParams();
        params.put("type",type);

        HWHttpRequest.post("Assistant/banners", params, new HWJsonHttpResponseHandler(successListener,failuredListener){

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);

                    if(status==1) {
                        List<GxBanner> banners = null;
                        JSONArray banner_list = response.getJSONArray(HWCommon.response_result);
                        if (banner_list != null) {
                            banners = HWJsonUtil.fromJsonArrayToList(banner_list, GxBanner.class);
                            successListener.onRespone(msg, banners);
                        }else{
                            successListener.onRespone(msg, 0);
                        }
                    }else{
                        failuredListener.onRespone(msg, status);
                    }
                } catch (Exception e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                }
            }
        });

    }



}

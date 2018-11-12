package cn.banband.global.http;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.banband.global.HWCommon;
import cz.msebera.android.httpclient.Header;

public class HWJsonHttpResponseHandler extends JsonHttpResponseHandler {
    private HWFailuredListener failuredListener;
    private HWSuccessListener successListener;

    public HWJsonHttpResponseHandler(HWSuccessListener successListener,HWFailuredListener failuredListener ) {
        this.failuredListener = failuredListener;
        this.successListener = successListener;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            String msg = (String) response.get(HWCommon.response_msg);
            int status = (int) response.get(HWCommon.response_status);
            if(status==1){
                successListener.onRespone(msg,null);
            }else{
                failuredListener.onRespone(msg,status);
            }
        }catch (JSONException e) {
            failuredListener.onRespone(e.getMessage(), -1);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            failuredListener.onRespone(e.getMessage(), -1);
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        failuredListener.onRespone(throwable.getMessage(), statusCode);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        failuredListener.onRespone(throwable.getMessage(), statusCode);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        failuredListener.onRespone(throwable.getMessage(), statusCode);
    }
}
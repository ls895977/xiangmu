package cn.banband.gaoxinjiaoyu.http;

import android.util.Log;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.banband.gaoxinjiaoyu.activity.DataDownLoaBean;
import cn.banband.gaoxinjiaoyu.model.careerquiz.CareerQuizBean;
import cn.banband.global.HWCommon;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWHttpRequest;
import cn.banband.global.http.HWJsonHttpResponseHandler;
import cn.banband.global.http.HWSuccessListener;
import cn.banband.global.utils.HWJsonUtil;
import cn.banband.gaoxinjiaoyu.model.GxRecruit;
import cz.msebera.android.httpclient.Header;

public class GxJobRequest {
    public static void recruitList(int page, int pageSize, final HWSuccessListener successListener, final HWFailuredListener failuredListener) {
        RequestParams params = new RequestParams();
        params.put("page", page);
        params.put("pageSize", pageSize);
        HWHttpRequest.post("Job/recruitList", params, new HWJsonHttpResponseHandler(successListener, failuredListener) {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);

                    if (status == 1) {
                        List<GxRecruit> recruits = null;
                        JSONArray _list = response.getJSONArray(HWCommon.response_result);
                        if (_list != null) {
                            recruits = HWJsonUtil.fromJsonArrayToList(_list, GxRecruit.class);
                            successListener.onRespone(msg, recruits);
                        } else {
                            successListener.onRespone(msg, 0);
                        }
                    } else {
                        failuredListener.onRespone(msg, status);
                    }
                } catch (Exception e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                }
            }
        });
    }

    public static void recruitDetail(int id, final HWSuccessListener successListener, final HWFailuredListener failuredListener) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        HWHttpRequest.post("Job/recruitDetail", params, new HWJsonHttpResponseHandler(successListener, failuredListener) {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);

                    JSONObject result = (JSONObject) response.getJSONObject(HWCommon.response_result);
                    GxRecruit recruit = HWJsonUtil.fromJsonToModel(result, GxRecruit.class);

                    successListener.onRespone(msg, recruit);
                } catch (JSONException e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    failuredListener.onRespone(e.getMessage(), 0);
                }
            }
        });
    }

    /**
     * 资料列表
     *
     * @param category
     * @param sort_time
     * @param sort_downloads
     * @param page
     * @param pageSize
     * @param successListener
     * @param failuredListener
     */
    public static void rematerialList(int category, int sort_time, int sort_downloads, int page, int pageSize, final HWSuccessListener successListener, final HWFailuredListener failuredListener) {
        RequestParams params = new RequestParams();
        params.put("category", category);
        params.put("sort_time", sort_time);
        params.put("sort_downloads", sort_downloads);
        params.put("page", page);
        params.put("pageSize", pageSize);
        HWHttpRequest.post("Material/materialList", params, new HWJsonHttpResponseHandler(successListener, failuredListener) {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.e("aa", "--------" + response.toString());
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);
                    if (status == 1) {
                        List<DataDownLoaBean> recruits = null;
                        JSONArray _list = response.getJSONArray(HWCommon.response_result);
                        if (_list != null) {
                            recruits = HWJsonUtil.fromJsonArrayToList(_list, DataDownLoaBean.class);
                            successListener.onRespone(msg, recruits);
                        } else {
                            successListener.onRespone(msg, 0);
                        }
                    } else {
                        failuredListener.onRespone(msg, status);
                    }
                } catch (Exception e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 资料下载
     */
    public static void recruitDownloa(String material_id, final HWSuccessListener successListener, final HWFailuredListener failuredListener) {
        RequestParams params = new RequestParams();
        params.put("material_id", material_id);
        HWHttpRequest.post("Material/materialDownload", params, new HWJsonHttpResponseHandler(successListener, failuredListener) {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.e("aa", "--------" + response.toString());
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);
                    if (status == 1) {
                        List<DataDownLoaBean> recruits = null;
                        JSONArray _list = response.getJSONArray(HWCommon.response_result);
                        if (_list != null) {
                            recruits = HWJsonUtil.fromJsonArrayToList(_list, DataDownLoaBean.class);
                            successListener.onRespone(msg, recruits);
                        } else {
                            successListener.onRespone(msg, 0);
                        }
                    } else {
                        failuredListener.onRespone(msg, status);
                    }
                } catch (Exception e) {
                    failuredListener.onRespone(e.getMessage(), 0);
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 测评列表
     */
    public static void recruitjobtestLista(String level_id, String page, final HWSuccessListener successListener, final HWFailuredListener failuredListener) {
        RequestParams params = new RequestParams();
        params.put("level_id", level_id);
        params.put("page", page);
        params.put("pageSize", "10");
        HWHttpRequest.post("Jobtest/jobtestList", params, new HWJsonHttpResponseHandler(successListener, failuredListener) {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msg = (String) response.get(HWCommon.response_msg);
                    int status = (int) response.get(HWCommon.response_status);
                    if (status == 1) {
                        List<CareerQuizBean> recruits = null;
                        JSONArray _list = response.getJSONArray(HWCommon.response_result);
                        if (_list != null) {
                            recruits = HWJsonUtil.fromJsonArrayToList(_list, CareerQuizBean.class);
                            successListener.onRespone(msg, recruits);
                        } else {
                            successListener.onRespone(msg, 0);
                        }
                    } else {
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

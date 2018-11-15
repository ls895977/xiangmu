package cn.banband.gaoxinjiaoyu.model.datadownload;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.http.GxJobRequest;
import cn.banband.global.activity.HWBaseActivity;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWSuccessListener;

public class Act_Download_details extends HWBaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addCommonHeader("资料下载");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_download_details);
        init();
    }

    @Override
    public void initSubViews() {
        findViewById(R.id.Download_details).setOnClickListener(this);
    }

    private String id;

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Download_details://点击下载
                GxJobRequest.recruitDownloa(id, new HWSuccessListener() {
                    @Override
                    public void onRespone(String msg, Object response) {

                    }
                }, new HWFailuredListener() {
                    @Override
                    public void onRespone(String msg, int errorCode) {
                        Log.e("aa", "---onRespone-----" + errorCode);
                    }
                });
                break;
        }
    }
}

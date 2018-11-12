package cn.banband.gaoxinjiaoyu.model.datadownload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.activity.DataDownLoaBean;
import cn.banband.gaoxinjiaoyu.http.GxJobRequest;
import cn.banband.gaoxinjiaoyu.model.GxRecruit;
import cn.banband.gaoxinjiaoyu.model.datadownload.adapter.DataDownloadAdapter;
import cn.banband.global.activity.HWBaseActivity;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWSuccessListener;

public class Act_DataDownload extends HWBaseActivity implements HWBaseActivity.onRight, View.OnClickListener {
    public RelativeLayout l1, l2, l3;
    public TextView tv1, tv2, tv3;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addCommonHeader("资料下载", R.drawable.icon_secher, Act_DataDownload.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_datadownload);
        init();
    }

    @Override
    public void initSubViews() {
        tv1 = findViewById(R.id.tv_DataDownload1);
        tv2 = findViewById(R.id.tv_DataDownload2);
        tv3 = findViewById(R.id.tv_DataDownload3);
        l1 = findViewById(R.id.rl_DataDownload1);
        l2 = findViewById(R.id.rl_DataDownload2);
        l3 = findViewById(R.id.rl_DataDownload3);
        myRecyclerView = findViewById(R.id.myRecyclerView);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
    }

    List<DataDownLoaBean> listData;

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        PostData(1, 2, 2, 1, 10);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onRightBt() {
        Log.e("aa", "点击了搜索。。。");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_DataDownload1://全部下载
                if (tv1.isSelected()) {
                    tv1.setSelected(false);
                } else {
                    tv1.setSelected(true);
                }
                break;
            case R.id.rl_DataDownload2://上传时间
                if (tv2.isSelected()) {
                    tv2.setSelected(false);
                } else {
                    tv2.setSelected(true);
                }
                break;
            case R.id.rl_DataDownload3://下载
                if (tv3.isSelected()) {
                    tv3.setSelected(false);
                } else {
                    tv3.setSelected(true);
                }
                break;
        }
    }

    DataDownloadAdapter adapter = null;

    public void PostData(int category, int sort_time, int sort_downloads, int page, int pageSize) {
        GxJobRequest.rematerialList(category, sort_time, sort_downloads, page, pageSize, new HWSuccessListener() {
            @Override
            public void onRespone(String msg, Object response) {
                listData = (List<DataDownLoaBean>) response;
                if (adapter == null) {
                    adapter = new DataDownloadAdapter(Act_DataDownload.this, listData);
                    myRecyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        }, new HWFailuredListener() {
            @Override
            public void onRespone(String msg, int errorCode) {
                Log.e("aa", "---onRespone-----" + errorCode);
            }
        });
    }
}

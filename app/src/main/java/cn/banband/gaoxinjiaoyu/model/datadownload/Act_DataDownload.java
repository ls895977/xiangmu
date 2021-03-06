package cn.banband.gaoxinjiaoyu.model.datadownload;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

public class Act_DataDownload extends HWBaseActivity implements HWBaseActivity.onRight, View.OnClickListener, OnRefreshListener, OnLoadMoreListener, DataDownloadAdapter.OnBackItem {
    public RelativeLayout l1, l2, l3;
    public TextView tv1, tv2, tv3;
    private ListView myListView;
    private int sort_time = 2, sort_downloads = 2;
    private SmartRefreshLayout refreshLayout;

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
        refreshLayout = findViewById(R.id.refresh_recruit_list);
        myListView = findViewById(R.id.lv_recruit_list);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
    }

    List<DataDownLoaBean> listData = new ArrayList<>();
    private int indext = 1;

    @Override
    public void initData() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        PostData(2, sort_time, sort_downloads, indext, 10);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onRightBt() {
        startActivity(new Intent(this, Act_Download_Search.class));
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
                    sort_time = 1;
                    tv2.setSelected(false);
                } else {
                    sort_time = 2;
                    tv2.setSelected(true);
                }
                indext = 1;
                listData.clear();
                PostData(2, sort_time, sort_downloads, indext, 10);
                break;
            case R.id.rl_DataDownload3://下载
                if (tv3.isSelected()) {
                    sort_downloads = 1;
                    tv3.setSelected(false);
                } else {
                    sort_downloads = 2;
                    tv3.setSelected(true);
                }
                indext = 1;
                listData.clear();
                PostData(2, sort_time, sort_downloads, indext, 10);
                break;
        }
    }

    DataDownloadAdapter adapter = null;

    public void PostData(int category, int sort_time, int sort_downloads, int page, int pageSize) {
        GxJobRequest.rematerialList(category, sort_time, sort_downloads, page, pageSize, new HWSuccessListener() {
            @Override
            public void onRespone(String msg, Object response) {
                List<DataDownLoaBean> listDatas = (List<DataDownLoaBean>) response;
                for (int i = 0; i < listDatas.size(); i++) {
                    DataDownLoaBean bean = listDatas.get(i);
                    listData.add(bean);
                }
                if (adapter == null) {
                    adapter = new DataDownloadAdapter(Act_DataDownload.this, listData);
                    myListView.setAdapter(adapter);
                    adapter.setOnBackItem(Act_DataDownload.this);
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

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        indext=1;
        listData.clear();
        refreshLayout.finishRefresh();
        PostData(2, sort_time, sort_downloads, indext, 10);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        indext++;
        refreshLayout.finishLoadMore();
        PostData(2, sort_time, sort_downloads, indext, 10);
    }

    @Override
    public void backItem(int position) {
        Intent intent = new Intent();
        intent.putExtra("id", listData.get(position).getId());
        intent.setClass(this, Act_Download_details.class);
        startActivity(intent);
        Log.e("aa", "---点击了-----" + listData.get(position).getId());
    }
}

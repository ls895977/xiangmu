package cn.banband.gaoxinjiaoyu.model.careerquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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
import cn.banband.gaoxinjiaoyu.model.careerquiz.adapter.Career_QuizAdapter;
import cn.banband.gaoxinjiaoyu.model.datadownload.Act_DataDownload;
import cn.banband.gaoxinjiaoyu.model.datadownload.adapter.DataDownloadAdapter;
import cn.banband.global.activity.HWBaseActivity;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWSuccessListener;

public class Act_Career_Quiz extends HWBaseActivity implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener, Career_QuizAdapter.OnBackItem {
    public RelativeLayout l1, l2, l3;
    public TextView tv1, tv2, tv3;
    private ListView myListView;
    private int sort_time = 2, sort_downloads = 2;
    private SmartRefreshLayout refreshLayout1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addCommonHeader("职业测评");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_career_quiz);
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
        refreshLayout1 = findViewById(R.id.refresh_recruit_list);
        myListView = findViewById(R.id.lv_recruit_list);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        refreshLayout1.setOnRefreshListener(this);
        refreshLayout1.setOnLoadMoreListener(this);
    }

    @Override
    public void initData() {
        postData();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_DataDownload1://HR一级
                if (tv1.isSelected()) {
                    tv1.setSelected(false);
                } else {
                    tv1.setSelected(true);
                }
                break;
            case R.id.rl_DataDownload2://综合排序
                if (tv2.isSelected()) {
                    sort_time = 1;
                    tv2.setSelected(false);
                } else {
                    sort_time = 2;
                    tv2.setSelected(true);
                }
                break;
            case R.id.rl_DataDownload3://筛选
                if (tv3.isSelected()) {
                    sort_downloads = 1;
                    tv3.setSelected(false);
                } else {
                    sort_downloads = 2;
                    tv3.setSelected(true);
                }
                break;
        }
    }
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        listData.clear();
        page = 1;
        refreshLayout.finishRefresh();
        postData();
    }


    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        refreshLayout.finishLoadMore();
        postData();
    }


    int page = 1;
    List<CareerQuizBean> listData = new ArrayList<>();
    Career_QuizAdapter adapter = null;

    private void postData() {
        GxJobRequest.recruitjobtestLista("1", String.valueOf(page), new HWSuccessListener() {
            @Override
            public void onRespone(String msg, Object response) {
                List<CareerQuizBean> listDatas = (List<CareerQuizBean>) response;
                for (int i = 0; i < listDatas.size(); i++) {
                    CareerQuizBean bean = listDatas.get(i);
                    listData.add(bean);
                }
                if (adapter == null) {
                    adapter = new Career_QuizAdapter(Act_Career_Quiz.this, listData);
                    myListView.setAdapter(adapter);
                    adapter.setOnBackItem(Act_Career_Quiz.this);
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
    public void backItem(int position) {
        Log.e("aa", "---onRespone-----" + listData.get(position).getId());
    }
}

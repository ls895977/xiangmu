package cn.banband.gaoxinjiaoyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.adapter.JobRecruitListAdapter;
import cn.banband.gaoxinjiaoyu.http.GxJobRequest;
import cn.banband.gaoxinjiaoyu.model.GxRecruit;
import cn.banband.global.activity.HWBaseActivity;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWSuccessListener;
import cn.banband.global.utils.HWDialogUtils;


@EActivity(R.layout.activity_job_recruit_list)
public class JobRecruitListActivity extends HWBaseActivity implements OnRefreshListener,OnLoadMoreListener,JobRecruitListAdapter.ItemClickListener {

    @ViewById(R.id.refresh_recruit_list)
    public  SmartRefreshLayout refreshLayout;
    @ViewById(R.id.lv_recruit_list)
    public ListView listView;

    private int page=1;
    private boolean ismaxpage=false;
    private List<GxRecruit> recruitList;

    JobRecruitListAdapter listAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addCommonHeader("招聘列表");
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void init(){
        super.init();
    }

    @Override
    public void initSubViews() {
        listAdapter = new JobRecruitListAdapter(this);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void initData() {
        LoadData();
    }

    @Override
    public void initEvent() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        listAdapter.setItemClickListener(this);
    }

    public void LoadData() {
        if (refreshLayout.isRefreshing() == false) {
            HWDialogUtils.showLoadingSmallToast(this);
        }
        GxJobRequest.recruitList(page,10, new HWSuccessListener() {
            @Override
            public void onRespone(String msg, Object response) {
                List<GxRecruit> list = (List<GxRecruit>) response;

                if (page == 1) {
                    recruitList = list;
                    refreshLayout.finishRefresh();
                } else {
                    recruitList.addAll(list);
                    refreshLayout.finishLoadMore();
                }
                listAdapter.setRecruitList(recruitList);
                listAdapter.notifyDataSetChanged();
                if (list == null || list.size() == 0) {
                    ismaxpage = true;
                }
                HWDialogUtils.hideLoadingSmallToast();
            }
        }, new HWFailuredListener() {
            @Override
            public void onRespone(String msg, int errorCode) {
                HWDialogUtils.showToast(JobRecruitListActivity.this,msg);
                HWDialogUtils.hideLoadingSmallToast();
            }
        });
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (ismaxpage == false) {
            page++;
            LoadData();
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        ismaxpage = false;
        LoadData();
    }

    @Override
    public void onItemClickListener(GxRecruit recruit) {
        Intent intent = new Intent(this, JobRecruitDetailActivity_.class);
        intent.putExtra("id",recruit.getId());
        startActivity(intent);
    }
}

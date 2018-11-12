package cn.banband.gaoxinjiaoyu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.http.GxJobRequest;
import cn.banband.gaoxinjiaoyu.model.GxRecruit;
import cn.banband.global.activity.HWBaseActivity;
import cn.banband.global.dialog.HWConfirmDialog;
import cn.banband.global.http.HWFailuredListener;
import cn.banband.global.http.HWSuccessListener;
import cn.banband.global.utils.HWDialogUtils;

@EActivity(R.layout.activity_job_recruit_detail)
public class JobRecruitDetailActivity extends HWBaseActivity implements View.OnClickListener {

    @ViewById(R.id.textView3)
    public TextView textView;
    @ViewById(R.id.button)
    public Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addCommonHeader("招聘详情");
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void init(){
        super.init();
    }

    @Override
    public void initSubViews() {

    }

    @Override
    public void initData() {
        int id = getIntent().getIntExtra("id",0);
        GxJobRequest.recruitDetail(id, new HWSuccessListener() {
            @Override
            public void onRespone(String msg, Object response) {
                GxRecruit recruit = (GxRecruit)response;
                textView.setText(recruit.getCompany());
            }
        }, new HWFailuredListener() {
            @Override
            public void onRespone(String msg, int errorCode) {

            }
        });

    }

    @Override
    public void initEvent() {
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                HWDialogUtils.showConfirmDialog(this,"是否确认", "确认删除", new HWConfirmDialog.ConfirmDialogListener() {
                    @Override
                    public void OnConfirmOkClick(int actionType) {
                        HWDialogUtils.showToast(JobRecruitDetailActivity.this,"点了确认");
                    }
                },0);
                break;
        }
    }
}

package cn.banband.gaoxinjiaoyu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import cn.banband.gaoxinjiaoyu.activity.JobRecruitListActivity_;
import cn.banband.gaoxinjiaoyu.model.datadownload.Act_DataDownload;
import cn.banband.global.activity.HWBaseMainActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends HWBaseMainActivity implements View.OnClickListener {

    @ViewById(R.id.button2)
    public Button button;
    @ViewById(R.id.button3)
    public Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void init() {
        super.init();
    }

    @Override
    public void initSubViews() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Intent intent = new Intent(this, JobRecruitListActivity_.class);
                startActivity(intent);
                break;
            case R.id.button3://资料下载
                startActivity(new Intent(this, Act_DataDownload.class));
                break;
        }

    }


}

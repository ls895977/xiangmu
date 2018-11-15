package cn.banband.gaoxinjiaoyu.model.datadownload;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import cn.banband.gaoxinjiaoyu.R;

public class Act_Download_Search extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.act_download_search);
        initView();
    }

    public void initView() {
        findViewById(R.id.header_title_back_imgv).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_title_back_imgv:
                finish();
                break;
        }
    }
}

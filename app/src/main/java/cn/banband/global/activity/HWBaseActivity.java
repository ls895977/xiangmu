package cn.banband.global.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.banband.gaoxinjiaoyu.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

public abstract class HWBaseActivity extends Activity {
    private onRight onRight;
    TextView txtv_title;
    ImageView backImgv, Img_right;
    private RelativeLayout rltitle;
    boolean commonHeaderEnabled = false;
    String title;
    int imge = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (commonHeaderEnabled) {
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        }
    }

    public void init() {
        if (commonHeaderEnabled) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header_common);
            txtv_title = findViewById(R.id.header_title_txtv);
            rltitle = findViewById(R.id.header_title_title);
            backImgv = findViewById(R.id.header_title_back_imgv);
            Img_right = findViewById(R.id.header_title_right);
            txtv_title.setText(title);
            backImgv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HWBaseActivity.this.finish();
                }
            });
        }
        if (imge != 0) {
            Img_right.setImageResource(imge);
            Img_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRight.onRightBt();
                }
            });
        }
        initSubViews();
        initEvent();
        initData();
    }


    abstract public void initSubViews();

    abstract public void initData();

    abstract public void initEvent();

    public void addCommonHeader(String title) {
        commonHeaderEnabled = true;
        this.title = title;
    }

    public void addCommonHeader(String title, int imge, onRight onRight1) {
        this.onRight = onRight1;
        this.imge = imge;
        commonHeaderEnabled = true;
        this.title = title;
    }

    public interface onRight {
        void onRightBt();
    }

    public void hid() {
        rltitle.setVisibility(View.GONE);
    }
}

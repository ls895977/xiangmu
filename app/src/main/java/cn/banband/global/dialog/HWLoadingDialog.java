package cn.banband.global.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.banband.gaoxinjiaoyu.R;

public class HWLoadingDialog extends Dialog {
    private TextView tv;
    public String mTitle;

    public HWLoadingDialog(Context context , String title) {
        super(context, R.style.loadingDialogStyle);
        mTitle = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        tv = (TextView)findViewById(R.id.tv);
        if (mTitle == null) {
            mTitle = "正在加载数据, 请稍后";
        }
        tv.setText(mTitle);
        LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }
}
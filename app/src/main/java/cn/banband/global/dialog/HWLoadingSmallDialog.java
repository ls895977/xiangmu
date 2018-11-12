package cn.banband.global.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import cn.banband.gaoxinjiaoyu.R;

public class HWLoadingSmallDialog extends Dialog {
    ImageView imageView;

    public HWLoadingSmallDialog(@NonNull Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_small);
        imageView = (ImageView)findViewById(R.id.loading_imgv);
        Animation operatingAnim = AnimationUtils.loadAnimation(getContext(), R.anim.loading_small_rotate);
        imageView.setAnimation(operatingAnim);
    }

    @Override
    protected void onStop() {
        super.onStop();
        imageView.clearAnimation();
    }
}

package cn.banband.global.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class HWBaseMainActivity extends HWBaseActivity {
    private static HWBaseMainActivity mainActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
    }

    public static HWBaseMainActivity getMainActivity() {
        return mainActivity;
    }
}

package cn.banband.global.fragment;

import android.app.Fragment;
import android.view.View;

public abstract class HWBaseFragment extends Fragment {

    public void init(View view){
        initSubView(view);
        initEvent();
        initData();
    }

    public abstract void initSubView(View view);
    public abstract void initData();
    public abstract void initEvent();
}

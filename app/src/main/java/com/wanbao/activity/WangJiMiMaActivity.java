package com.wanbao.activity;

import android.os.Bundle;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.tools.SetTitleColor;

public class WangJiMiMaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetTitleColor.FlymeSetStatusBarLightMode(getWindow(),false);
        setContentView(R.layout.activity_wang_ji_mi_ma);
    }
}

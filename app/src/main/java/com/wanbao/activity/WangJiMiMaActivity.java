package com.wanbao.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.tools.SetTitleColor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WangJiMiMaActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.viewBar)
    LinearLayout viewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        SetTitleColor.FlymeSetStatusBarLightMode(getWindow(), false);
        setContentView(R.layout.activity_wang_ji_mi_ma);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }
}

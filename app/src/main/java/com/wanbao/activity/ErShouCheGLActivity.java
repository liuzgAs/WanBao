package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.fragment.ErShouCheGLFragment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Seller_CarManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class ErShouCheGLActivity extends BaseActivity {

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private Seller_CarManage pay_new_pay;
    private int currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shou_che_gl);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        titleText.setText("二手车管理");
        imageRight.setImageResource(R.mipmap.acda_zhengjian);
        imageRight.setVisibility(View.VISIBLE);
    }
    @Override
    protected void initData() {
        getOrder();

    }

    @OnClick({R.id.imageback, R.id.imageRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.imageRight:
                Intent intent=new Intent(context,ErShouCheYYActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    private void getOrder() {
        HttpApi.post(context, getOkObjectOrder(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                try {
                    LogUtils.e("WeiBaoDDActivity", s);
                    pay_new_pay = GsonUtils.parseJSON(s, Seller_CarManage.class);
                    if (pay_new_pay.getStatus() == 1) {
                        if (pay_new_pay.getStateType().size()>0){
                            for (int i=0;i<pay_new_pay.getStateType().size();i++){
                                list.add(String.valueOf(pay_new_pay.getStateType().get(i).getName()));
                            }
                        }
                        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
                        tablayout.setupWithViewPager(viewPager);
                        viewPager.setOffscreenPageLimit(4);
                        tablayout.removeAllTabs();
                        for (int i = 0; i < list.size(); i++) {
                            View view = LayoutInflater.from(context).inflate(R.layout.item_tablayout, null);
                            TextView textTitle = view.findViewById(R.id.textTitle);
                            textTitle.setText(list.get(i));
                            if (i == currentItem) {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
                            } else {
                                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
                            }
                        }
                    } else {
                        ToastUtils.showShort(pay_new_pay.getInfo());
                    }
                } catch (Exception e) {
                    Toast.makeText(context,"数据异常",Toast.LENGTH_SHORT).show();
                    dismissDialog();
                }
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常");
                dismissDialog();
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }

    private OkObject getOkObjectOrder() {
        String url = Constant.HOST + Constant.Url.Seller_CarManage;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("state", "0");
        params.put("p", "1");
        return new OkObject(params, url);
    }
    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ErShouCheGLFragment.newInstance(String.valueOf(pay_new_pay.getStateType().get(position).getState()));
        }

        @Override
        public int getCount() {
            return pay_new_pay.getStateType().size();
        }
    }
}

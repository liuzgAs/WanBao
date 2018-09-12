package com.wanbao.activity;

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
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.fragment.WeiBaoDDFragment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Maintain_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WeiBaoDDActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private  User_Maintain_order pay_new_pay;
    private int currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bao_dd);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        currentItem=getIntent().getIntExtra("currentItem",0);
    }

    @Override
    protected void initViews() {
        titleText.setText("维保订单");
        getOrder();
    }

    @Override
    protected void initData() {
    }
    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.PaySureOrder.equals(event.getAction())) {
            viewPager.setCurrentItem(4,false);
        }
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }
    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
                    return WeiBaoDDFragment.newInstance(pay_new_pay.getType().get(position).getV());

        }

        @Override
        public int getCount() {
            return pay_new_pay.getType().size();
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
                    pay_new_pay = GsonUtils.parseJSON(s, User_Maintain_order.class);
                    if (pay_new_pay.getStatus() == 1) {
                        if (pay_new_pay.getType().size()>0){
                            for (int i=0;i<pay_new_pay.getType().size();i++){
                                list.add(pay_new_pay.getType().get(i).getN());
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
        String url = Constant.HOST + Constant.Url.User_Maintain_order;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("state", "");
        return new OkObject(params, url);
    }
}

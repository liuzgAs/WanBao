package com.wanbao.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.GradientTextView;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Censor;
import com.wanbao.ui.CircleImageView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class TiYanZhongXinActivity extends BaseActivity {

    @BindView(R.id.imageBack)
    ImageView imageBack;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewBYSC)
    LinearLayout viewBYSC;
    @BindView(R.id.imageFenShu)
    ImageView imageFenShu;
    @BindView(R.id.imageHeader)
    CircleImageView imageHeader;
    @BindView(R.id.textCarName)
    TextView textCarName;
    @BindView(R.id.textCarNo)
    TextView textCarNo;
    @BindView(R.id.imageFx)
    ImageView imageFx;
    @BindView(R.id.textPjhy)
    TextView textPjhy;
    @BindView(R.id.textXh)
    TextView textXh;
    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.textLc)
    TextView textLc;
    @BindView(R.id.textScbysj)
    TextView textScbysj;
    @BindView(R.id.textScbylc)
    TextView textScbylc;
    @BindView(R.id.textJlxcby)
    TextView textJlxcby;
    @BindView(R.id.textJlxcbys)
    TextView textJlxcbys;
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    @BindView(R.id.textFenShu)
    GradientTextView textFenShu;
    @BindView(R.id.viewCgby)
    LinearLayout viewCgby;
    @BindView(R.id.viewWxjl)
    LinearLayout viewWxjl;
    private AnimationDrawable anim;
    int[] images = {R.mipmap.yuanhu0, R.mipmap.yuanhu1, R.mipmap.yuanhu2, R.mipmap.yuanhu3, R.mipmap.yuanhu4, R.mipmap.yuanhu5, R.mipmap.yuanhu6, R.mipmap.yuanhu7, R.mipmap.yuanhu8, R.mipmap.yuanhu9
            , R.mipmap.yuanhu10, R.mipmap.yuanhu11, R.mipmap.yuanhu12, R.mipmap.yuanhu13, R.mipmap.yuanhu14, R.mipmap.yuanhu15, R.mipmap.yuanhu16, R.mipmap.yuanhu17, R.mipmap.yuanhu18
            , R.mipmap.yuanhu19, R.mipmap.yuanhu20, R.mipmap.yuanhu21, R.mipmap.yuanhu22};
    private String id;
    private MySumAdapter mySumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_yan_zhong_xin);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData() {
        getCensor();
    }

    @OnClick({R.id.imageBack, R.id.viewBYSC, R.id.viewCgby, R.id.viewWxjl})
    public void onViewClicked(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            case R.id.viewBYSC:
                intent = new Intent();
                intent.putExtra("pos",0);
                intent.setClass(context, BaoYangChouCeActivity.class);
                startActivity(intent);
                break;
            case R.id.viewCgby:
                intent = new Intent();
                intent.putExtra("pos",1);
                intent.setClass(context, BaoYangChouCeActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWxjl:
                intent = new Intent();
                intent.putExtra("pos",2);
                intent.setClass(context, BaoYangChouCeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getCensor() {
        HttpApi.post(context, getOkObjectCensor(), new HttpApi.CallBack() {
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
                Log.e("Usercar_Censor", s);
                try {
                    final Usercar_Censor uCensor = GsonUtils.parseJSON(s, Usercar_Censor.class);
                    if (uCensor.getStatus() == 1) {
                        GlideApp.with(context)
                                .asBitmap()
                                .load(uCensor.getData().getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHeader);
                        textCarName.setText(uCensor.getData().getCar_name());
                        textCarNo.setText(uCensor.getData().getCar_no());
                        textFenShu.setText(uCensor.getVal() + "");
                        textPjhy.setText(uCensor.getVal1() + "");
                        textXh.setText(uCensor.getVal2() + "");
                        textState.setText(uCensor.getVal3() + "");
                        textLc.setText(uCensor.getData().getKm() + "");
                        textScbysj.setText(uCensor.getKmDes().get(0));
                        textScbylc.setText(uCensor.getKmDes().get(1));
                        textJlxcby.setText(uCensor.getKmDes().get(2));
                        textJlxcbys.setText(uCensor.getKmDes().get(3));
                        mySumAdapter = new MySumAdapter(uCensor);
                        listView.setAdapter(mySumAdapter);
                        setAnim(Math.round(uCensor.getVal()*23 /100));

                    } else {
                        ToastUtils.showShort(uCensor.getInfo());
                    }

                } catch (Exception e) {
                    ToastUtils.showShort("数据出错");

                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常！");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }

    private OkObject getOkObjectCensor() {
        String url = Constant.HOST + Constant.Url.Usercar_Censor;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }


    class MySumAdapter extends BaseAdapter {

        private Usercar_Censor dataBean;

        class ViewHolder {
            public TextView textTitle;
            public TextView textDes;
        }

        public MySumAdapter(Usercar_Censor dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getDes().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_tyzx, null);
                holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
                holder.textDes = (TextView) convertView.findViewById(R.id.textDes);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textTitle.setText(dataBean.getDes().get(position).getN());
            holder.textDes.setText(dataBean.getDes().get(position).getV());
            return convertView;
        }
    }

    private void setAnim(int p) {
        anim = new AnimationDrawable();
        for (int i = 0; i < p; i++) {
            anim.addFrame(getResources().getDrawable(images[i]), 80);
        }
        // 设置为循环播放
        anim.setOneShot(true);

        // 设置ImageView的背景为AnimationDrawable
        imageFenShu.setBackground(anim);
        if (anim != null && !anim.isRunning()) {
            anim.start();
        }
    }
}

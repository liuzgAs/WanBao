package com.wanbao.fragment;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.R;
import com.wanbao.activity.LoginActivity;
import com.wanbao.activity.WebViewActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Money_Share;
import com.wanbao.modle.OkObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FenXiangZCFragment extends PSFragment {
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    @BindView(R.id.textGz)
    TextView textGz;
    @BindView(R.id.viewGz)
    LinearLayout viewGz;
    @BindView(R.id.viewWx)
    LinearLayout viewWx;
    @BindView(R.id.viewPyq)
    LinearLayout viewPyq;
    @BindView(R.id.viewEwm)
    LinearLayout viewEwm;
    Unbinder unbinder;
    private View view;
    private MyAdapter myAdapter;
    private Money_Share mShare;
    private IWXAPI api;

    public FenXiangZCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_fen_xiang_zc, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        api= WXAPIFactory.createWXAPI(context,Constant.WXAPPID,true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onEventMainThread(BaseEvent event) {
        if (event.getAction().equals(BaseEvent.Change_Data)) {
            fetchData();
        }
    }

    private void Share() {
        HttpApi.post(context, getOkObjectShare(), new HttpApi.CallBack() {
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
                    mShare = GsonUtils.parseJSON(s, Money_Share.class);
                    int status = mShare.getStatus();
                    if (status == 1) {
                        myAdapter=new MyAdapter(mShare);
                        textTitle.setText(mShare.getTiele());
                        textGz.setText(mShare.getUrl_title()+">>");
                        textGz.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        listView.setAdapter(myAdapter);
                    } else {
                        ToastUtils.showShort(mShare.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }

    private OkObject getOkObjectShare() {
        String url = Constant.HOST + Constant.Url.Money_Share;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    @OnClick({R.id.viewGz, R.id.viewWx, R.id.viewPyq, R.id.viewEwm})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.viewGz:
                if (mShare==null){
                    return;
                }
                intent=new Intent();
                intent.putExtra("title",mShare.getUrl_title());
                intent.putExtra("mUrl",mShare.getUrl());
                intent.setClass(context, WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWx:
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if (mShare==null){
                    return;
                }
                MyDialog.wxShare1(context,api,0,mShare.getShare().getShareUrl(),mShare.getShare().getShareImg(),
                        mShare.getShare().getShareTitle(),mShare.getShare().getShareDes());
                break;
            case R.id.viewPyq:
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if (mShare==null){
                    return;
                }
                MyDialog.wxShare1(context,api,1,mShare.getShare().getShareUrl(),mShare.getShare().getShareImg(),
                        mShare.getShare().getShareTitle(),mShare.getShare().getShareDes());
                break;
            case R.id.viewEwm:
                intent=new Intent();
                if (SPUtils.getInstance().getInt(Constant.SF.Uid, 0) == 0) {
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if (mShare==null){
                    return;
                }
                MyDialog.wxShareTP(context,api,0,mShare.getEwm_url());
                break;
            default:
                break;
        }
    }

    @Override
    public void fetchData() {
        Share();
    }

    class MyAdapter extends BaseAdapter {

        private Money_Share dataBean;

        class ViewHolder {
            public TextView textDes;
        }

        public MyAdapter(Money_Share dataBean) {
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
           MyAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new MyAdapter.ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_des, null);
                holder.textDes = (TextView) convertView.findViewById(R.id.textDes);
                convertView.setTag(holder);
            } else {
                holder = (MyAdapter.ViewHolder) convertView.getTag();
            }
            holder.textDes.setText(dataBean.getDes().get(position));
            return convertView;
        }
    }


}

package com.wanbao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.ui.MenuGridView;
import com.wanbao.base.ui.StateButton;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Maintain_order_info;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class XinCheBDFragment extends PSFragment {


    @BindView(R.id.textState)
    TextView textState;
    @BindView(R.id.viewState)
    LinearLayout viewState;
    @BindView(R.id.textCzxm)
    TextView textCzxm;
    @BindView(R.id.textDh)
    TextView textDh;
    @BindView(R.id.textZjhm)
    TextView textZjhm;
    @BindView(R.id.textCx)
    TextView textCx;
    @BindView(R.id.textVhm)
    TextView textVhm;
    @BindView(R.id.textFdjh)
    TextView textFdjh;
    @BindView(R.id.textJcrq)
    TextView textJcrq;
    @BindView(R.id.textXsgw)
    TextView textXsgw;
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    Unbinder unbinder;
    @BindView(R.id.sbtn_tijiao)
    StateButton sbtnTijiao;
    private View view;

    public static XinCheBDFragment newInstance() {
        XinCheBDFragment sf = new XinCheBDFragment();
        return sf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_xin_che_bd, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dispose();
    }

    @Override
    public void fetchData() {
        getNewcar();
    }

    private void getNewcar() {
        HttpApi.post(context, getOkObjectNewcar(), new HttpApi.CallBack() {
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
                LogUtils.e("Usernewcar_Newcar", s);
                try {
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

    private OkObject getOkObjectNewcar() {
        String url = Constant.HOST + Constant.Url.Usernewcar_Newcar;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    @OnClick(R.id.sbtn_tijiao)
    public void onViewClicked() {
    }

    class MyAdapter extends BaseAdapter {

        private User_Maintain_order_info.DataBean dataBean;
        private int type;

        class ViewHolder {
            public TextView textTitle;
            public MenuGridView gridView;
        }

        public MyAdapter(User_Maintain_order_info.DataBean dataBean, int type) {
            this.dataBean = dataBean;
            this.type = type;
        }

        @Override
        public int getCount() {
            if (type == 0) {
                return dataBean.getBag_des().size();
            } else {
                return dataBean.getDes().size();
            }
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
                convertView = getLayoutInflater().inflate(R.layout.item_tjxc, null);
                holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
                holder.gridView = (MenuGridView) convertView.findViewById(R.id.gridView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }

    class MyGAdapter extends BaseAdapter {

        private User_Maintain_order_info.DataBean dataBean;
        private int type;

        class ViewHolder {
            public CheckBox checkbox;
        }

        public MyGAdapter(User_Maintain_order_info.DataBean dataBean, int type) {
            this.dataBean = dataBean;
            this.type = type;
        }

        @Override
        public int getCount() {
            if (type == 0) {
                return dataBean.getBag_des().size();
            } else {
                return dataBean.getDes().size();
            }
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
                convertView = getLayoutInflater().inflate(R.layout.item_xcxm, null);
                holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }
}
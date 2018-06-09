package com.wanbao.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.MenuGridView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.User_Interest;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XingQuBQActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.gridView0)
    MenuGridView gridView0;
    @BindView(R.id.gridView1)
    MenuGridView gridView1;
    private MyGAdapter myGAdapter0;
    private MyGAdapter myGAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xing_qu_bq);
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
        titleText.setText("兴趣标签");
    }

    @Override
    protected void initData() {
        getInterest();
    }

    private void getInterest() {
        HttpApi.post(context, getOkObInterest(), new HttpApi.CallBack() {
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
                LogUtils.e("User_Interest", s);
                try {
                    User_Interest uInterest = GsonUtils.parseJSON(s, User_Interest.class);
                    if (uInterest.getStatus() == 1) {
                        myGAdapter0=new MyGAdapter(uInterest,0);
                        myGAdapter1=new MyGAdapter(uInterest,1);
                        gridView0.setAdapter(myGAdapter0);
                        gridView1.setAdapter(myGAdapter1);
                    } else {
                        ToastUtils.showShort(String.valueOf(uInterest.getInfo()));
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

    private OkObject getOkObInterest() {
        String url = Constant.HOST + Constant.Url.User_Interest;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        return new OkObject(params, url);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    class MyGAdapter extends BaseAdapter {

        private User_Interest dataBean;
        private int type;

        class ViewHolder {
            public TextView textBiaoq;
            public ImageView imageDelete;

        }

        public MyGAdapter(User_Interest dataBean, int type) {
            this.dataBean = dataBean;
            this.type = type;
        }

        @Override
        public int getCount() {
            if (type==0){
                return dataBean.getData().size();
            }else {
                return dataBean.getTag().size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_biaoq, null);
                holder.textBiaoq = (TextView) convertView.findViewById(R.id.textBiaoq);
                holder.imageDelete = (ImageView) convertView.findViewById(R.id.imageDelete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (type == 0) {
                holder.imageDelete.setVisibility(View.VISIBLE);
                holder.textBiaoq.setText(dataBean.getData().get(position).getName());
            } else {
                holder.imageDelete.setVisibility(View.INVISIBLE);
                holder.textBiaoq.setText(dataBean.getTag().get(position).getName());
            }

            holder.imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type==0){
                        getInterest_del(String.valueOf(dataBean.getData().get(position).getId()));
                    }
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type!=0){
                        getInterest_save(String.valueOf(dataBean.getTag().get(position).getId()));
                    }
                }
            });
            return convertView;
        }
    }

    private void getInterest_del(String item_id) {
        HttpApi.post(context, getOkObInterest_del(item_id), new HttpApi.CallBack() {
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
                LogUtils.e("User_Interest", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        initData();
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeXx,null));
                    } else {
                        ToastUtils.showShort(String.valueOf(comment.getInfo()));
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

    private OkObject getOkObInterest_del(String item_id) {
        String url = Constant.HOST + Constant.Url.User_Interest_del;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("item_id", item_id);
        return new OkObject(params, url);
    }

    private void getInterest_save(String item_id) {
        HttpApi.post(context, getOkObInterest_save(item_id), new HttpApi.CallBack() {
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
                LogUtils.e("User_Interest", s);
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    if (comment.getStatus() == 1) {
                        initData();
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeXx,null));
                    } else {
                        ToastUtils.showShort(String.valueOf(comment.getInfo()));
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

    private OkObject getOkObInterest_save(String item_id) {
        String url = Constant.HOST + Constant.Url.User_Interest_save;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("item_id", item_id);
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}

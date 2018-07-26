package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.activity.TuiJianJLActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Comment;
import com.wanbao.modle.Money_Recom_Log;
import com.wanbao.modle.OkObject;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class TuiJianJLViewHolder extends BaseViewHolder<Money_Recom_Log.DataBean> {
    private final TextView textPhone;
    private final TextView text1;
    private final TextView text2;
    private final TextView btnText1;
    private final TextView btnText2;
    private CompositeDisposable compositeDisposable;
    private TuiJianJLActivity activity;
    public TuiJianJLViewHolder(ViewGroup parent, @LayoutRes int res,TuiJianJLActivity activity) {
        super(parent, res);
        this.activity=activity;
        textPhone = $(R.id.textPhone);
        text1 = $(R.id.text1);
        text2 = $(R.id.text2);
        btnText1 = $(R.id.btnText1);
        btnText2 = $(R.id.btnText2);
    }

    @Override
    public void setData(final Money_Recom_Log.DataBean data) {
        super.setData(data);
        textPhone.setText(data.getPhone());
        if (data.getBtnType1()==1){
            btnText1.setVisibility(View.VISIBLE);
            text1.setVisibility(View.GONE);
            btnText1.setText(data.getBtnText1());
        }else {
            btnText1.setVisibility(View.GONE);
            text1.setVisibility(View.VISIBLE);
            btnText1.setText(data.getText1());
        }
        if (data.getBtnType2()==1){
            btnText2.setVisibility(View.VISIBLE);
            text2.setVisibility(View.GONE);
            btnText2.setText(data.getBtnText1());
        }else {
            btnText2.setVisibility(View.GONE);
            text2.setVisibility(View.VISIBLE);
            btnText2.setText(data.getText1());
        }
        btnText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money_Act1(String.valueOf(data.getId()));
            }
        });
        btnText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money_Act2(String.valueOf(data.getId()));
            }
        });
    }

    private void money_Act1(String id) {

        HttpApi.post(activity, getOkObjectMoney_Act1(id), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                activity.showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                activity.dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        ToastUtils.showShort("邀请成功！");
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.TuiJianJL, null));
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                activity.dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                activity.dismissDialog();
                dispose();
            }


        });
    }

    private OkObject getOkObjectMoney_Act1(String id) {
        String url = Constant.HOST + Constant.Url.Money_Act1;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return new OkObject(params, url);
    }

    private void money_Act2(String id) {
        HttpApi.post(activity, getOkObjectMoney_Act2(id), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                activity.showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                activity.dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        ToastUtils.showShort("通知成功！");
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.TuiJianJL, null));
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                activity.dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                activity.dismissDialog();
                dispose();
            }


        });
    }

    private OkObject getOkObjectMoney_Act2(String id) {
        String url = Constant.HOST + Constant.Url.Money_Act2;
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return new OkObject(params, url);
    }

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

}

package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.R;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.fragment.CanTuanJLFragment;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Orderteam_Joinlog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class CanTuanJLViewHolder extends BaseViewHolder<Orderteam_Joinlog.DataBean> {
    private TextView textStoreName;
    private TextView textState;
    private TextView textCarNo;
    private TextView textCarName;
    private TextView textBookTime;
    private TextView textOrder_amount;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private CompositeDisposable compositeDisposable;
    private CanTuanJLFragment fragment;

    public CanTuanJLViewHolder(ViewGroup parent, @LayoutRes int res, CanTuanJLFragment fragment) {
        super(parent, res);
        this.fragment = fragment;
        textStoreName = $(R.id.textStoreName);
        textState = $(R.id.textState);
        textCarNo = $(R.id.textCarNo);
        textCarName = $(R.id.textCarName);
        textBookTime = $(R.id.textBookTime);
        textOrder_amount = $(R.id.textOrder_amount);
        btn0 = $(R.id.btn0);
        btn1 = $(R.id.btn1);
        btn2 = $(R.id.btn2);
    }

    @Override
    public void setData(final Orderteam_Joinlog.DataBean data) {
        super.setData(data);
        textStoreName.setText(data.getStore_name());
        textState.setText(data.getStateDes());
        textCarNo.setText(data.getCar_no());
        textCarName.setText(data.getCar_name());
        textBookTime.setText(data.getBook_time());
        textOrder_amount.setText(data.getOrder_amount());
        btn1.setVisibility(View.GONE);
        btn0.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        if (data.getIsShare() == 1) {
            btn1.setText("邀请参团");
            btn1.setVisibility(View.VISIBLE);
        }

        if (data.getIsCancel() == 1) {
            btn0.setText("取消拼团");
            btn0.setVisibility(View.VISIBLE);
        }
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancleCreate(data);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.share02(getContext(), WXAPIFactory.createWXAPI(getContext(), Constant.WXAPPID,true),data.getShare().getShareUrl(),data.getShare().getShareTitle(),data.getShare().getShareDes(),"");
            }
        });
    }


    private void CancleCreate(final Orderteam_Joinlog.DataBean data) {
        HttpApi.post(fragment.context, getOkObjectCancleCreate(data), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                fragment.showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                fragment.dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ChangeCTOrder, null));
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                fragment.dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                fragment.dismissDialog();
                dispose();
            }


        });
    }

    private OkObject getOkObjectCancleCreate(Orderteam_Joinlog.DataBean data) {
        String url = Constant.HOST + Constant.Url.Orderteam_CancleJoin;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", String.valueOf(data.getId()));
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

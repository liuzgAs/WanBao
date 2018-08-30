package com.wanbao.viewholder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.LiJiPPActivity;
import com.wanbao.activity.LiJiZhiFuActivity;
import com.wanbao.activity.XuanZheCheXSJActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.fragment.ShiJiaDDFragment;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Testdrive_TestOrder;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ShiJiaDDViewHolder extends BaseViewHolder<Testdrive_TestOrder.DataBean> {
    private TextView textStoreName;
    private TextView textState;
    private TextView textCarNo;
    private TextView textCarName;
    private TextView textBookTime;
    private TextView textOrder_amount;
    private ImageView imageCar;

    private Button btn0;
    private Button btn1;
    private Testdrive_TestOrder.DataBean data;
    private ShiJiaDDFragment fragment;
    private CompositeDisposable compositeDisposable;

    public ShiJiaDDViewHolder(ViewGroup parent, @LayoutRes int res,ShiJiaDDFragment fragment) {
        super(parent, res);
        this.fragment=fragment;
        textStoreName = $(R.id.textStoreName);
        textState = $(R.id.textState);
        textCarNo = $(R.id.textCarNo);
        textCarName = $(R.id.textCarName);
        textBookTime = $(R.id.textBookTime);
        textOrder_amount = $(R.id.textOrder_amount);
        imageCar = $(R.id.imageCar);
        btn0 = $(R.id.btn0);
        btn1 = $(R.id.btn1);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getIsDel() == 1) {
                    setState(BaseEvent.Del_Order,String.valueOf(data.getId()));
                } else if (data.getIsCancel() == 1) {
                    setState(BaseEvent.Cancle_order,String.valueOf(data.getId()));
                }else if (data.getIsRefund() == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("提示")
                            .setMessage("您确认要取消订单并申请退款吗！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    setState(BaseEvent.IsRefund,String.valueOf(data.getId()));
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getGoPay() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("paytype",1);
                    intent.putExtra("Oid", String.valueOf(data.getId()));
                    intent.setClass(getContext(), LiJiZhiFuActivity.class);
                    getContext().startActivity(intent);
                } else if (data.getIsConfirm() == 1) {
                    setState(BaseEvent.Is_Confirm,String.valueOf(data.getId()));
                } else if (data.getIsEvaluate() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("id", String.valueOf(data.getId()));
                    intent.putExtra("type",1);
                    intent.setClass(getContext(), LiJiPPActivity.class);
                    getContext().startActivity(intent);
                } else if (data.getIsAgain() == 1) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), XuanZheCheXSJActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public void setData(final Testdrive_TestOrder.DataBean data) {
        super.setData(data);
        this.data=data;
        textStoreName.setText(data.getStore_name());
        textState.setText(data.getStateDes());
        textCarNo.setText(data.getCar_name());
        textCarName.setText(data.getBook_time());
        textBookTime.setText(data.getOrder_amount());
        btn1.setVisibility(View.GONE);
        btn0.setVisibility(View.GONE);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getCar_img())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
        if (data.getGoPay() == 1) {
            btn1.setText("立即付款");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsConfirm() == 1) {
            btn1.setText("确认订单");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsEvaluate() == 1) {
            btn1.setText("去评价");
            btn1.setVisibility(View.VISIBLE);
        } else if (data.getIsAgain() == 1) {
            btn1.setText("再来一单");
            btn1.setVisibility(View.VISIBLE);
        }

        if (data.getIsDel() == 1) {
            btn0.setText("删除订单");
            btn0.setVisibility(View.VISIBLE);
        } else if (data.getIsCancel() == 1) {
            btn0.setText("取消订单");
            btn0.setVisibility(View.VISIBLE);
        }else if (data.getIsRefund() == 1) {
            btn0.setText("申请退款");
            btn0.setVisibility(View.VISIBLE);
        }
    }

    private void setState(String even, String id) {
        HttpApi.post(fragment.context, getOkObjectState(even, id), new HttpApi.CallBack() {
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
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.Change_SjOrder, null));
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

    private OkObject getOkObjectState(String even, String id) {
        String url = "";
        if (even.equals(BaseEvent.Cancle_order)) {
            url = Constant.HOST + Constant.Url.User_CancelOrder;
        } else if (even.equals(BaseEvent.Del_Order)) {
            url = Constant.HOST + Constant.Url.User_DelOrder;
        } else if (even.equals(BaseEvent.Is_Confirm)) {
            url = Constant.HOST + Constant.Url.User_ConfirmOrder;
        }else if (even.equals(BaseEvent.IsRefund)) {
            url = Constant.HOST + Constant.Url.User_Refund_order;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
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

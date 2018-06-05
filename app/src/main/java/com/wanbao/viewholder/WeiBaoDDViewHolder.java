package com.wanbao.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.activity.LiJiPPActivity;
import com.wanbao.activity.LiJiZhiFuActivity;
import com.wanbao.activity.WeiXiuBYActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.modle.User_Maintain_order;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class WeiBaoDDViewHolder extends BaseViewHolder<User_Maintain_order.DataBean> {
    private TextView textStoreName;
    private TextView textState;
    private TextView textCarNo;
    private TextView textCarName;
    private TextView textBookTime;
    private TextView textOrder_amount;
    private Button btn0;
    private Button btn1;
    private User_Maintain_order.DataBean data;

    public WeiBaoDDViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textStoreName = $(R.id.textStoreName);
        textState = $(R.id.textState);
        textCarNo = $(R.id.textCarNo);
        textCarName = $(R.id.textCarName);
        textBookTime = $(R.id.textBookTime);
        textOrder_amount = $(R.id.textOrder_amount);
        btn0 = $(R.id.btn0);
        btn1 = $(R.id.btn1);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getIsDel() == 1) {
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Del_Order, data.getId()));
                } else if (data.getIsCancel() == 1) {
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Cancle_order, data.getId()));
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getGoPay() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("Oid", String.valueOf(data.getId()));
                    intent.setClass(getContext(), LiJiZhiFuActivity.class);
                    getContext().startActivity(intent);
                } else if (data.getIsConfirm() == 1) {
                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Is_Confirm, data.getId()));
                } else if (data.getIsEvaluate() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("Oid", String.valueOf(data.getId()));
                    intent.setClass(getContext(), LiJiPPActivity.class);
                    getContext().startActivity(intent);
                } else if (data.getIsAgain() == 1) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), WeiXiuBYActivity.class);
                    getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public void setData(final User_Maintain_order.DataBean data) {
        super.setData(data);
        this.data=data;
        textStoreName.setText(data.getStore_name());
        textState.setText(data.getStateDes());
        textCarNo.setText(data.getCar_no());
        textCarName.setText(data.getCar_name());
        textBookTime.setText(data.getBook_time());
        textOrder_amount.setText(data.getOrder_amount());
        btn1.setVisibility(View.GONE);
        btn0.setVisibility(View.GONE);
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
        }
    }

}

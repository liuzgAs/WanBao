package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Testdrive_TestOrder;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ShiJiaDDViewHolder extends BaseViewHolder<Testdrive_TestOrder.InfoBean> {
    private TextView textStoreName;
    private TextView textState;
    private TextView textCarNo;
    private TextView textCarName;
    private TextView textBookTime;
    private TextView textOrder_amount;
    private ImageView imageCar;

    private Button btn0;
    private Button btn1;
    private Testdrive_TestOrder.InfoBean data;
    private String state;

    public ShiJiaDDViewHolder(ViewGroup parent, @LayoutRes int res,String state) {
        super(parent, res);
        this.state=state;
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
//                if (data.getIsDel() == 1) {
//                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Del_Order, data.getId()));
//                } else if (data.getIsCancel() == 1) {
//                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Cancle_order, data.getId()));
//                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (data.getGoPay() == 1) {
//                    Intent intent = new Intent();
//                    intent.putExtra("Oid", String.valueOf(data.getId()));
//                    intent.setClass(getContext(), LiJiZhiFuActivity.class);
//                    getContext().startActivity(intent);
//                } else if (data.getIsConfirm() == 1) {
//                    EventBus.getDefault().post(new BaseEvent(BaseEvent.Is_Confirm, data.getId()));
//                } else if (data.getIsEvaluate() == 1) {
//                    Intent intent = new Intent();
//                    intent.putExtra("Oid", String.valueOf(data.getId()));
//                    intent.setClass(getContext(), LiJiPPActivity.class);
//                    getContext().startActivity(intent);
//                } else if (data.getIsAgain() == 1) {
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), WeiXiuBYActivity.class);
//                    getContext().startActivity(intent);
//                }
            }
        });
    }

    @Override
    public void setData(final Testdrive_TestOrder.InfoBean data) {
        super.setData(data);
        this.data=data;
        textStoreName.setText(data.getStore());
//        textState.setText(data.getStateDes());
        textCarNo.setText(data.getTitle());
        textCarName.setText("试驾时间："+data.getBook_time());
        textBookTime.setText("¥"+data.getAmount());
//        textOrder_amount.setText(data.getOrder_amount());
        btn1.setVisibility(View.GONE);
        btn0.setVisibility(View.GONE);
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getThumb())
                .placeholder(R.mipmap.ic_empty)
                .into(imageCar);
//        if (data.getGoPay() == 1) {
//            btn1.setText("立即付款");
//            btn1.setVisibility(View.VISIBLE);
//        } else if (data.getIsConfirm() == 1) {
//            btn1.setText("确认订单");
//            btn1.setVisibility(View.VISIBLE);
//        } else if (data.getIsEvaluate() == 1) {
//            btn1.setText("去评价");
//            btn1.setVisibility(View.VISIBLE);
//        } else if (data.getIsAgain() == 1) {
//            btn1.setText("再来一单");
//            btn1.setVisibility(View.VISIBLE);
//        }
//
//        if (data.getIsDel() == 1) {
//            btn0.setText("删除订单");
//            btn0.setVisibility(View.VISIBLE);
//        } else if (data.getIsCancel() == 1) {
//            btn0.setText("取消订单");
//            btn0.setVisibility(View.VISIBLE);
//        }
    }

}

package com.wanbao.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.modle.MaintainBook;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class TimeSlotViewHolder extends BaseViewHolder<MaintainBook.TimesBean> {
    private final TextView textTimes;
    private final TextView textZhe;
    private final TextView btnState;
    private MaintainBook.TimesBean timesBean;
    public TimeSlotViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTimes = $(R.id.textTimes);
        textZhe = $(R.id.textZhe);
        btnState = $(R.id.btnState);
        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timesBean!=null){
                    if (timesBean.getIs_v()==1){
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.SSID, timesBean.getId()));
                    }
                }
            }
        });
    }

    @Override
    public void setData(MaintainBook.TimesBean data) {
        super.setData(data);
        timesBean=data;
        textTimes.setText(data.getName());
        if (!TextUtils.isEmpty(data.getDes())) {
            textZhe.setVisibility(View.VISIBLE);
            textZhe.setText(data.getDes());
        } else {
            textZhe.setVisibility(View.GONE);
        }
        btnState.setText(data.getBtn_txt());
        if (data.getIs_selecet()==1) {
            btnState.setBackground(ContextCompat.getDrawable(getContext(), R.mipmap.selete_time));
            btnState.setEnabled(true);
        } else {
            if (data.getIs_v() == 1) {
                btnState.setEnabled(true);
                btnState.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.weibao_yy));
            } else {
                btnState.setEnabled(false);
                btnState.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.weibao_ym));
            }
        }
    }

}

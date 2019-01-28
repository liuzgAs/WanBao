package com.wanbao.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.wanbao.R;
import com.wanbao.activity.QueRenSQActivity;
import com.wanbao.activity.QueRenYSActivity;
import com.wanbao.activity.WeiBaoDDXQActivity;
import com.wanbao.base.ui.StateButton;
import com.wanbao.modle.User_Maintain_order_info;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class FooterWBDDViewHolder extends BaseViewHolder<User_Maintain_order_info.BtnBean> {
    private final StateButton btnState;
    private User_Maintain_order_info.BtnBean btnBean;
    private String oid;
    private WeiBaoDDXQActivity myActivity;
    public FooterWBDDViewHolder(ViewGroup parent, @LayoutRes int res,String id,WeiBaoDDXQActivity activity) {
        super(parent, res);
        this.oid=id;
        this.myActivity=activity;
        btnState = $(R.id.btnState);
        btnState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnBean==null){
                    return;
                }
                if ("maintain_order_auth".equals(btnBean.getUrl())){
                    Intent intent = new Intent();
                    intent.putExtra("id", oid);
                    intent.setClass(getContext(), QueRenSQActivity.class);
                    getContext().startActivity(intent);
                    myActivity.finish();
                }else if ("maintain_order_accepting".equals(btnBean.getUrl())){
                    Intent intent = new Intent();
                    intent.putExtra("id", oid);
                    intent.setClass(getContext(), QueRenYSActivity.class);
                    getContext().startActivity(intent);
                    myActivity.finish();
                }
            }
        });
    }

    @Override
    public void setData(final User_Maintain_order_info.BtnBean data) {
        super.setData(data);
        this.btnBean=data;
        btnState.setText(data.getN());
        if (data.getIs_v()==1){
            btnState.setClickable(true);
            btnState.setEnabled(true);
        }else {
            btnState.setClickable(false);
            btnState.setEnabled(false);
        }
    }

}

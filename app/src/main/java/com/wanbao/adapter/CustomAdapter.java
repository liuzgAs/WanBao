package com.wanbao.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanbao.R;
import com.wanbao.base.view.AutoScrollView;
import com.wanbao.modle.Usercar_Manual;

import java.util.List;

import cn.zhouchaoyuan.excelpanel.BaseExcelPanelAdapter;

/**
 * Created by liuzhigang on 2018/7/25/025.
 *
 * @author LiuZG
 */

public class CustomAdapter extends BaseExcelPanelAdapter<Usercar_Manual.Cm21kmBean, Usercar_Manual.DataBean, String> {
    private Context context;
    private View.OnClickListener blockListener;
    private List<Usercar_Manual.DataBean> dataBeans;
    public CustomAdapter(Context context, List<Usercar_Manual.DataBean> dataBeans, View.OnClickListener blockListener) {
        super(context);
        this.context = context;
        this.blockListener = blockListener;
        this.dataBeans=dataBeans;
    }

    //=========================================normal cell=========================================
    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        CellHold myHold = new CellHold(LayoutInflater.from(context).inflate(R.layout.item_xiangmu, parent, false));
        return myHold;
    }


    @Override
    public void onBindCellViewHolder(RecyclerView.ViewHolder holder, int verticalPosition, int horizontalPosition) {
        String s = getMajorItem(verticalPosition, horizontalPosition);
        CellHold cellHold = (CellHold) holder;
        ViewGroup.LayoutParams layoutParams=cellHold.relativeLayout.getLayoutParams();
        if ("0".equals(s)) {
            layoutParams.width=(int) context.getResources().getDimension(R.dimen.dp_120);
            cellHold.relativeLayout.setLayoutParams(layoutParams);

            cellHold.image.setVisibility(View.INVISIBLE);
            cellHold.autoScrollView.setVisibility(View.GONE);
            cellHold.view1.setVisibility(View.VISIBLE);
        } else if ("1".equals(s)) {
            layoutParams.width=(int)context.getResources().getDimension(R.dimen.dp_120);
            cellHold.relativeLayout.setLayoutParams(layoutParams);

            cellHold.autoScrollView.setVisibility(View.GONE);
            cellHold.image.setVisibility(View.VISIBLE);
            cellHold.view1.setVisibility(View.VISIBLE);
            cellHold.image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.icon_baoyang_yuan));
        } else if ("2".equals(s)) {
            layoutParams.width=(int)context.getResources().getDimension(R.dimen.dp_120);
            cellHold.relativeLayout.setLayoutParams(layoutParams);

            cellHold.autoScrollView.setVisibility(View.GONE);
            cellHold.image.setVisibility(View.VISIBLE);
            cellHold.view1.setVisibility(View.VISIBLE);
            cellHold.image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.icon_baoyang_dred));
        }else {
            layoutParams.width=(int)context.getResources().getDimension(R.dimen.dp_120)*8;
            cellHold.relativeLayout.setLayoutParams(layoutParams);

            cellHold.image.setVisibility(View.INVISIBLE);
            cellHold.view1.setVisibility(View.GONE);
            cellHold.autoScrollView.setVisibility(View.VISIBLE);
            if (horizontalPosition==0){
                cellHold.autoScrollView.setVisibility(View.VISIBLE);
                cellHold.autoScrollView.setAutoToScroll(true);//设置可以自动滑动
                cellHold.autoScrollView.setFistTimeScroll(2000);//设置第一次自动滑动的时间
                cellHold.autoScrollView.setScrollRate(50);//设置滑动的速率
                cellHold.autoScrollView.setScrollLoop(true);//设置是否循环滑动
                cellHold.textTitle.setText(dataBeans.get(verticalPosition).getV1());
            }else {
                cellHold.autoScrollView.setVisibility(View.GONE);
            }
        }
    }


    //=========================================top cell===========================================
    @Override
    public RecyclerView.ViewHolder onCreateTopViewHolder(ViewGroup parent, int viewType) {
        TopHold myHold = new TopHold(LayoutInflater.from(context).inflate(R.layout.item_top_text, parent, false));
        return myHold;
    }

    @Override
    public void onBindTopViewHolder(RecyclerView.ViewHolder holder, int position) {
        TopHold myHold = (TopHold) holder;
        Usercar_Manual.Cm21kmBean cm21kmBean = getTopItem(position);
        myHold.textView.setText(cm21kmBean.getK() + "\n" + cm21kmBean.getM());
    }

    //=========================================left cell===========================================
    @Override
    public RecyclerView.ViewHolder onCreateLeftViewHolder(ViewGroup parent, int viewType) {
        LeftHold myHold = new LeftHold(LayoutInflater.from(context).inflate(R.layout.item_left_text, parent, false));
        return myHold;
    }

    @Override
    public void onBindLeftViewHolder(RecyclerView.ViewHolder holder, int position) {
        Usercar_Manual.DataBean dataBean = getLeftItem(position);
        LeftHold myHold = (LeftHold) holder;
        myHold.textView.setText(dataBean.getName());
    }

    //=========================================top left cell=======================================
    @Override
    public View onCreateTopLeftView() {
        return LayoutInflater.from(context).inflate(R.layout.item_text, null);
    }


    static class TopHold extends RecyclerView.ViewHolder {
        TextView textView;

        public TopHold(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    static class LeftHold extends RecyclerView.ViewHolder {
        TextView textView;

        public LeftHold(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    static class CellHold extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textTitle;
        AutoScrollView autoScrollView;
        View view1;
        View view2;
        View view3;
        View relativeLayout;

        public CellHold(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.textTitle);
            autoScrollView = itemView.findViewById(R.id.autoScrollView);
            view1 = itemView.findViewById(R.id.view1);
            view2 = itemView.findViewById(R.id.view2);
            view3 = itemView.findViewById(R.id.view3);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}

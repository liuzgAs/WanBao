package com.wanbao.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.R;
import com.wanbao.activity.WeiBaoDDXQActivity;
import com.wanbao.interfaces.RecyclerFace;
import com.wanbao.modle.User_Maintain_order_info;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class DingDanImageViewHolder extends BaseViewHolder<User_Maintain_order_info.ImgShowBean> {
    private final EasyRecyclerView recyclerView;
    private final TextView textName;
    private WeiBaoDDXQActivity activity;
    private List<LocalMedia> imageList = new ArrayList<>();
    private int themeId= R.style.picture_default_style;
    private RecyclerArrayAdapter<User_Maintain_order_info.ImgShowBean.ImgsBean> adapter;
    private List<User_Maintain_order_info.ImgShowBean.ImgsBean> imgShowBean;
    private RecyclerFace recyclerFace;
    public DingDanImageViewHolder(ViewGroup parent, @LayoutRes int res,WeiBaoDDXQActivity activity) {
        super(parent, res);
        this.activity=activity;
        recyclerView = $(R.id.listChildImg);
        textName = $(R.id.textName);
        imgShowBean=new ArrayList<>();
        initRecycler();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // 查看源码可知State有三种状态：SCROLL_STATE_IDLE（静止）、SCROLL_STATE_DRAGGING（上升）、SCROLL_STATE_SETTLING（下落）
                if (newState == SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
                    recyclerFace.setScrolling(false);
                    adapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
                } else{
                    recyclerFace.setScrolling(true);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    public void setData(final User_Maintain_order_info.ImgShowBean data) {
        super.setData(data);
        imgShowBean.clear();
        imgShowBean.addAll(data.getImgs());
        textName.setText(data.getName());
        adapter.clear();
        adapter.addAll(data.getImgs());
//        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(manager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) activity.getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<User_Maintain_order_info.ImgShowBean.ImgsBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_image;
                return new ImageChildViewHolder(parent, layout,DingDanImageViewHolder.this);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                imageList.clear();
                    for (int i=0;i<imgShowBean.size();i++){
                        LocalMedia localMedia=new LocalMedia();
                        localMedia.setPath(imgShowBean.get(i).getImg());
                        imageList.add(localMedia);
                    }
                    PictureSelector.create(activity).themeStyle(themeId).openExternalPreview(position, imageList);
            }
        });
    }
    public void setCallBack(RecyclerFace callBack) {
        this.recyclerFace = callBack;
    }
}

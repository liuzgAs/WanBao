package com.wanbao.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.wanbao.R;
import com.wanbao.activity.MainActivity;
import com.wanbao.base.fragment.PSFragment;
import com.wanbao.base.http.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * des： 引导页
 * author： zhangjiebo
 * date： 2017/7/6 0006 下午 1:54
 */
public class GuideFragment extends PSFragment {

    @BindView(R.id.guide_img)
    ImageView guideImg;
    @BindView(R.id.button_guide)
    Button buttonGuide;
    Unbinder unbinder;
    private int guideImg_length;
    private int position;
    private View mInflate;
    private int img;

    public GuideFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public GuideFragment(int img, int guideImg_length, int position) {
        this.img = img;
        this.guideImg_length = guideImg_length;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_guide, container, false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        unbinder = ButterKnife.bind(this, mInflate);
        return mInflate;
    }


    @Override
    public void fetchData() {
        if (position == guideImg_length) {
            buttonGuide.setVisibility(View.VISIBLE);
        } else {
            buttonGuide.setVisibility(View.GONE);
        }
        guideImg.setImageResource(img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_guide)
    public void onViewClicked() {
        SPUtils.getInstance().put(Constant.SF.isFirst,0);
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        context.finish();
    }
}

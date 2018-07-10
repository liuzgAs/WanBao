package com.wanbao.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.CarDetails;

import java.util.ArrayList;
import java.util.List;

public class CheLiangBannerImgHolderView implements Holder<CarDetails.BannerBean> {
    private ImageView imageView;
    private List<CarDetails.BannerBean> bannerBeanList;

    public CheLiangBannerImgHolderView(List<CarDetails.BannerBean> bannerBeanList) {
        this.bannerBeanList = bannerBeanList;
    }

    @Override
    public View createView(final Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, final int position, CarDetails.BannerBean data) {
        GlideApp.with(context)
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> imgList = new ArrayList<>();
                for (int i = 0; i < bannerBeanList.size(); i++) {
                    imgList.add(bannerBeanList.get(i).getImg());
                }
//                Intent intent = new Intent();
//                intent.setClass(context, BigImgActivity.class);
//                intent.putExtra(Constant.IntentKey.BIG_IMG, new BigImgList(imgList));
//                intent.putExtra(Constant.IntentKey.BIG_IMG_POSITION, position);
//                intent.putExtra(Constant.IntentKey.VALUE,"");
//                context.startActivity(intent);
            }
        });
    }
}
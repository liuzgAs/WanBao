package com.wanbao.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.modle.Showbrand_Car;
import com.wanbao.modle.Showbrand_Info;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ZhanTingQCXQGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        Showbrand_Car.BannerBean bannerBean=(Showbrand_Car.BannerBean)path;
        GlideApp.with(context)
                .asBitmap()
                .load(bannerBean.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
    }
}

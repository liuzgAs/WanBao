package com.wanbao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.luck.picture.lib.tools.ScreenUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.dialog.MyDialog;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.RecycleViewDistancaUtil;
import com.wanbao.modle.CarDetails;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.ShareBean;
import com.wanbao.viewholder.CheLiangBannerImgHolderView;
import com.wanbao.viewholder.CheLiangXQViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.reactivex.disposables.Disposable;


/**
 * 车辆详情
 *
 * @author Administrator
 */
public class CheLiangXQActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CarDetails.ImgListBean> adapter;
    private TextView textViewTitle;
    private int viewBarHeight;
    //    private AlertDialog payVideoDialog;
    private int id;
    private ImageView imageShare;
    private View viewBottom;
    private List<CarDetails.ArchivesBean> archives;
    private List<CarDetails.BannerBean> bannerBeanList;
    private CarDetails.CarBean carBean;
    private CarDetails.StoreBean storeBean;
    private CarDetails.videoBean video;
    private ShareBean shareBean;
    private CarDetails carDetails;
    private int type;
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_xq);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
        viewBar = findViewById(R.id.viewBar);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        imageShare = (ImageView) findViewById(R.id.imageShare);
        viewBottom = findViewById(R.id.viewBottom);
        type=getIntent().getIntExtra("type",0);
    }


    @Override
    protected void initViews() {
        iwxapi = WXAPIFactory.createWXAPI(context, null);
        if (type==0){
            viewBottom.setVisibility(View.VISIBLE);
        }else {
            viewBottom.setVisibility(View.GONE);
        }
        textViewTitle.setText("车辆详情");
        viewBarHeight = (int) (getResources().getDimension(R.dimen.dp_45) + ScreenUtils.getStatusBarHeight(this));
        viewBar.getBackground().mutate().setAlpha(0);
        textViewTitle.setAlpha(0);
//        NewbieGuide.with(this)//传入activity
//                .setLabel("guide1")//设置引导层标示，用于区分不同引导层，必传！否则报错
//                .addHighLight(imageShare, HighLight.Type.RECTANGLE)//添加需要高亮的view
//                .setLayoutRes(R.layout.view_che_liangxq_yindao)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
//                .show();//显示引导层
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                NewbieGuide.with(CheLiangXQActivity.this)//传入activity
//                        .setLabel("guide1")//设置引导层标示，必传！否则报错
//                        .addHighLight(imageShare, HighLight.Type.ROUND_RECTANGLE,(int) DpUtils.convertDpToPixel(5,CheLiangXQActivity.this))//添加需要高亮的view
//                        .setLayoutRes(R.layout.view_che_liangxq_yindao)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
////                        .setBackgroundColor(Color.parseColor("#aa000000"))
//                        .show();//直接显示引导层
//            }
//        });
        initRecycle();

        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textCall).setOnClickListener(this);
        imageShare.setOnClickListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<String> imgList = new ArrayList<>();
                for (int i = 0; i < adapter.getAllData().size(); i++) {
                    imgList.add(adapter.getItem(i).getImg());
                }
//                Intent intent = new Intent();
//                intent.setClass(CheLiangXQActivity.this, BigImgActivity.class);
//                intent.putExtra(Constant.IntentKey.BIG_IMG, new BigImgList(imgList));
//                intent.putExtra(Constant.IntentKey.BIG_IMG_POSITION, position);
//                intent.putExtra(Constant.IntentKey.VALUE, "");
//                startActivity(intent);
            }
        });
    }


    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.WHITE, (int) DpUtils.convertDpToPixel(10f, this), 0, 0);
        itemDecoration.setDrawLastItem(true);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 220);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CarDetails.ImgListBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_resizable_imageview;
                return new CheLiangXQViewHolder(parent, layout);
            }

        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private Button buttonGuanZhu;
            private TextView textShiPing;
            private View viewVideo;
            private JZVideoPlayerStandard jzVideoPlayerStandard;
            private TextView textIntro;
            private TextView textName;
            private ImageView imageLogo;
            private View viewJiSuanQi;

            private TextView textPurchasePrice;
            private TextView textPrice;
            private TextView textTitle;
            private TextView textVip;
            private TextView textSingle;
            private View viewSingle;
            private View viewVip;
            private TextView textZhiShiQi;
            private ConvenientBanner banner;
            private boolean isVip = true;
            private TextView[] textArchivesTitle = new TextView[6];
            private TextView[] textArchivesDes = new TextView[6];
            private int[] textArchivesTitleID = {
                    R.id.textArchives0000,
                    R.id.textArchives0100,
                    R.id.textArchives0200,
                    R.id.textArchives0300,
                    R.id.textArchives0400,
                    R.id.textArchives0500,
            };
            private int[] textArchivesDesID = {
                    R.id.textArchives0001,
                    R.id.textArchives0101,
                    R.id.textArchives0201,
                    R.id.textArchives0301,
                    R.id.textArchives0401,
                    R.id.textArchives0501,
            };

            @Override
            public View onCreateView(ViewGroup parent) {
                View header_che_liang_xq = LayoutInflater.from(CheLiangXQActivity.this).inflate(R.layout.header_che_liang_xq, null);
                banner = header_che_liang_xq.findViewById(R.id.banner);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                textZhiShiQi = header_che_liang_xq.findViewById(R.id.textZhiShiQi);
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        textZhiShiQi.setText((position + 1) + "/" + bannerBeanList.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
//                header_che_liang_xq.findViewById(R.id.viewVideo).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        payVideo();
//                    }
//                });
                for (int i = 0; i < textArchivesTitle.length; i++) {
                    textArchivesTitle[i] = header_che_liang_xq.findViewById(textArchivesTitleID[i]);
                    textArchivesDes[i] = header_che_liang_xq.findViewById(textArchivesDesID[i]);
                }
                textTitle = header_che_liang_xq.findViewById(R.id.textTitle);
                textPrice = header_che_liang_xq.findViewById(R.id.textPrice);
                textPurchasePrice = header_che_liang_xq.findViewById(R.id.textPurchasePrice);
                imageLogo = header_che_liang_xq.findViewById(R.id.imageLogo);
                textName = header_che_liang_xq.findViewById(R.id.textName);
                textIntro = header_che_liang_xq.findViewById(R.id.textIntro);
                viewJiSuanQi = header_che_liang_xq.findViewById(R.id.viewJiSuanQi);

                jzVideoPlayerStandard = header_che_liang_xq.findViewById(R.id.videoplayer);
                jzVideoPlayerStandard.batteryLevel.setVisibility(View.GONE);
                jzVideoPlayerStandard.backButton.setVisibility(View.GONE);
                viewVideo = header_che_liang_xq.findViewById(R.id.viewVideo);
                textShiPing = header_che_liang_xq.findViewById(R.id.textShiPing);
                buttonGuanZhu = header_che_liang_xq.findViewById(R.id.buttonGuanZhu);
                buttonGuanZhu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                header_che_liang_xq.findViewById(R.id.viewCheHang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.putExtra(Constant.IntentKey.ID, storeBean.getId());
//                        intent.setClass(CheLiangXQActivity.this, CheHangXXActivity.class);
//                        startActivity(intent);
                    }
                });
                viewJiSuanQi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("id", String.valueOf(carBean.getId()));
                        intent.putExtra("money", String.valueOf(carDetails.getMoney()));
                        intent.setClass(CheLiangXQActivity.this, JiSuanQEActivity.class);
                        startActivity(intent);
                    }
                });
                return header_che_liang_xq;
            }

//            /**
//             * 支付视频dialog
//             */
//            private void payVideo() {
//                isVip = true;
//                LayoutInflater inflater = LayoutInflater.from(CheLiangXQActivity.this);
//                View dialog_chan_pin = inflater.inflate(R.layout.dialog_pay_video, null);
//                dialog_chan_pin.findViewById(R.id.imageCancle).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        payVideoDialog.dismiss();
//                    }
//                });
//                viewVip = dialog_chan_pin.findViewById(R.id.viewVip);
//                viewSingle = dialog_chan_pin.findViewById(R.id.viewSingle);
//                textSingle = dialog_chan_pin.findViewById(R.id.textSingle);
//                textVip = dialog_chan_pin.findViewById(R.id.textVip);
//                viewVip.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        isVip = true;
//                        vipRadio();
//                    }
//                });
//                viewSingle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        isVip = false;
//                        vipRadio();
//                    }
//                });
//                dialog_chan_pin.findViewById(R.id.textPayVideo).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.setClass(CheLiangXQActivity.this, PayVideoActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                vipRadio();
//                payVideoDialog = new AlertDialog.Builder(CheLiangXQActivity.this, R.style.dialog)
//                        .setView(dialog_chan_pin)
//                        .create();
//                payVideoDialog.show();
//                Window dialogWindow = payVideoDialog.getWindow();
//                dialogWindow.setGravity(Gravity.BOTTOM);
//                dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
//                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//                DisplayMetrics d = CheLiangXQActivity.this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//                lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
//                dialogWindow.setAttributes(lp);
//            }
//
//            /**
//             * 切换开通vip和单个视频
//             */
//            private void vipRadio() {
//                if (isVip) {
//                    viewSingle.setBackgroundResource(R.drawable.shape_gray1dp_5dp);
//                    viewVip.setBackgroundResource(R.drawable.shape_basic1dp_5dp);
//                    textSingle.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.light_black));
//                    textVip.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.textGold));
//                } else {
//                    viewSingle.setBackgroundResource(R.drawable.shape_basic1dp_5dp);
//                    viewVip.setBackgroundResource(R.drawable.shape_gray1dp_5dp);
//                    textSingle.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.textGold));
//                    textVip.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.light_black));
//                }
//            }

            @Override
            public void onBindView(View headerView) {
                if (bannerBeanList != null) {
                    if (bannerBeanList.size() > 0) {
                        banner.setPages(new CBViewHolderCreator() {
                            @Override
                            public Object createHolder() {
                                return new CheLiangBannerImgHolderView(bannerBeanList);
                            }
                        }, bannerBeanList);
                    } else {
                        textZhiShiQi.setText("0/0");
                    }
                } else {
                    textZhiShiQi.setText("0/0");
                }
                if (archives != null) {
                    for (int i = 0; i < archives.size(); i++) {
                        textArchivesTitle[i].setText(archives.get(i).getName());
                        textArchivesDes[i].setText(archives.get(i).getValue());
                    }
                }
                if (carBean != null) {
                    textTitle.setText(carBean.getTitle());
                    textPrice.setText(carBean.getPrice());
                    textPurchasePrice.setText(carBean.getPurchasePrice());
                }
                if (storeBean != null) {
                    GlideApp.with(context)
                            .asBitmap()
                            .load(storeBean.getLogo())
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageLogo);
                    textName.setText(storeBean.getName());
                    textIntro.setText(storeBean.getIntro());
                    if (storeBean.getIs_attention() == 1) {
                        buttonGuanZhu.setText("已关注");
                    } else {
                        buttonGuanZhu.setText("+\u3000关注");
                    }
                }
                if (video != null) {
                    viewVideo.setVisibility(View.VISIBLE);
                    textShiPing.setVisibility(View.VISIBLE);
                    jzVideoPlayerStandard.setUp(video.getPlayUrl()
                            , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                    GlideApp.with(context)
                            .asBitmap()
                            .load(video.getCoverForFeed())
                            .placeholder(R.mipmap.ic_empty)
                            .into(jzVideoPlayerStandard.thumbImageView);
                } else {
                    viewVideo.setVisibility(View.GONE);
                    textShiPing.setVisibility(View.GONE);
                }
            }

        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(CheLiangXQActivity.this);
                view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DpUtils.convertDpToPixel(15f, CheLiangXQActivity.this)));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        recyclerView.setRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollY = RecycleViewDistancaUtil.getDistance(recyclerView, 0);
                float guangGaoHeight = getResources().getDimension(R.dimen.dp_200);
                if (scrollY <= guangGaoHeight - viewBarHeight && scrollY >= 0) {
                    int i = (int) ((double) scrollY / (double) (guangGaoHeight - viewBar.getHeight()) * 255);
                    viewBar.getBackground().mutate().setAlpha(i);
                    textViewTitle.setAlpha((float) i / 255f);
                } else {
                    viewBar.getBackground().mutate().setAlpha(255);
                    textViewTitle.setAlpha(1);
                }
            }
        });
    }




    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.Carold_Details;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid, 0) + "");
        params.put("id", id + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        HttpApi.post(this, getOkObject(), new HttpApi.CallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                LogUtils.e("车辆详情", s);
                try {
                     carDetails = GsonUtils.parseJSON(s, CarDetails.class);
                    if (carDetails.getStatus() == 1) {
                        viewBar.getBackground().mutate().setAlpha(0);
                        imageShare.setVisibility(View.VISIBLE);
                        archives = carDetails.getArchives();
                        bannerBeanList = carDetails.getBanner();
                        carBean = carDetails.getCar();
                        storeBean = carDetails.getStore();
                        video = carDetails.getVideo();
                        shareBean=carDetails.getShare();
                        List<CarDetails.ImgListBean> imgListBeanList = carDetails.getImgList();
                        adapter.clear();
                        adapter.addAll(imgListBeanList);
                    }else {
                        showError(carDetails.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            @Override
            public void onComplete() {

            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(CheLiangXQActivity.this).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        initData();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
                viewBar.getBackground().mutate().setAlpha(255);
                imageShare.setVisibility(View.GONE);
                viewBottom.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textCall:
                PhoneUtils.dial(storeBean.getTel());
                break;
            case R.id.imageBack:
                finish();
                break;
            case R.id.imageShare:
                if (shareBean!=null){
                    MyDialog.share02(context,iwxapi,shareBean.getShareUrl(),shareBean.getShareTitle(),shareBean.getShareDes(),shareBean.getShareImg());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


}

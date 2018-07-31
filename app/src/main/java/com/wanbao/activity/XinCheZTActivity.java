package com.wanbao.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.wanbao.R;
import com.wanbao.adapter.XinCheZTGlideImageLoader;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.util.ScreenUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Showbrand_Index;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.XinCheBrandViewHolder;
import com.wanbao.viewholder.XinCheZTViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class XinCheZTActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Showbrand_Index.DataBean> adapter;
    int page = 1;
    private RecyclerArrayAdapter<Showbrand_Index.BrandBean> hadapter;
    private Showbrand_Index showbrand_index;
    private String bid;
    private int themeId= R.style.picture_default_style;
    private List<LocalMedia> imageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_che_zt);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initViews() {
        titleText.setText("新车展厅");
        initRecycler();
    }

    @Override
    protected void initData() {
        onRefresh();
    }
    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_10), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.light_red);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Showbrand_Index.DataBean>(XinCheZTActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xinchezt;
                return new XinCheZTViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private Banner banner;
            private MyEasyRecyclerView hrecyclerView;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_xinchezt, null);
                banner = view.findViewById(R.id.banner);
                int screenWidth = ScreenUtils.getScreenWidth(context);
                ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
                layoutParams.width = screenWidth;
                banner.setLayoutParams(layoutParams);
                LogUtils.e("ShouYeFragment--onCreateView", ""+(int) (480f * (float) screenWidth / 1080f));
                layoutParams.height = (int) (480f * (float) screenWidth / 1080f);
                hrecyclerView = view.findViewById(R.id.recyclerView);
                hrecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                hrecyclerView.setAdapter(hadapter = new RecyclerArrayAdapter<Showbrand_Index.BrandBean>(context) {

                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_xinchepp;
                        return new XinCheBrandViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12, context));
                spaceDecoration.setPaddingEdgeSide(false);
                hrecyclerView.addItemDecoration(spaceDecoration);
                hrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                hrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recycler, int dx, int dy) {
                        super.onScrolled(recycler, dx, dy);
                        hrecyclerView.setScroll(true);
                    }
                });
                hrecyclerView.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
                    @Override
                    public void daoDiLe() {
                    }
                });
                hadapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        bid=String.valueOf(hadapter.getItem(position).getBid());
                        onRefresh();
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (showbrand_index!=null){
                    hadapter.clear();
                    hadapter.addAll(showbrand_index.getBrand());
                    banner.setImageLoader(new XinCheZTGlideImageLoader());
                    //设置图片集合
                    banner.setImages(showbrand_index.getBanner());
                    //banner设置方法全部调用完毕时最后调用
                    //设置轮播时间
                    banner.setDelayTime(3000);
                    banner.start();
                }
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        imageList.clear();
                        for (int i=0;i<showbrand_index.getBanner().size();i++){
                            LocalMedia localMedia=new LocalMedia();
                            localMedia.setPath(showbrand_index.getBanner().get(i).getImg());
                            imageList.add(localMedia);
                        }
                        PictureSelector.create((Activity)context).themeStyle(themeId).openExternalPreview(position, imageList);
                    }
                });
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            Showbrand_Index usercar_index = GsonUtils.parseJSON(s, Showbrand_Index.class);
                            int status = usercar_index.getStatus();
                            if (status == 1) {
                                adapter.addAll(usercar_index.getData());
                            } else {
                                ToastUtils.showShort(usercar_index.getInfo());
                            }
                        } catch (Exception e) {
                            adapter.pauseMore();
                        }
                    }

                    @Override
                    public void onError() {
                        adapter.pauseMore();
                    }

                    @Override
                    public void onComplete() {
                    }

                });
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent();
                intent.putExtra("id",String.valueOf(adapter.getItem(position).getId()));
                intent.setClass(context,XinCheZTXQActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @OnClick(R.id.imageback)
    public void onViewClicked() {
        finish();
    }

    private void getCar() {
        page = 1;
        HttpApi.post(context, getOkObjectCar(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                try {
                    LogUtils.e("Showbrand_Index", s);
                    page++;
                    showbrand_index = GsonUtils.parseJSON(s, Showbrand_Index.class);
                    int status = showbrand_index.getStatus();
                    if (status == 1) {
                        adapter.clear();
                        adapter.addAll(showbrand_index.getData());
                    } else {
                        ToastUtils.showShort(showbrand_index.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据异常！");
                }
            }

            @Override
            public void onError() {
                showError("网络异常");
            }

            @Override
            public void onComplete() {
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(context).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        onRefresh();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }

        });
    }

    private OkObject getOkObjectCar() {
        String url = Constant.HOST + Constant.Url.Showbrand_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("p", String.valueOf(page));
        params.put("bid",bid);
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        getCar();
    }
}

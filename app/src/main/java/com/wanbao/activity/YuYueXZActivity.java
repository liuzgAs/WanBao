package com.wanbao.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.tools.DpUtils;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.MaintainBook;
import com.wanbao.modle.OkObject;
import com.wanbao.ui.MyEasyRecyclerView;
import com.wanbao.viewholder.MakeDateViewHolder;
import com.wanbao.viewholder.TimeSlotViewHolder;
import com.wanbao.viewholder.WeiBaoSellerViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * @author Administrator
 */
public class YuYueXZActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.myRecyclerView)
    MyEasyRecyclerView myRecyclerView;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<MaintainBook.TimesBean> adapter;
    private RecyclerArrayAdapter<MaintainBook.BookDateBean> myAdapter;
    private RecyclerArrayAdapter<MaintainBook.SellerBean> myHAdapter;
    private RecyclerArrayAdapter<MaintainBook.BookDateBean> popAdapter;

    private String id;
    private String book_date;
    private String seller_id;
    private String ssid;
    private String bookDatePos;
    private String sellerPos;
    private String timesPos;
    private List<MaintainBook.TimesBean> timesBeans=new ArrayList<>();
    private List<MaintainBook.BookDateBean> bookDateBeans=new ArrayList<>();
    private List<MaintainBook.SellerBean> sellerBeans=new ArrayList<>();
    private MaintainBook maintainBook;
    private View mPopView;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_yue_xz);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        id=getIntent().getStringExtra("sid");
    }

    @Override
    protected void initViews() {
        titleText.setText("预约选择");
        initMyRecycler();
        initRecycler();
        setPopwindow();
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.SSID.equals(event.getAction())){
            ssid=(String) event.getData();
            onRefresh();
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_1), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<MaintainBook.TimesBean>(YuYueXZActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_time_slot;
                return new TimeSlotViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            private MyEasyRecyclerView myHRecyclerView;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.header_yuyue_xz, null);
                myHRecyclerView=view.findViewById(R.id.myHRecyclerView);
                initMyHRecycler();
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (maintainBook!=null){
                    myHAdapter.clear();
                    myHAdapter.addAll(maintainBook.getSeller());
                    myHAdapter.notifyDataSetChanged();
                }
            }
            /**
             * 初始化recyclerview
             */
            private void initMyHRecycler() {
                myHRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                myHRecyclerView.setAdapter(myHAdapter = new RecyclerArrayAdapter<MaintainBook.SellerBean>(YuYueXZActivity.this) {
                    @Override
                    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                        int layout = R.layout.item_weibao_seller;
                        return new WeiBaoSellerViewHolder(parent, layout);
                    }
                });
                SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(10f, context));
                spaceDecoration.setPaddingEdgeSide(false);
                myHRecyclerView.addItemDecoration(spaceDecoration);
                myHRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

                myHRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recycler, int dx, int dy) {
                        super.onScrolled(recycler, dx, dy);
                        myHRecyclerView.setScroll(true);
                    }
                });
                myHRecyclerView.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
                    @Override
                    public void daoDiLe() {
                    }
                });
                myHAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (myHAdapter.getItem(position).getIs_v()==1){

                            seller_id=myHAdapter.getItem(position).getId();
                        onRefresh();}
                    }
                });
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                book_date="";
//                seller_id="";
//                ssid=adapter.getItem(position).getId();
//                onRefresh();
            }
        });
        recyclerView.setRefreshListener(this);
    }

    /**
     * 初始化recyclerview
     */
    private void initMyRecycler() {
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        myRecyclerView.setAdapter(myAdapter = new RecyclerArrayAdapter<MaintainBook.BookDateBean>(YuYueXZActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_make_date;
                return new MakeDateViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(1f, context));
        spaceDecoration.setPaddingEdgeSide(false);
        myRecyclerView.addItemDecoration(spaceDecoration);
        myRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        myRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);
                myRecyclerView.setScroll(true);
            }
        });
        myRecyclerView.setOnDaoDiLeListener(new MyEasyRecyclerView.OnDaoDiLeListener() {
            @Override
            public void daoDiLe() {
            }
        });
        myAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (myAdapter.getItem(position).getIs_v()==1){

                    book_date=myAdapter.getItem(position).getV();
                onRefresh();}
            }
        });
    }

    @OnClick({R.id.imageback, R.id.imageDate, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                String sellerName=null;
                String showTime=null;
                for (int i=0;i<bookDateBeans.size();i++){
                    if (bookDateBeans.get(i).getIs_selecet()==1){
                        bookDatePos=bookDateBeans.get(i).getV();
                    }
                }
                for (int i=0;i<timesBeans.size();i++){
                    if (timesBeans.get(i).getIs_selecet()==1){
                        timesPos=timesBeans.get(i).getId();
                        showTime=timesBeans.get(i).getName();
                    }
                }
                for (int i=0;i<sellerBeans.size();i++){
                    if (sellerBeans.get(i).getIs_selecet()==1){
                        sellerPos=sellerBeans.get(i).getId();
                        sellerName=sellerBeans.get(i).getName();
                    }
                }
                if (TextUtils.isEmpty(bookDatePos)){
                    ToastUtils.showShort("请选择日期！");
                    return;
                }
                if (TextUtils.isEmpty(sellerPos)){
                    ToastUtils.showShort("请选择销售人员！");
                    return;
                }
                if (TextUtils.isEmpty(timesPos)){
                    ToastUtils.showShort("请选择时间段！");
                    return;
                }
                HashMap<String,String> timeAndNames=new HashMap<>();
                timeAndNames.put("book_time",bookDatePos);
                timeAndNames.put("show_time",bookDatePos+showTime);
                timeAndNames.put("ssid",timesPos);
                timeAndNames.put("seller_id",sellerPos);
                timeAndNames.put("sellerName",sellerName);
                EventBus.getDefault().post(new BaseEvent(BaseEvent.TimeAndName,timeAndNames));
                finish();
                break;
            case R.id.imageback:
                finish();
                break;
            case R.id.imageDate:
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    // 设置PopupWindow 显示的形式 底部或者下拉等
                    // 在某个位置显示
                    mPopupWindow.showAsDropDown(myRecyclerView);
                    // 作为下拉视图显示
                    // mPopupWindow.showAsDropDown(mPopView, Gravity.CENTER, 200, 300);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        getMaintainBook();
    }
    private void getMaintainBook() {
        HttpApi.post(context, getOkObjectMaintainBook(), new HttpApi.CallBack() {
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
                    LogUtils.e("选车", s);
                    maintainBook = GsonUtils.parseJSON(s, MaintainBook.class);
                    int status = maintainBook.getStatus();
                    if (status == 1) {
                        timesBeans.clear();
                        bookDateBeans.clear();
                        sellerBeans.clear();
                        timesBeans.addAll(maintainBook.getTimes());
                        bookDateBeans.addAll(maintainBook.getBook_date());
                        sellerBeans.addAll(maintainBook.getSeller());

                        adapter.clear();
                        adapter.addAll(maintainBook.getTimes());
                        myAdapter.clear();
                        myAdapter.addAll(maintainBook.getBook_date());
                        myAdapter.notifyDataSetChanged();
                        popAdapter.clear();
                        popAdapter.addAll(maintainBook.getBook_date());
                        popAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showShort(maintainBook.getInfo());
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

    private OkObject getOkObjectMaintainBook() {
        String url = Constant.HOST + Constant.Url.Maintain_book;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id",id);
        params.put("book_date",book_date);
        params.put("seller_id",seller_id);
        params.put("ssid",ssid);
        return new OkObject(params, url);
    }


    private void setPopwindow() {
        mPopView = getLayoutInflater().inflate(R.layout.popwindow_pcate_mache, null);
        // 将转换的View放置到 新建一个popuwindow对象中
        mPopupWindow = new PopupWindow(mPopView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 点击popuwindow外让其消失
        EasyRecyclerView recyclePacate = mPopView.findViewById(R.id.recyclePacate);
//        FrameLayout viewBg = mPopView.findViewById(R.id.viewBg);
        initPopRecycler(recyclePacate);
//        viewBg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPopupWindow.dismiss();
//            }
//        });
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
    }

    private void initPopRecycler(EasyRecyclerView recyclePacate) {
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        recyclePacate.setLayoutManager(manager);
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(1f, context));
        recyclePacate.addItemDecoration(spaceDecoration);
        recyclePacate.setAdapter(popAdapter = new RecyclerArrayAdapter<MaintainBook.BookDateBean>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_make_date_pop;
                return new MakeDateViewHolder(parent, layout);
            }
        });
        manager.setSpanSizeLookup(popAdapter.obtainGridSpanSizeLookUp(1));
        popAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (popAdapter.getItem(position).getIs_v()==1){
                    mPopupWindow.dismiss();
                    book_date=myAdapter.getItem(position).getV();
                    myRecyclerView.scrollToPosition(position);
                    onRefresh();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}

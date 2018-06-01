package com.wanbao.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.base.view.EditDialog;
import com.wanbao.modle.Index_Seller;
import com.wanbao.modle.Index_Store;
import com.wanbao.modle.Maintain_Index;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Usercar_Index;
import com.wanbao.viewholder.TcViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class WeiXiuBYActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageRight)
    ImageView imageRight;
    @BindView(R.id.viewBar)
    View viewBar;
    @BindView(R.id.viewXuanZheCX)
    LinearLayout viewXuanZheCX;
    @BindView(R.id.textLc)
    TextView textLc;
    @BindView(R.id.viewSzlc)
    LinearLayout viewSzlc;
    @BindView(R.id.textDp)
    TextView textDp;
    @BindView(R.id.viewXzdp)
    LinearLayout viewXzdp;
    @BindView(R.id.textYyxm)
    TextView textYyxm;
    @BindView(R.id.viewYyxm)
    LinearLayout viewYyxm;
    @BindView(R.id.textYysj)
    TextView textYysj;
    @BindView(R.id.viewYysj)
    LinearLayout viewYysj;
    @BindView(R.id.textFwry)
    TextView textFwry;
    @BindView(R.id.viewFwry)
    LinearLayout viewFwry;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.textWcsj)
    TextView textWcsj;
    @BindView(R.id.radioZx)
    RadioButton radioZx;
    @BindView(R.id.viewZx)
    LinearLayout viewZx;
    @BindView(R.id.radioQt)
    RadioButton radioQt;
    @BindView(R.id.viewQt)
    LinearLayout viewQt;
    @BindView(R.id.checkBoxBx)
    CheckBox checkBoxBx;
    @BindView(R.id.viewBx)
    LinearLayout viewBx;
    @BindView(R.id.btnZf)
    Button btnZf;
    @BindView(R.id.btnYc)
    Button btnYc;
    @BindView(R.id.textXzcl)
    TextView textXzcl;
    private Index_Store.DataBean index_store;
    private Usercar_Index.DataBean usercar_Index;
    private Index_Seller.DataBean index_Seller;
    private String ucid;
    private String store_id;
    private String maintain_id = "1";
    private String seller_id;
    private String book_time;
    private String bag_id;
    private int isOnline;
    private int isBx;
    private Maintain_Index maintain_index;
    private ArrayList<Maintain_Index.DataBeanX> dataBeanXES=new ArrayList<>();
    private RecyclerArrayAdapter<Maintain_Index.DataBeanX> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xiu_by);
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
        titleText.setText("维修保养");
        initRecycler();
    }

    @Override
    protected void initData() {
        getTc();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Choose_Dp.equals(event.getAction())) {
            index_store = (Index_Store.DataBean) event.getData();
            if (index_store != null) {
                textDp.setText(index_store.getTitle());
                store_id = String.valueOf(index_store.getId());
            }
        }
        if (BaseEvent.Choose_MyCar.equals(event.getAction())) {
            usercar_Index = (Usercar_Index.DataBean) event.getData();
            if (usercar_Index != null) {
                textXzcl.setText(usercar_Index.getTitle());
                ucid = String.valueOf(usercar_Index.getCid());
            }
        }
        if (BaseEvent.Choose_Xsgw.equals(event.getAction())) {
            index_Seller = (Index_Seller.DataBean) event.getData();
            if (index_Seller != null) {
                textFwry.setText(index_Seller.getName());
                seller_id = String.valueOf(index_Seller.getId());
            }
        }
        if (BaseEvent.Choose_Tc.equals(event.getAction())) {
            index_Seller = (Index_Seller.DataBean) event.getData();
            if (index_Seller != null) {
                textFwry.setText(index_Seller.getName());
                seller_id = String.valueOf(index_Seller.getId());
            }
        }
    }

    @OnClick({R.id.viewBx, R.id.imageback, R.id.viewXuanZheCX, R.id.viewSzlc, R.id.viewXzdp, R.id.viewYyxm, R.id.viewYysj, R.id.viewFwry, R.id.viewZx, R.id.viewQt, R.id.btnZf, R.id.btnYc})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewXuanZheCX:
                intent = new Intent();
                intent.setClass(context, AiCheDangAnActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSzlc:
                final EditDialog editDialog = new EditDialog(context, "行驶里程（km）", "0", "确认", "取消");
                editDialog.setClicklistener(new EditDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm(String intro) {
                        editDialog.dismiss();
                        textLc.setText(intro);
                    }

                    @Override
                    public void doCancel() {
                        editDialog.dismiss();
                    }
                });
                editDialog.show();
                break;
            case R.id.viewXzdp:
                if (TextUtils.isEmpty(ucid)) {
                    ToastUtils.showShort("请先选择车辆！");
                    return;
                }
                intent = new Intent();
                intent.putExtra("cid", ucid);
                intent.setClass(context, YuYueDpActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYyxm:
                break;
            case R.id.viewYysj:
                final Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(WeiXiuBYActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {


                        TimePickerDialog dialog = new TimePickerDialog(WeiXiuBYActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                textYysj.setText(year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute);
                                book_time = year + "-" + (month + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute;
                            }
                        }, c1.get(Calendar.HOUR_OF_DAY), c1.get(Calendar.MINUTE), true);
                        dialog.show();

                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.viewFwry:
                if (TextUtils.isEmpty(store_id)) {
                    ToastUtils.showShort("请先选择店铺！");
                    return;
                }
                intent = new Intent();
                intent.putExtra("sid", store_id);
                intent.setClass(context, XiaoShouGWActivity.class);
                startActivity(intent);
                break;
            case R.id.viewZx:
                isOnline(1);
                break;
            case R.id.viewQt:
                isOnline(0);
                break;
            case R.id.btnZf:
                intent = new Intent();
                intent.putExtra("cid", ucid);
                intent.setClass(context, QueRenWeiBaoXMActivity.class);
                startActivity(intent);
                break;
            case R.id.btnYc:
                break;
            case R.id.viewBx:
                if (isBx == 1) {
                    isBx(0);
                } else {
                    isBx(1);
                }
                break;
            default:
                break;
        }
    }


    private void getTc() {
        HttpApi.post(context, getOkObjectTc(), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                dismissDialog();
                try {
                    LogUtils.e("保养套餐", s);
                    maintain_index = GsonUtils.parseJSON(s, Maintain_Index.class);
                    if (maintain_index.getStatus() == 1) {
                        textXzcl.setText(maintain_index.getCar_name());
                        textDp.setText(maintain_index.getStore_name());
                        textYyxm.setText(maintain_index.getMaintain_name());
                        textYysj.setText(maintain_index.getBook_time());
                        textFwry.setText(maintain_index.getSeller_name());
                        textWcsj.setText(maintain_index.getEnd_time());
                        ucid = String.valueOf(maintain_index.getUcid());
                        store_id = String.valueOf(maintain_index.getStore_id());
                        maintain_id = String.valueOf(maintain_index.getMaintain_id());
                        seller_id = String.valueOf(maintain_index.getSeller_id());
                        book_time = String.valueOf(maintain_index.getBook_time());
                        bag_id = String.valueOf(maintain_index.getBag_id());
                        isOnline(maintain_index.getOnline_pay());
                        isBx(maintain_index.getInsurance());
                        dataBeanXES.clear();
                        dataBeanXES.addAll(maintain_index.getData());
                        adapter.clear();
                        adapter.addAll(dataBeanXES);
                    } else {
                        ToastUtils.showShort(maintain_index.getInfo());
                    }
                } catch (Exception e) {
                    dismissDialog();
                    ToastUtils.showShort("数据异常");
                }
            }

            @Override
            public void onError() {
                dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }


        });
    }

    private OkObject getOkObjectTc() {
        String url = Constant.HOST + Constant.Url.Maintain_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("ucid", ucid);
        params.put("store_id", store_id);
        params.put("maintain_id", maintain_id);
        params.put("seller_id", seller_id);
        params.put("book_time", book_time);
        params.put("bag_id", bag_id);
        return new OkObject(params, url);
    }

    private void isOnline(int isOnline) {
        this.isOnline = isOnline;
        if (isOnline == 1) {
            radioZx.setChecked(true);
            radioQt.setChecked(false);
        } else {
            radioZx.setChecked(false);
            radioQt.setChecked(true);
        }
    }

    private void isBx(int isBx) {
        this.isBx = isBx;
        if (isBx == 1) {
            checkBoxBx.setChecked(true);
        } else {
            checkBoxBx.setChecked(false);
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.dp_2), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Maintain_Index.DataBeanX>(WeiXiuBYActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_group;
                return new TcViewHolder(parent, layout,WeiXiuBYActivity.this);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i=0;i<dataBeanXES.size();i++){
                    dataBeanXES.set(i,new Maintain_Index.DataBeanX(dataBeanXES.get(i).getId(),0,
                            dataBeanXES.get(i).getTitle(),dataBeanXES.get(i).getDes(),
                            dataBeanXES.get(i).getMoney(),dataBeanXES.get(i).getData()));
                }
                dataBeanXES.set(position,new Maintain_Index.DataBeanX(dataBeanXES.get(position).getId(),1,
                        dataBeanXES.get(position).getTitle(),dataBeanXES.get(position).getDes(),
                        dataBeanXES.get(position).getMoney(),dataBeanXES.get(position).getData()));
                adapter.clear();
                adapter.addAll(dataBeanXES);
//                adapter.notifyDataSetChanged();
            }
        });
    }

}

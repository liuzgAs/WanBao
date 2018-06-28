package com.wanbao.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.ui.ListViewForScrollView;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.Index_Seller;
import com.wanbao.modle.Index_Store;
import com.wanbao.modle.Maintain_Index;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Order_NewOrder;
import com.wanbao.modle.Usercar_Index;

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
    EditText textLc;
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
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    @BindView(R.id.viewTc)
    FrameLayout viewTc;
    @BindView(R.id.editmsgDes)
    EditText editmsgDes;
    @BindView(R.id.viewBysc)
    LinearLayout viewBysc;
    private Index_Store.DataBean index_store;
    private Usercar_Index.DataBean usercar_Index;
    private Index_Seller.DataBean index_Seller;
    private String ucid;
    private String store_id;
    private String maintain_id;
    private String seller_id;
    private String book_time;
    private String bag_id;
    private int isOnline;
    private int isBx;
    private Maintain_Index maintain_index;
    private ArrayList<Maintain_Index.DataBeanX> dataBeanXES = new ArrayList<>();
    private MyTcAdapter myTcAdapter;
    private ArrayList<String> maintainString = new ArrayList<>();
    private String xslc;

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
        myTcAdapter = new MyTcAdapter();
        listView.setAdapter(myTcAdapter);
    }

    @Override
    protected void initData() {
        getTc();
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Pay_Sucess.equals(event.getAction())) {
            finish();
        }
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
        getTc();
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
                intent.putExtra("type",1);
                intent.setClass(context, AiCheDangAnActivity.class);
                startActivity(intent);
                break;
            case R.id.viewSzlc:
//                final EditDialog editDialog = new EditDialog(context, "行驶里程（km）", "", "确认", "取消");
//                editDialog.setClicklistener(new EditDialog.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm(String intro) {
//                        editDialog.dismiss();
//                        textLc.setText(intro+"km");
//                        xslc=intro;
//                    }
//
//                    @Override
//                    public void doCancel() {
//                        editDialog.dismiss();
//                    }
//                });
//                editDialog.show();
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
                maintainString.clear();
                for (int i = 0; i < maintain_index.getMaintain().size(); i++) {
                    maintainString.add(maintain_index.getMaintain().get(i).getName());
                }
                new MaterialDialog.Builder(context)
                        .title("选择预约项目")
                        .items(maintainString)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                dialog.dismiss();
                                textYyxm.setText(maintain_index.getMaintain().get(position).getName());
                                maintain_id = String.valueOf(maintain_index.getMaintain().get(position).getId());
                                getTc();
                            }
                        })
                        .show();
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
                                getTc();
                            }
                        }, c1.get(Calendar.HOUR_OF_DAY), c1.get(Calendar.MINUTE), true);
                        dialog.show();

                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
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
                if (maintain_index.getOnline_pay() == 1) {
                    isOnline(1);
                } else {
                    ToastUtils.showShort("该套餐只能前台支付！");
                }
                break;
            case R.id.viewQt:
                isOnline(0);
                break;
            case R.id.btnZf:
                if (TextUtils.isEmpty(textLc.getText().toString())) {
                    ToastUtils.showShort("请设置里程");
                    return;
                }
                getOrder();
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
//                        textWcsj.setText(String.valueOf(maintain_index.getInsurance()));
                        editmsgDes.setHint(maintain_index.getMsgDes());
                        ucid = String.valueOf(maintain_index.getUcid());
                        store_id = String.valueOf(maintain_index.getStore_id());
                        maintain_id = String.valueOf(maintain_index.getMaintain_id());
                        seller_id = String.valueOf(maintain_index.getSeller_id());
                        book_time = String.valueOf(maintain_index.getBook_time());
                        bag_id = String.valueOf(maintain_index.getBag_id());
                        isOnline(maintain_index.getOnline_pay());
                        isBx(maintain_index.getInsurance());
                        if (maintain_index.getData().size() > 0) {
                            dataBeanXES.clear();
                            dataBeanXES.addAll(maintain_index.getData());
                        }
                        myTcAdapter.notifyDataSetChanged();
                        viewMaintain(maintain_index.getDataShow());
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


    private void isBx(int isBx) {
        this.isBx = isBx;
        if (isBx == 1) {
            checkBoxBx.setChecked(true);
        } else {
            checkBoxBx.setChecked(false);
        }
    }


    class MyTcAdapter extends BaseAdapter {

        private MyAdapter myAdapter;

        class ViewHolder {
            public RadioButton radioButton;
            public TextView textTc;
            public TextView textJine;
            public View viewTc;
            public View viewTc0;
            public ListView listView;
            public TextView textContent;
        }


        @Override
        public int getCount() {
            return dataBeanXES.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_group, null);
                holder.radioButton = (RadioButton) convertView.findViewById(R.id.radioButton);
                holder.textTc = (TextView) convertView.findViewById(R.id.textTc);
                holder.textJine = (TextView) convertView.findViewById(R.id.textJine);
                holder.viewTc = (View) convertView.findViewById(R.id.viewTc);
                holder.viewTc0 = (View) convertView.findViewById(R.id.viewTc0);
                holder.listView = (ListView) convertView.findViewById(R.id.listView);
                holder.textContent = (TextView) convertView.findViewById(R.id.textContent);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (dataBeanXES.get(position).getIsc() == 1) {
                holder.radioButton.setChecked(true);
                holder.viewTc.setVisibility(View.VISIBLE);
            } else {
                holder.radioButton.setChecked(false);
                holder.viewTc.setVisibility(View.GONE);
            }
            holder.textTc.setText(dataBeanXES.get(position).getTitle());
            holder.textJine.setText(String.valueOf(dataBeanXES.get(position).getMoney()));
            holder.textContent.setText(dataBeanXES.get(position).getDes());
            myAdapter = new MyAdapter(dataBeanXES.get(position));
            holder.listView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
            holder.viewTc0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < dataBeanXES.size(); i++) {
                        dataBeanXES.set(i, new Maintain_Index.DataBeanX(dataBeanXES.get(i).getId(), 0,
                                dataBeanXES.get(i).getTitle(), dataBeanXES.get(i).getDes(),
                                dataBeanXES.get(i).getMoney(), dataBeanXES.get(i).getData()));
                    }
                    dataBeanXES.set(position, new Maintain_Index.DataBeanX(dataBeanXES.get(position).getId(), 1,
                            dataBeanXES.get(position).getTitle(), dataBeanXES.get(position).getDes(),
                            dataBeanXES.get(position).getMoney(), dataBeanXES.get(position).getData()));
                    myTcAdapter.notifyDataSetChanged();
                    bag_id = String.valueOf(dataBeanXES.get(position).getId());
                    getTc();
                }
            });
            return convertView;
        }
    }

    class MyAdapter extends BaseAdapter {

        private Maintain_Index.DataBeanX dataBean;

        class ViewHolder {
            public TextView textName;
            public TextView textJinel;
        }

        public MyAdapter(Maintain_Index.DataBeanX dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public int getCount() {
            return dataBean.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_child, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                holder.textJinel = (TextView) convertView.findViewById(R.id.textJinel);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText(dataBean.getData().get(position).getN());
            holder.textJinel.setText(dataBean.getData().get(position).getV());
            return convertView;
        }
    }

    private void getOrder() {
        HttpApi.post(context, getOkObjectOrder(), new HttpApi.CallBack() {
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
                    Order_NewOrder order_newOrder = GsonUtils.parseJSON(s, Order_NewOrder.class);
                    if (order_newOrder.getStatus() == 1) {
                        if (isOnline == 1) {
                            Intent intent = new Intent();
                            intent.putExtra("Oid", order_newOrder.getOid());
                            intent.setClass(context, LiJiZhiFuActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent();
                            intent.putExtra("paytype", 0);
                            intent.setClass(context, PaySucessActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        ToastUtils.showShort(order_newOrder.getInfo());
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

    private OkObject getOkObjectOrder() {
        String url = Constant.HOST + Constant.Url.Order_NewOrder;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("sid", store_id);
        params.put("ucid", ucid);
        params.put("bag_id", bag_id);
        params.put("mid", maintain_id);
        params.put("seller_id", seller_id);
        params.put("book_time", book_time);
        params.put("item_id", "");
        params.put("type", "1");
        params.put("pay_msg", editmsgDes.getText().toString());
        params.put("online_pay", String.valueOf(isOnline));
        params.put("km", textLc.getText().toString());
        return new OkObject(params, url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

    private void viewMaintain(int id) {
        if (id == 1) {
            isOnline(1);
            viewTc.setVisibility(View.VISIBLE);
            viewBysc.setVisibility(View.VISIBLE);
            editmsgDes.setVisibility(View.GONE);
        } else {
            isOnline(0);
            viewTc.setVisibility(View.GONE);
            viewBysc.setVisibility(View.GONE);
            editmsgDes.setVisibility(View.VISIBLE);
        }
    }

    private void isOnline(int isOnline) {
        this.isOnline = isOnline;
        if (isOnline == 1) {
            radioZx.setChecked(true);
            radioQt.setChecked(false);
            btnZf.setText("立即支付");
        } else {
            radioZx.setChecked(false);
            radioQt.setChecked(true);
            btnZf.setText("立即预约");
        }
    }
}

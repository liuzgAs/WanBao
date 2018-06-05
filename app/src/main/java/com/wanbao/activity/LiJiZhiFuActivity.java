package com.wanbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.base.activity.BaseActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.PayResult;
import com.wanbao.modle.Pay_Index;
import com.wanbao.modle.Pay_New_pay;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class LiJiZhiFuActivity extends BaseActivity {

    @BindView(R.id.imageback)
    ImageView imageback;
    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.imageCar)
    ImageView imageCar;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDes)
    TextView textDes;
    @BindView(R.id.textDes2)
    TextView textDes2;
    @BindView(R.id.textOrder_amount)
    TextView textOrderAmount;
    @BindView(R.id.textOrder_sn)
    TextView textOrderSn;
    @BindView(R.id.imageZfb)
    ImageView imageZfb;
    @BindView(R.id.viewZfb)
    LinearLayout viewZfb;
    @BindView(R.id.imageWx)
    ImageView imageWx;
    @BindView(R.id.viewWx)
    LinearLayout viewWx;
    @BindView(R.id.imageYl)
    ImageView imageYl;
    @BindView(R.id.viewYl)
    LinearLayout viewYl;
    @BindView(R.id.textOrder_amount0)
    TextView textOrderAmount0;
    @BindView(R.id.btnZfa)
    Button btnZfa;
    private String Oid;
    private int type = 0;
    private static final int SDK_PAY_FLAG = 1;
    private MyHandler myHandler;
    private IWXAPI iwxapi;
    private int paytype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_ji_zhi_fu);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void initSP() {

    }
    private void regTowx(){
        iwxapi= WXAPIFactory.createWXAPI(context,null);
    }

    @Override
    protected void initIntent() {
        paytype=getIntent().getIntExtra("paytype",0);
        Oid = getIntent().getStringExtra("Oid");
    }

    @Override
    protected void initViews() {
        regTowx();
        titleText.setText("立即支付");
    }

    @Override
    public void onEventMainThread(BaseEvent event) {
        if (BaseEvent.Wx_Pay.equals(event.getAction())){
            int errCode=(int)event.getData();
            if (errCode == 0) {
                paySuccess();
            } else {
                ToastUtils.showShort("支付失败！");
            }
        }
    }
    private void paySuccess(){
        Intent intent=new Intent();
        intent.putExtra("paytype",paytype);
        intent.setClass(context,PaySucessActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void initData() {
        getOrder();
    }

    @OnClick({R.id.imageback, R.id.viewZfb, R.id.viewWx, R.id.viewYl, R.id.btnZfa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.viewZfb:
                zfFs(0);
                break;
            case R.id.viewWx:
                zfFs(1);
                break;
            case R.id.viewYl:
                zfFs(2);
                break;
            case R.id.btnZfa:
                getPaya();
                break;
            default:
                break;
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
                    Pay_Index pay_index = GsonUtils.parseJSON(s, Pay_Index.class);
                    if (pay_index.getStatus() == 1) {
                        textTitle.setText(pay_index.getTitle());
                        textDes.setText(pay_index.getDes());
                        textDes2.setText(pay_index.getDes2());
                        textOrderAmount.setText("¥" + pay_index.getOrder_amount());
                        textOrderSn.setText("订单编号："+pay_index.getOrder_sn());
                        textOrderAmount0.setText("¥" + pay_index.getOrder_amount());
                        GlideApp.with(context)
                                .asBitmap()
                                .load(pay_index.getImg())
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageCar);
                    } else {
                        ToastUtils.showShort(pay_index.getInfo());
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
        String url = Constant.HOST + Constant.Url.Pay_Index;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("oid", Oid);
        return new OkObject(params, url);
    }

    private void getPaya() {
        HttpApi.post(context, getOkObjectPay(), new HttpApi.CallBack() {
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
                    LogUtils.e("Pay_New_pay", s);
                    Pay_New_pay pay_new_pay = GsonUtils.parseJSON(s, Pay_New_pay.class);
                    if (pay_new_pay.getStatus() == 1) {
                        if (type == 0) {
                            zfbZf(pay_new_pay.getPayAli());
                        }else if (type==1){
                            wechatPay(pay_new_pay);
                        }
                    } else {
                        ToastUtils.showShort(pay_new_pay.getInfo());
                    }
                } catch (Exception e) {
                    Toast.makeText(context,"数据异常",Toast.LENGTH_SHORT).show();
                    dismissDialog();
                }
            }

            @Override
            public void onError() {
                ToastUtils.showShort("网络异常");
                dismissDialog();
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }

        });
    }

    private OkObject getOkObjectPay() {
        String url = Constant.HOST + Constant.Url.Pay_New_pay;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("oid", Oid);
        return new OkObject(params, url);
    }

    private void zfFs(int type) {
        this.type = type;
        if (type == 0) {
            imageZfb.setVisibility(View.VISIBLE);
            imageWx.setVisibility(View.INVISIBLE);
            imageYl.setVisibility(View.INVISIBLE);
        } else if (type == 1) {
            imageZfb.setVisibility(View.INVISIBLE);
            imageWx.setVisibility(View.VISIBLE);
            imageYl.setVisibility(View.INVISIBLE);
        } else {
            imageZfb.setVisibility(View.INVISIBLE);
            imageWx.setVisibility(View.INVISIBLE);
            imageYl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

    private void zfbZf(final String orderInfo) {
        myHandler = new MyHandler();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(LiJiZhiFuActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                myHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                    }
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 微信支付
     */
    private void wechatPay(Pay_New_pay pay_new_pay) {
        if (!checkIsSupportedWeachatPay()) {
            Toast.makeText(LiJiZhiFuActivity.this, "您暂未安装微信或您的微信版本暂不支持支付功能\n请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
        } else {
            if (pay_new_pay.getPay() == null) {
                Toast.makeText(LiJiZhiFuActivity.this, "微信支付暂未开通", Toast.LENGTH_SHORT).show();
            } else {
                Pay_New_pay.PayBean.ConfigBean config = pay_new_pay.getPay().getConfig();
                iwxapi.registerApp(config.getAppid());
                PayReq mPayReq = new PayReq();
                mPayReq.appId = config.getAppid();
                mPayReq.partnerId = config.getPartnerid();
                mPayReq.prepayId = config.getPrepayid();
                mPayReq.packageValue = config.getPackagevalue();
                mPayReq.nonceStr = config.getNoncestr();
                mPayReq.timeStamp = config.getTimestamp() + "";
                mPayReq.sign = config.getSign().toUpperCase();
                iwxapi.sendReq(mPayReq);
            }

        }
    }
    /**
     * 检查微信版本是否支付支付或是否安装可支付的微信版本
     */
    private boolean checkIsSupportedWeachatPay() {
        boolean isPaySupported = iwxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }
}

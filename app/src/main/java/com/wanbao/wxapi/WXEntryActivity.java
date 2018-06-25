package com.wanbao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wanbao.base.http.Constant;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, Constant.WXAPPID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
//        LogUtils.e("WXPayEntryActivity", "WXEntryActivity--onReq--0" + req.toString());
//        switch (req.getType()) {
//            case BaseResp.ErrCode.ERR_OK:
//                ToastUtils.showShort("分享成功");
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                ToastUtils.showShort("分享取消");
//                finish();
//                break;
//            case BaseResp.ErrCode.ERR_SENT_FAILED:
//                ToastUtils.showShort("分享失败");
//                finish();
//                break;
//            default:
//                finish();
//                break;
//        }
    }



    @Override
    public void onResp(BaseResp resp) {
        LogUtils.e("WXEntryActivity", "WXEntryActivity--onResp--1" + resp.errCode);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                ToastUtils.showShort("分享成功");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.showShort("分享取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                ToastUtils.showShort("分享失败");
                finish();
                break;
            default:
                finish();
                break;
        }
    }

}
package com.wanbao.viewholder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.utils.LogUtil;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.wanbao.GlideApp;
import com.wanbao.R;
import com.wanbao.activity.ErShouCheBJActivity;
import com.wanbao.base.event.BaseEvent;
import com.wanbao.base.http.Constant;
import com.wanbao.base.http.HttpApi;
import com.wanbao.base.util.GsonUtils;
import com.wanbao.fragment.ErShouCheGLFragment;
import com.wanbao.modle.Comment;
import com.wanbao.modle.OkObject;
import com.wanbao.modle.Seller_CarManage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by liuzhigang on 2018/5/15/015.
 *
 * @author LiuZG
 */

public class ErShouCheGLViewHolder extends BaseViewHolder<Seller_CarManage.DataBean> {
    private ImageView imageImg;
    private TextView textTitle;
    private TextView textDes;
    private TextView textPrice;
    private ImageView imageBianJi;
    private Seller_CarManage.DataBean data;
    private CompositeDisposable compositeDisposable;
    private ErShouCheGLFragment fragment;
    private AlertDialog guanLiDialog;

    public ErShouCheGLViewHolder(ViewGroup parent, @LayoutRes int res, ErShouCheGLFragment fragment) {
        super(parent, res);
        this.fragment = fragment;
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
        imageBianJi = $(R.id.imageBianJi);
        imageBianJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheLiangGLDialog(data);
            }
        });
    }

    @Override
    public void setData(final Seller_CarManage.DataBean data) {
        super.setData(data);
        this.data = data;
        GlideApp.with(getContext())
                .asBitmap()
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textPrice.setText(data.getPrice());
        if (data.getIs_edit() == 1) {
            imageBianJi.setVisibility(View.VISIBLE);
        } else {
            imageBianJi.setVisibility(View.GONE);
        }
    }

    /**
     * des： 车辆管理dialog
     * author： ZhangJieBo
     * date： 2017/11/30 0030 上午 9:56
     */
    private void cheLiangGLDialog(final Seller_CarManage.DataBean data) {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialog_chan_pin = inflater.inflate(R.layout.dialog_che_liang_gl, null);
        TextView textShangXiaJ = dialog_chan_pin.findViewById(R.id.textShangXiaJ);
        ImageView image05 = dialog_chan_pin.findViewById(R.id.image05);
        if (data.getOnline_state() == 0) {
            dialog_chan_pin.findViewById(R.id.viewShangXiaJ).setVisibility(View.INVISIBLE);
        } else if (data.getOnline_state() == 1) {
            dialog_chan_pin.findViewById(R.id.viewShangXiaJ).setVisibility(View.VISIBLE);
            textShangXiaJ.setText("上架");
            image05.setImageResource(R.mipmap.circle_sahng_jia);
        }else if (data.getOnline_state() == 2) {
            dialog_chan_pin.findViewById(R.id.viewShangXiaJ).setVisibility(View.VISIBLE);
            textShangXiaJ.setText("下架");
            image05.setImageResource(R.mipmap.circle_xia_jia);
        }else {
            dialog_chan_pin.findViewById(R.id.viewShangXiaJ).setVisibility(View.INVISIBLE);
        }
//        if (data.getState() == 21) {
//            textShangXiaJ.setText("下架");
//            image05.setImageResource(R.mipmap.circle_xia_jia);
//        } else if (data.getState() == 20) {
//            textShangXiaJ.setText("上架");
//            image05.setImageResource(R.mipmap.circle_sahng_jia);
//        }

        guanLiDialog = new AlertDialog.Builder(getContext(), R.style.dialog)
                .setView(dialog_chan_pin)
                .create();
        dialog_chan_pin.findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
            }
        });
        guanLiDialog.show();
        dialog_chan_pin.findViewById(R.id.viewFenXiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
            }
        });
        dialog_chan_pin.findViewById(R.id.viewBianJi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
                Intent intent = new Intent(getContext(), ErShouCheBJActivity.class);
                intent.putExtra("id", data.getId() + "");
                getContext().startActivity(intent);
            }
        });
        dialog_chan_pin.findViewById(R.id.viewShangXiaJ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
                if (data.getState() == 21) {
                    setSele(0, String.valueOf(data.getId()));
                } else if (data.getState() == 20) {
                    setSele(1, String.valueOf(data.getId()));
                }
            }
        });
        dialog_chan_pin.findViewById(R.id.viewLoadTp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
                fragment.showDialog("下载中..");
                final int[] count = {0};
                for (int i = 0; i < data.getImgs().size(); i++) {
                    LogUtil.e("TuWenTGViewHolder", "" + data.getImgs().get(i).getImg());
                    try {
                        HttpApi.downLoadBitmap(getContext(), data.getImgs().get(i).getImg(), "牵车", "二手车" + System.currentTimeMillis() + ".jpg", new HttpApi.CallBackImg() {
                            @Override
                            public void onSuccess(Bitmap s) {
                                LogUtil.e("TuWenTGViewHolder--onSuccess", "" + s);
                                count[0]++;
                                try {
                                    HttpApi.saveFile(getContext(), s, "牵车" + System.currentTimeMillis() + ".jpg");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (count[0] == data.getImgs().size()) {
                                    Toast.makeText(getContext(), "图片保存在/牵车", Toast.LENGTH_SHORT).show();
                                    fragment.dismissDialog();
                                }
                            }

                            @Override
                            public void onError(Response response) {

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        dialog_chan_pin.findViewById(R.id.viewYiShou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
                setSele(2, String.valueOf(data.getId()));
            }
        });
        Window dialogWindow = guanLiDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = fragment.getActivity().getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    private void setSele(int type, String id) {
        HttpApi.post(fragment.context, getOkObjectSele(type, id), new HttpApi.CallBack() {
            @Override
            public void onStart() {
                fragment.showDialog("");
            }

            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onSuccess(String s) {
                fragment.dismissDialog();
                try {
                    Comment comment = GsonUtils.parseJSON(s, Comment.class);
                    int status = comment.getStatus();
                    if (status == 1) {
                        ToastUtils.showShort("操作成功！");
                        EventBus.getDefault().post(new BaseEvent(BaseEvent.ErShouChe, null));
                    } else {
                        ToastUtils.showShort(comment.getInfo());
                    }
                } catch (Exception e) {
                    ToastUtils.showShort("数据异常！");
                }
            }

            @Override
            public void onError() {
                fragment.dismissDialog();
                ToastUtils.showShort("网络异常");
            }

            @Override
            public void onComplete() {
                fragment.dismissDialog();
                dispose();
            }


        });
    }

    private OkObject getOkObjectSele(int type, String id) {
        String url = "";
        if (type == 0) {
            url = Constant.HOST + Constant.Url.Seller_CarDown;
        } else if (type == 1) {
            url = Constant.HOST + Constant.Url.Seller_CarSale;
        } else if (type == 2) {
            url = Constant.HOST + Constant.Url.Seller_CarSaleIs;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", SPUtils.getInstance().getInt(Constant.SF.Uid) + "");
        params.put("id", id);
        return new OkObject(params, url);
    }


    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }

}

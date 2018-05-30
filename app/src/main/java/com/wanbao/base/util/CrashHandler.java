package com.wanbao.base.util;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.wanbao.base.event.QuitEvent;

import org.greenrobot.eventbus.EventBus;

import java.lang.Thread.UncaughtExceptionHandler;


/**
 * UncaughtException处理�?,当程序发生Uncaught异常的时�?,有该类来接管程序,并记录发送错误报�?.
 *
 * @author user
 */
public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    // 系统默认的UncaughtException处理�?
    private UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;

    /**
     * 保证只有�?个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始�?
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理�?
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理�?
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处�?
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            EventBus.getDefault().post(new QuitEvent());
            System.exit(0);
        }
    }

    /**
     * 自定义错误处�?,收集错误信息 发�?�错误报告等操作均在此完�?.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        Log.e("wanbaoproject", Log.getStackTraceString(ex));
//		// 使用Toast来显示异常信�?
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        });
        return true;
    }

}

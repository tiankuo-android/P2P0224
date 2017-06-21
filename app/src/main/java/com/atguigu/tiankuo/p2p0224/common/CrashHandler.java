package com.atguigu.tiankuo.p2p0224.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/21 0021.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //设置单例
    //1.私有化构造器
    private CrashHandler() {
    }

    //2.创建实例
    private static CrashHandler crashHandler = new CrashHandler();

    //3.构建一个方法 用来返回实例
    public static CrashHandler getCrashHandler() {
        return crashHandler;
    }

    private Context context;

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.d("crash", "uncaughtException: " + e.getMessage());

        //1.提醒用户
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "软件出现问题，即将爆炸，请立刻扔掉手机", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        //2.收集异常
        collection(e);
        //3.退出应用
        AppManager.getInstance().removeAll();
            //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
            //添加参数，除了0以外都是非法退出
        System.exit(0);//退出虚拟机
    }

    private void collection(Throwable e) {
        String version = Build.DEVICE;
    }
}
package com.atguigu.tiankuo.p2p0224.common;

import android.app.Application;
import android.content.Context;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/21 0021.
 */

public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        context = this;
        //初始化CrashHandler
        CrashHandler.getCrashHandler().init(this);
    }
}

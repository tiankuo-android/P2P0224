package com.atguigu.tiankuo.p2p0224.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/21 0021.
 */

public class MyApplication extends Application {
    public static Context getContext() {
        return context;
    }
    private static Context context;
    private static Handler handler;
    private static int pid;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        context = this;
        //初始化crashHandler
        //CrashHandler.getInstance().init(this);

        initGallery();

        handler = new Handler();
        pid = android.os.Process.myPid();


    }

    /*
    * 配置gallery
    * */
    private void initGallery() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new PicassoImageLoader();
        CoreConfig coreConfig =
                new CoreConfig.Builder(this, imageloader, theme)
                        .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);
    }

    public static int getPid(){
        return pid;
    }

    public static Handler getHandler(){
        return handler;
    }
}

package com.atguigu.tiankuo.p2p0224.utils;

import android.content.Context;
import android.view.View;

import com.atguigu.tiankuo.p2p0224.common.MyApplication;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/22 0022.
 */

public class UIUtils {

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    private static Context getContext() {
        return MyApplication.getContext();
    }

    public static String stringFormat(int id, String value) {
        String versionName = String.format(getString(id), value);
        return versionName;
    }

    private static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    //与屏幕分辨率相关的
        public static int dp2px(int dp){
                float density = getContext().getResources().getDisplayMetrics().density;
                return (int) (density * dp + 0.5);
        
                    }
    
                public static int px2dp(int px){
                float density = getContext().getResources().getDisplayMetrics().density;
                return (int) (px / density + 0.5);
            }
}

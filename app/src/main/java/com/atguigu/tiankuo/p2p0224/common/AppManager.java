package com.atguigu.tiankuo.p2p0224.common;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/21 0021.
 */

public class AppManager {

    //创建工具类--首先设置单例

    //1.私有化构造器
    private AppManager() {
    }

    //2.创建实例
    private static AppManager appManager = new AppManager();

    //3.创建一个方法 用来返回实例
    public static AppManager getInstance() {
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    //添加Activity
    public void addActivity(Activity activity) {
        //校验
        if (activity != null) {
            stack.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {

            for (int i = stack.size() - 1; i > 0; i--) {
                Activity currentActivity = stack.get(i);

                if (currentActivity == activity) {
                    currentActivity.finish();
                    stack.remove(i);
                }
            }
        }
    }

    public void removeAll(){
        for(int i = stack.size() - 1; i > 0; i--) {

            Activity currentActivity = stack.get(i);
            
            if(currentActivity != null) {
                currentActivity.finish();
                stack.remove(i);
            }
        }
    }

}

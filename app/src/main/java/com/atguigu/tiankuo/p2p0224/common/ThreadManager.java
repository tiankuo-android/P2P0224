package com.atguigu.tiankuo.p2p0224.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/22 0022.
 */

public class ThreadManager {

    //设置单例
    //1.私有化构造器
    private ThreadManager() {
    }

    //2.创建实例
    private static ThreadManager threadManager = new ThreadManager();

    //3.构建一个方法 用来返回实例
    public static ThreadManager getThreadManager() {
        return threadManager;
    }

    // (1) newCachedThreadPool
    //  创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    //
    // (2) newFixedThreadPool
    //  创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    //
    // (3)  newScheduledThreadPool
    //  创建一个定时线程池，支持定时及周期性任务执行
    //
    //  (4) newSingleThreadExecutor
    //  创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ExecutorService getThread(){
        return executorService;
    }
}

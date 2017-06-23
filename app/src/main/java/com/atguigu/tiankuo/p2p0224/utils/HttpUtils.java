package com.atguigu.tiankuo.p2p0224.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/23 0023.
 */

public class HttpUtils {
    private AsyncHttpClient httpClient;

    private HttpUtils() {
        httpClient = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance() {
        return httpUtils;
    }

    public void get(String url, final OnHttpClientListener onHttpClientListener) {
        httpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                if (onHttpClientListener != null) {
                    onHttpClientListener.onSuccess(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (onHttpClientListener != null) {
                    onHttpClientListener.onFailure(content);
                }
            }
        });
    }

    public interface OnHttpClientListener {
        void onSuccess(String content);

        void onFailure(String content);
    }
}

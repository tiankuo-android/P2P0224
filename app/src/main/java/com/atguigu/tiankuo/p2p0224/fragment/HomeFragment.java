package com.atguigu.tiankuo.p2p0224.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.bean.IndexBean;
import com.atguigu.tiankuo.p2p0224.common.AppNetConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/20 0020.
 */

public class HomeFragment extends Fragment {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.banner)
    com.youth.banner.Banner banner;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initView() {

    }

    private List<String> list = new ArrayList<>();

    private void initData() {
        loadNet();
        initbanner();
    }

    private void loadNet() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);

//                try {
//                    parseJson(content);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                //解析数据
                IndexBean indexBean = JSON.parseObject(content, IndexBean.class);
                Log.d("content", "onSuccess: " + indexBean.getProInfo().getName());
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    private void parseJson(String content) throws JSONException {
        //创建IndexBean对象
        IndexBean indexBean = new IndexBean();
        //创建一个集合
        List<IndexBean.ImageArrBean> list = new ArrayList<>();
        //解析最外层数据
        JSONObject jsonObject = new JSONObject(content);
        //获取对象中的第一个元素
        JSONArray array = jsonObject.getJSONArray("imageArr");
        for (int i = 0; i < array.length(); i++) {
            //创建一个集合中的子元素
            IndexBean.ImageArrBean imageArrBean = new IndexBean.ImageArrBean();
            //获取子元素数组中的对象
            JSONObject jsonObject1 = array.getJSONObject(i);
            //解析数组中对象的元素
            String id = jsonObject.getString("ID");
            imageArrBean.setID(id);

            String imapaurl = jsonObject.getString("IMAPAURL");
            imageArrBean.setIMAPAURL(imapaurl);

            String imaurl = jsonObject.getString("IMAURL");
            imageArrBean.setIMAURL(imaurl);

            //把数组元素的对象添加到集合当中
            list.add(imageArrBean);
        }
        indexBean.getImageArr(list);

        //获取第二个元素
        JSONObject proInfo = jsonObject.getJSONObject("proInfo");
        String name = proInfo.getString("name");
        int id = proInfo.getInt("id");
        Log.d("json", "parseJson: " + name);

    }

    private void initbanner() {
        list.add(AppNetConfig.BASE_URL + "images/index02.png");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用结束时调用
        banner.start();
    }

    private void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}

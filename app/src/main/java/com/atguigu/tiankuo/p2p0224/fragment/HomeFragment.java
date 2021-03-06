package com.atguigu.tiankuo.p2p0224.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseFragment;
import com.atguigu.tiankuo.p2p0224.bean.IndexBean;
import com.atguigu.tiankuo.p2p0224.common.AppNetConfig;
import com.atguigu.tiankuo.p2p0224.view.ProgressView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
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

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @InjectView(R.id.proView)
    ProgressView proView;
    @Override
    public String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    public void setContent(String json) {
        //解析数据
        IndexBean indexBean = JSON.parseObject(json, IndexBean.class);
        initBanner(indexBean);
        initProgressView(indexBean);
    }

    @Override
    public void initTitle() {
        baseTitle.setText("首页");
    }


    private List<String> list = new ArrayList<>();


    public void initData() {

        loadNet();
    }


    private void loadNet() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//
////                try {
////                    parseJson(content);
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//                //解析数据
//                IndexBean indexBean = JSON.parseObject(content, IndexBean.class);
//                initBanner(indexBean);
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                Log.d("content", "onFailure: "+content);
//            }
//        });

//        HttpUtils.getInstance().get(AppNetConfig.INDEX,
//                new HttpUtils.OnHttpClientListener() {
//                    @Override
//                    public void onSuccess(String json) {
//                        //解析数据
//                        IndexBean indexBean = JSON.parseObject(json, IndexBean.class);
//                        initBanner(indexBean);
//                        initProgressView(indexBean);
//                    }
//
//                    @Override
//                    public void onFailure(String message) {
//
//                    }
//                });
    }

    private void initProgressView(IndexBean indexBean) {

        String progress = indexBean.getProInfo().getProgress();

        proView.setSweepAngle(Integer.parseInt(progress));
    }


    //    * 手动解析
//    *
    private void parseJson(String content) throws JSONException {
        //创建IndexBean对象
        IndexBean bean = new IndexBean();
        //创建一个集合
        List<IndexBean.ImageArrBean> list = new ArrayList<>();
        //解析最外层的对象
        JSONObject object = new JSONObject(content);
        //获取对象中的第一个元素
        JSONArray imageArr = object.getJSONArray("imageArr");
        for (int i = 0; i < imageArr.length(); i++) {
            //创建一个集合里面的子元素
            IndexBean.ImageArrBean imageArrBean = new IndexBean.ImageArrBean();
            //获取子元素数组中的对象
            JSONObject jsonObject = imageArr.getJSONObject(i);
            //解析数组中对象的元素
            String id = jsonObject.getString("ID");
            imageArrBean.setID(id);
            String imapaurl = jsonObject.getString("IMAPAURL");
            imageArrBean.setIMAPAURL(imapaurl);
            String imaurl = jsonObject.getString("IMAURL");
            imageArrBean.setIMAURL(imaurl);
            //把数组元素的对象添加到集合中
            list.add(imageArrBean);
        }

        bean.setImageArr(list);

        //获取对象中的第二个元素
        JSONObject proInfo = object.getJSONObject("proInfo");
        String name = proInfo.getString("name");
        int id = proInfo.getInt("id");
        Log.d("json", "parseJson: " + name);

    }


//    * 初始化banner
//    *

    private void initBanner(IndexBean indexBean) {
        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();
        for (int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            list.add(AppNetConfig.BASE_URL + imaurl);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    //    * 加载图片类
//    *
//    *
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}

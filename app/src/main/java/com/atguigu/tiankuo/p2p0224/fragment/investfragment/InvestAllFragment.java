package com.atguigu.tiankuo.p2p0224.fragment.investfragment;

import android.widget.ListAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.adapter.ImpAdapter3;
import com.atguigu.tiankuo.p2p0224.base.BaseFragment;
import com.atguigu.tiankuo.p2p0224.bean.ProductBean;
import com.atguigu.tiankuo.p2p0224.common.AppNetConfig;

import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/24 0024.
 */

public class InvestAllFragment extends BaseFragment {
    @InjectView(R.id.lv)
    ListView lv;

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    public void setContent(String json) {
        super.setContent(json);


        ProductBean productBean = JSON.parseObject(json, ProductBean.class);

        lv.setAdapter((ListAdapter) new ImpAdapter3(getActivity(),productBean.getData()));

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest_all;
    }
}

package com.atguigu.tiankuo.p2p0224.adapter;

import android.content.Context;
import android.view.View;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.adapter.base.ProductAdapter2;
import com.atguigu.tiankuo.p2p0224.bean.ProductBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public class ImpAdapter2 extends ProductAdapter2<ProductBean.DataBean> {



    public ImpAdapter2(Context context, List<ProductBean.DataBean> list) {
        super(context, list);
    }


    @Override
    protected void setData(ProductBean.DataBean dataBean) {
//        pName.setText(dataBean.getName());
    }

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.product_item, null);
        return view;
    }
}

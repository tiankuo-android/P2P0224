package com.atguigu.tiankuo.p2p0224.holder;

import android.view.View;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.bean.ProductBean;
import com.atguigu.tiankuo.p2p0224.utils.UIUtils;


/**
 * Created by Administrator on 2017/6/24.
 */

public class ProductHolder extends BaseHolder<ProductBean.DataBean> {


    @Override
    public View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.product_item, null);
    }

    @Override
    protected void setContent(ProductBean.DataBean dataBean) {

//        pName.setText(dataBean.getName());
    }
}

package com.atguigu.tiankuo.p2p0224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.adapter.base.ProductAdapter1;
import com.atguigu.tiankuo.p2p0224.bean.ProductBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/6/24.
 */

public class ImpAdapter1 extends ProductAdapter1<ProductBean.DataBean> {

    public ImpAdapter1(Context context, List<ProductBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public View getChildView(int position,
                             View convertView, ViewGroup parent,
                             ProductBean.DataBean dataBean) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.product_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.pName.setText(dataBean.getName());

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.p_name)
        TextView pName;
        @InjectView(R.id.p_money)
        TextView pMoney;
        @InjectView(R.id.p_yearlv)
        TextView pYearlv;
        @InjectView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @InjectView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @InjectView(R.id.p_minnum)
        TextView pMinnum;
        @InjectView(R.id.p_progresss)
        com.atguigu.tiankuo.p2p0224.view.ProgressView pProgresss;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

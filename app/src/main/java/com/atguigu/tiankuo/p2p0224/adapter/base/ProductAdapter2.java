package com.atguigu.tiankuo.p2p0224.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */


/*
*
* 第一种抽取方法 只是在getView中添加了一个抽象方法
*  注意：
*       返回view
*
* */
public abstract class ProductAdapter2<T>  extends BaseAdapter{

    public Context context;
    public List<T> list;

    public ProductAdapter2(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


         /*
        *
        * 优化三步
        * 第一步 抽出initView
        * 第二步 抽出setTag
        * 第三步 抽出设置数据
        *
        * */


       ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = initView();
            viewHolder = new ViewHolder(convertView);
            //convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //ProductBean.DataBean dataBean = list.get(position);

        //viewHolder.pName.setText(dataBean.getName());

        setData(list.get(position));
        return convertView;
    }

    protected abstract void setData(T t);

    protected abstract View initView();

    class ViewHolder {


        ViewHolder(View view) {
            view.setTag(this);
        }
    }
}

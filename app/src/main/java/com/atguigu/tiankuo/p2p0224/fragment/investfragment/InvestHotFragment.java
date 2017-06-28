package com.atguigu.tiankuo.p2p0224.fragment.investfragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseFragment;
import com.atguigu.tiankuo.p2p0224.utils.UIUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/24 0024.
 */

public class InvestHotFragment extends BaseFragment {

    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private String[] datas = new String[]{
            "新手福利计划", "财神道90天计划", "硅谷钱包计划",
            "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划",
            "大学老师购买车辆", "屌丝下海经商计划"
    };

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        idFlowlayout.setAdapter(new TagAdapter<String>(datas){

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = new TextView(getActivity());
                textView.setTextSize(15);
                textView.setTextColor(Color.WHITE);
                //获取shape布局的实例对象
                Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
                textView.setBackgroundDrawable(drawable);
                //获取drawable的背景
                GradientDrawable gd = (GradientDrawable) textView.getBackground();
                //设置背景色
                gd.setColor(UIUtils.getColor());
                textView.setText(s);

                return textView;
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest_hot;
    }

}

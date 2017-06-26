package com.atguigu.tiankuo.p2p0224.fragment.investfragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/20 0020.
 */

public class InvestFragment extends BaseFragment {

    @InjectView(R.id.tv_invest_all)
    TextView tvInvestAll;
    @InjectView(R.id.tv_invest_rem)
    TextView tvInvestRem;
    @InjectView(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @InjectView(R.id.vp)
    ViewPager vp;
    private List<BaseFragment> listFragment;

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {
        super.initListener();
        //viewpager监听
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTv(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void selectTv(int position) {
        switch (position) {
            case 0 :
                setSelectTv(tvInvestAll);
                break;
            case 1 :
                setSelectTv(tvInvestRem);
                break;
            case 2 :
                setSelectTv(tvInvestHot);
                break;
        }
    }

    @Override
    public void initData() {
        initViewPager();
        //设置默认选中的页面
        setSelectTv(tvInvestAll);
    }

    private void setSelectTv(View view) {
        setDefaultAll(tvInvestAll);
        setDefaultAll(tvInvestHot);
        setDefaultAll(tvInvestRem);

        TextView textView = (TextView) view;
        textView.setTextColor(Color.GREEN);
        textView.setBackgroundColor(Color.GRAY);
    }

    private void setDefaultAll(View view) {
        TextView textView = (TextView) view;
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundColor(Color.WHITE);
    }

    private void initViewPager() {
        listFragment = new ArrayList<>();
        listFragment.add(new InvestAllFragment());
        listFragment.add(new InvestReFragment());
        listFragment.add(new InvestHotFragment());
        vp.setAdapter(new MyAdapter(getFragmentManager()));
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @OnClick({R.id.tv_invest_all, R.id.tv_invest_rem, R.id.tv_invest_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_invest_all:
                vp.setCurrentItem(0);
                break;
            case R.id.tv_invest_rem:
                vp.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot:
                vp.setCurrentItem(2);
                break;
        }
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return listFragment.size();

        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

    }
}

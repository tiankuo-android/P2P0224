package com.atguigu.tiankuo.p2p0224.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/23 0023.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            TextView textView = new TextView(getActivity());
            textView.setText("小白");
            return textView;
        }
        View view = View.inflate(getActivity(), getLayoutId(), null);

        ButterKnife.inject(this, view);

        initView();
        initData();
        initTitle();
        initListener();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initTitle();

    public void initListener() {

    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutId();
}
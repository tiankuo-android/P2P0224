package com.atguigu.tiankuo.p2p0224.fragment.propertyfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.acticity.IconSettingsActivity;
import com.atguigu.tiankuo.p2p0224.acticity.MainActivity;
import com.atguigu.tiankuo.p2p0224.base.BaseFragment;
import com.atguigu.tiankuo.p2p0224.common.AppNetConfig;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.UnsupportedEncodingException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/20 0020.
 */

public class PropertyFragment extends BaseFragment {
    @InjectView(R.id.tv_settings)
    TextView tvSettings;
    @InjectView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @InjectView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @InjectView(R.id.tv_me_name)
    TextView tvMeName;
    @InjectView(R.id.rl_me)
    RelativeLayout rlMe;
    @InjectView(R.id.recharge)
    ImageView recharge;
    @InjectView(R.id.withdraw)
    ImageView withdraw;
    @InjectView(R.id.ll_touzi)
    TextView llTouzi;
    @InjectView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @InjectView(R.id.ll_zichan)
    TextView llZichan;
    private MainActivity mainActivity;

    @Override
    public String getChildUrl() {
        return "";
    }

    @Override
    public void setContent(String json) {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {
        super.initListener();

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //起动设置界面
                startActivity(new Intent(getActivity(), IconSettingsActivity.class));
            }
        });

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PayActivity.class));
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WidthDrawActivity.class));
            }
        });

        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TouZiActivity.class));
            }
        });

        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TouZiZhiGuanActivity.class));
            }
        });

        llZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ZiChanActivity.class));
            }
        });


    }

    @Override
    public void initData() {
//        Picasso.with(getActivity())
//                .load(AppNetConfig.BASE_URL+"images/tx.png")
//
//                .transform(new Transformation() {
//                    @Override
//                    public Bitmap transform(Bitmap bitmap) {
//
//                        return BitmapUtils.getBitmap(bitmap);
//                    }
//
//                    @Override
//                    public String key() {
//                        return "CropCircleTransformation()";
//                    }
//                })
//                .into(ivMeIcon);

        mainActivity = (MainActivity) getActivity();


        try {
            String value = mainActivity.getUser().getName();
            String name = new String(value.getBytes("UTF-8"));
            tvMeName.setText(name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /*
    *
    * 当页面再次显示UI的时候 会调用此方法
    * */
    @Override
    public void onResume() {
        super.onResume();

        String image = mainActivity.getImage();

        /*
        * 判断加载网络图片还是本地图片
        * */
        if (TextUtils.isEmpty(image)) {
            //加载头像
            Picasso.with(getActivity())
                    .load(AppNetConfig.BASE_URL + "images/tx.png")

                    .transform(new CropCircleTransformation())
                    .into(ivMeIcon);
        } else {
            //加载头像
            Picasso.with(getActivity())
                    .load(new File(image))
                    .transform(new CropCircleTransformation())
                    .into(ivMeIcon);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

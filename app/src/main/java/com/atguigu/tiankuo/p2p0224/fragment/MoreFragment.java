package com.atguigu.tiankuo.p2p0224.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.activity.MainActivity;
import com.atguigu.tiankuo.p2p0224.activity.RegisterActivity;
import com.atguigu.tiankuo.p2p0224.activity.gesture.GestureEditActivity;
import com.atguigu.tiankuo.p2p0224.base.BaseFragment;
import com.atguigu.tiankuo.p2p0224.common.AppNetConfig;
import com.atguigu.tiankuo.p2p0224.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/20 0020.
 */

public class MoreFragment extends BaseFragment {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @InjectView(R.id.toggle_more)
    ToggleButton toggleMore;
    @InjectView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @InjectView(R.id.tv_more_phone)
    TextView tvMorePhone;
    @InjectView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @InjectView(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_about)
    TextView tvMoreAbout;
    private MainActivity activity;

    @Override
    public String getChildUrl() {
        return "";
    }

    @Override
    public void initTitle() {
        baseTitle.setText("设置");
    }

    @Override
    public void initListener() {
        super.initListener();

        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断当前的开关
                if (isChecked) {
                    //开
                    if (activity.get("settings")) {
                        //设置过密码
                        activity.save("toggle", isChecked);
                    } else {
                        //设置密码
                        activity.save("settings", true);
                        activity.save("toggle", isChecked);
                        //没有设置过密码
                        startActivity(new Intent(getActivity(), GestureEditActivity.class));
                    }
                } else {
                    //关
                    activity.save("toggle", isChecked);
                }
            }
        });

        /*
        * 手势密码更改
        * */
        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity.get("toggle")) {
                    startActivity(new Intent(getActivity(), GestureEditActivity.class));
                } else {
                    activity.showToast("您没有打开手势密码");
                }
            }
        });

        tvMoreFankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = View.inflate(getActivity(),R.layout.dailog_view,null);
                final RadioGroup fankui = (RadioGroup) view.findViewById(R.id.rg_fankui);
                final EditText et = (EditText) view.findViewById(R.id.et_fankui_content);

                new AlertDialog.Builder(getActivity())
                        .setView(view)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //获取数据，然后发送给服务
                                fankui.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                                        RadioButton rb =
                                                (RadioButton) view.findViewById(checkedId);
                                        String department = rb.getText().toString().trim();
                                        String message = et.getText().toString();

                                        Map<String,String> map = new HashMap<String, String>();
                                        map.put("department",department);
                                        map.put("content",message);
                                        HttpUtils.getInstance().post(AppNetConfig.FEEDBACK, map, new HttpUtils.OnHttpClientListener() {
                                            @Override
                                            public void onSuccess(String json) {
                                                Log.d("more", "onSuccess: "+json);
                                            }

                                            @Override
                                            public void onFailure(String message) {
                                                Log.d("more", "onSuccess: "+message);
                                            }
                                        });

                                    }
                                });

                            }
                        })
                        .show();
            }
        });

        rlMoreContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MoreFragment.this.getActivity())
                        .setTitle("联系客服")
                        .setMessage("是否联系客服010-56253825")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);

                                Uri uri = Uri.parse("tel:010-56253825");
                                intent.setData(uri);
                                try {
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });

    }

    @Override
    public void initData() {
        //设置toggle状态
        activity = (MainActivity) getActivity();
        toggleMore.setChecked(activity.get("toggle"));

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

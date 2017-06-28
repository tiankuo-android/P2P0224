package com.atguigu.tiankuo.p2p0224.acticity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseActivity;
import com.atguigu.tiankuo.p2p0224.common.AppManager;
import com.atguigu.tiankuo.p2p0224.utils.UIUtils;

import butterknife.InjectView;

public class WelcomeActivity extends BaseActivity {


    @InjectView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @InjectView(R.id.tv_welcome_version)
    TextView tvWelcomeVersion;
    @InjectView(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //设置动画
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断账号是否登录过，如果没有登录跳转到登录界面，或者跳转到主界面
                if (isLogin()){
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));

                }else{
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));

                }
                //清除动画
                ivWelcomeIcon.clearAnimation();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivWelcomeIcon.startAnimation(animation);
    }

    /*
   * 判断是否登录过
   * */
    private boolean isLogin() {
        String name = getUser().getName();
        if (name.equals("admin")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void initView() {
        //第一个参数是 含有占位字符的字符串 第二个参数是占位字符的值
        tvWelcomeVersion.setText(
                UIUtils.stringFormat(
                        R.string.splash_version,
                        getVersionCode()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }
    /*
       * 获取版本号
       * */
    private String getVersionCode() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            //versionCode应用市场用来区分版本有没有更新
            int versionCode = info.versionCode;
            //versionName是给用户看的
            String versionName = info.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "3";
    }
}

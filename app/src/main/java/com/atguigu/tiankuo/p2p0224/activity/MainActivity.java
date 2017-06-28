package com.atguigu.tiankuo.p2p0224.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseActivity;
import com.atguigu.tiankuo.p2p0224.common.AppManager;
import com.atguigu.tiankuo.p2p0224.fragment.HomeFragment;
import com.atguigu.tiankuo.p2p0224.fragment.MoreFragment;
import com.atguigu.tiankuo.p2p0224.fragment.propertyfragment.PropertyFragment;
import com.atguigu.tiankuo.p2p0224.fragment.investfragment.InvestFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {


    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.tb_home)
    RadioButton tbHome;
    @InjectView(R.id.tb_invest)
    RadioButton tbInvest;
    @InjectView(R.id.tb_more)
    RadioButton tbMore;
    @InjectView(R.id.tb_propert)
    RadioButton tbPropert;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    @Override
    public void initListener() {
        //radioGroup监听
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switchFrgment(checkedId);
            }
        });
    }

    /*
   * 切换fragment
   *
   * */
    public void switchFrgment(int checkedId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);

        switch (checkedId){
            case R.id.tb_invest:
                if (investFragment == null){
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main,investFragment);
                }else{
                    transaction.show(investFragment);
                }
                break;
            case R.id.rg_main:
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
            case R.id.tb_propert:
                if (propertyFragment == null){
                    propertyFragment = new PropertyFragment();
                    transaction.add(R.id.fl_main,propertyFragment);
                }else{
                    transaction.show(propertyFragment);
                }
                break;
            case R.id.tb_more:
                if (moreFragment == null){
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main,moreFragment);
                }else{
                    transaction.show(moreFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (investFragment != null){
            transaction.hide(investFragment);
        }
        if (moreFragment != null){
            transaction.hide(moreFragment);
        }
        if (homeFragment != null){
            transaction.hide(homeFragment);
        }
        if (propertyFragment != null){
            transaction.hide(propertyFragment);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        switchFrgment(R.id.rg_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    /*
   * 双击退出
   * */
    private boolean isExit = false;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            if (isExit){
                finish();
            }
            Toast.makeText(this, "臭不要脸不要摸我", Toast.LENGTH_SHORT).show();
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
            return true;
        }
        return super.onKeyUp(keyCode,event);
    }
}

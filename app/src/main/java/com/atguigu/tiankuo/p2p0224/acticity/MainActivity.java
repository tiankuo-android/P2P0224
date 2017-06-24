package com.atguigu.tiankuo.p2p0224.acticity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.common.AppManager;
import com.atguigu.tiankuo.p2p0224.fragment.HomeFragment;
import com.atguigu.tiankuo.p2p0224.fragment.investfragment.InvestFragment;
import com.atguigu.tiankuo.p2p0224.fragment.MoreFragment;
import com.atguigu.tiankuo.p2p0224.fragment.PropertyFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


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

    private boolean isExit = false;
    private HomeFragment homeFragment;
    private MoreFragment moreFragment;
    private InvestFragment investFragment;
    private PropertyFragment propertyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        AppManager.getInstance().addActivity(this);
        //初始化控件
        initData();
        //初始化数据
        initView();
        //事件监听
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    private void initListener() {
        //radioGroup的监听
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId) {
                switchFragment(checkedId);
            }
        });
    }
    //切换Fragment
    public void switchFragment(int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);
        switch (checkedId) {
            case R.id.tb_home :
                if(homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
            case R.id.tb_invest :
                if(investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main,investFragment);
                }else{
                 transaction.show(investFragment);
                }
                break;
            case R.id.tb_more :
                if(moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main,moreFragment);
                }else{
                 transaction.show(moreFragment);
                }
                break;
            case R.id.tb_propert :
                if(propertyFragment == null) {
                    propertyFragment = new PropertyFragment();
                    transaction.add(R.id.fl_main,propertyFragment);
                }else{
                    transaction.show(propertyFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if(homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if(investFragment != null) {
            transaction.hide(investFragment);
        }
        if(moreFragment != null) {
            transaction.hide(moreFragment);
        }
        if(propertyFragment != null) {
            transaction.hide(propertyFragment);
        }
    }

    private void initView() {
        switchFragment(R.id.tb_home);

    }

    private void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isExit) {
                finish();
            }
            Toast.makeText(MainActivity.this, "点击两下退出", Toast.LENGTH_SHORT).show();
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                  isExit = false;
                }
            },2000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

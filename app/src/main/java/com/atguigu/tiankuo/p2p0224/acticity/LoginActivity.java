package com.atguigu.tiankuo.p2p0224.acticity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseActivity;
import com.atguigu.tiankuo.p2p0224.bean.LoginBean;
import com.atguigu.tiankuo.p2p0224.common.AppNetConfig;
import com.atguigu.tiankuo.p2p0224.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

public class LoginActivity extends BaseActivity {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_login_number)
    TextView tvLoginNumber;
    @InjectView(R.id.et_login_number)
    EditText etLoginNumber;
    @InjectView(R.id.rl_login)
    RelativeLayout rlLogin;
    @InjectView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @InjectView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.regitster_tv)
    TextView regitsterTv;

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("登录");
        baseBack.setVisibility(View.VISIBLE);
    }

    /*
    * 面试题
    * sp可以存哪些类型的数据？
    * sp存的数据最大是多少？
    * */
    @Override
    public void initListener() {

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取输入的账号和密码
                String number = etLoginNumber.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                //校验
                if (TextUtils.isEmpty(number)) {
                    showToast("账号不能为空");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
//                    if (pwd.length()>6){
//                        if ()//有没有非法字符
//                    }
                    showToast("密码不能为空");
                    return;
                }

                //登录
                Map<String, String> map = new HashMap<String, String>();
                map.put("phone", number);
                map.put("password", pwd);
                HttpUtils.getInstance().post(AppNetConfig.LOGIN, map, new HttpUtils.OnHttpClientListener() {
                    @Override
                    public void onSuccess(String json) {
                        // Log.d("json", "onSuccess: "+json);
                        try {
                            JSONObject obj = new JSONObject(json);
                            boolean isOk = obj.getBoolean("success");
                            if (isOk) {
                                //登录成功
                                JSONObject data = obj.getJSONObject("data");
                                String name = data.getString("name");
                                String imageurl = data.getString("imageurl");
                                String iscredit = data.getString("iscredit");
                                String phone = data.getString("phone");
                                LoginBean bean = new LoginBean();
                                bean.setIscredit(iscredit);
                                bean.setName(name);
                                bean.setPhone(phone);
                                bean.setImageurl(imageurl);
                                //存储数据
                                saveUser(bean);
                                //跳转
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                //结束自己
                                finish();
                            } else {
                                //登录失败（账号或者密码不对）
                                showToast("账号或者密码不对");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.d("json", "onFailure: " + message);
                    }
                });


            }
        });

        //注册监听
        regitsterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

}

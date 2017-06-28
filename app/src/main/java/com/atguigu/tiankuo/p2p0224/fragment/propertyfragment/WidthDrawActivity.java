package com.atguigu.tiankuo.p2p0224.fragment.propertyfragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.p2p0224.R;
import com.atguigu.tiankuo.p2p0224.base.BaseActivity;

import butterknife.InjectView;

public class WidthDrawActivity extends BaseActivity {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @InjectView(R.id.select_bank)
    RelativeLayout selectBank;
    @InjectView(R.id.chongzhi_text)
    TextView chongzhiText;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.et_input_money)
    EditText etInputMoney;
    @InjectView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @InjectView(R.id.textView5)
    TextView textView5;
    @InjectView(R.id.btn_tixian)
    Button btnTixian;

    @Override
    public void initTitle() {
        super.initTitle();
        baseBack.setVisibility(View.VISIBLE);
        baseTitle.setText("提现");
    }

    @Override
    public void initListener() {

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btnTixian.setClickable(false);
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                } else {
                    btnTixian.setClickable(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });

        btnTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("提现成功");
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
        return R.layout.activity_width_draw;
    }
}

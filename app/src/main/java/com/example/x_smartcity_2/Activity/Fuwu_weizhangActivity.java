package com.example.x_smartcity_2.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_2.Bean.GetViolationsByCarId;
import com.example.x_smartcity_2.R;

public class Fuwu_weizhangActivity extends AppCompatActivity {

    private GetViolationsByCarId carId;
    private ImageView back;
    private TextView title;
    private TextView didian;
    private TextView txtXingwei;
    private TextView txtShuhao;
    private TextView txtJifen;
    private TextView txt_fakuanjine;
    private TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwu_weizhang);
        carId = (GetViolationsByCarId) getIntent().getSerializableExtra("1");
        initView();
        title.setText("违章详情");
        didian.setText(carId.getPlace());
        txtXingwei.setText("违法行为："+carId.getDescription());
        txtShuhao.setText("通知书号："+carId.getNotification());
        txtJifen.setText("违章记分："+carId.getDeductPoints());
        txt_fakuanjine.setText("罚款金额："+carId.getCost());
        txtTime.setText("时间："+carId.getTime());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        didian = findViewById(R.id.didian);
        txtXingwei = findViewById(R.id.txt_xingwei);
        txtShuhao = findViewById(R.id.txt_shuhao);
        txtJifen = findViewById(R.id.txt_jifen);
        txt_fakuanjine = findViewById(R.id.txt_fakuanjine);
        txtTime = findViewById(R.id.txt_time);
    }
}
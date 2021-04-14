package com.example.worktreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonlibrary.constant.ArouterUrl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ArouterUrl.LIGHTWEBACURL)
                        .withString("common_uri","http://authentication-center.dev.ennewi.cn/auth?appid=dgb-api&redirect=http%3A%2F%2Fdgb-user-h5.dev.ennewi.cn%2F%23%2Fhome")
                        .withString("common_title","测试页面")
                        .navigation();
            }
        });
    }
}

package com.myubeo.appbanhang.View.ManHinhChao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    Intent intent = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
//                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();
    }
}

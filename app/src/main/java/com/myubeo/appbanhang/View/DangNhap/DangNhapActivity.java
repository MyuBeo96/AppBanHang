package com.myubeo.appbanhang.View.DangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.myubeo.appbanhang.Adapter.PagerAdapterDangNhap;
import com.myubeo.appbanhang.R;

/**
 * Created by as1 on 3/31/2018.
 */

public class DangNhapActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        tabLayout = findViewById(R.id.tl_DangNhap);
        viewPager = findViewById(R.id.vp_DangNhap);
        toolbar = findViewById(R.id.tb_DangNhap);

        setSupportActionBar(toolbar);

        PagerAdapterDangNhap pagerAdapterDangNhap = new PagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapterDangNhap);
        pagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}

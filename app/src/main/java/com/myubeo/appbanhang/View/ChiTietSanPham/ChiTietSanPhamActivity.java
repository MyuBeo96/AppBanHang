package com.myubeo.appbanhang.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.myubeo.appbanhang.Adapter.AdapterViewPagerSlider;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham{

    ViewPager viewPager;
    PresenterChiTietSanPham presenterChiTietSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        viewPager = findViewById(R.id.viewPagerSlideShow);

        int masp = getIntent().getIntExtra("masp", 0);
        presenterChiTietSanPham = new PresenterChiTietSanPham(this);
        presenterChiTietSanPham.LayChiTietSanPham(masp);
    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

    }

    @Override
    public void HienThiSlideAnh(String[] linkAnh) {

        List<Fragment> fragmentList = new ArrayList<>();

        for (int i = 0; i < linkAnh.length; i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkAnh", TrangChuActivity.SERVER + linkAnh[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }

        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();
    }
}

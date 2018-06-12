package com.myubeo.appbanhang.View.ChiTietSanPham;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myubeo.appbanhang.Adapter.AdapterViewPagerSlider;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener{

    ViewPager viewPager;
    PresenterChiTietSanPham presenterChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layout_Dots;
    List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        viewPager = findViewById(R.id.viewPagerSlideShow);

        layout_Dots = findViewById(R.id.layout_Dots);

        int masp = getIntent().getIntExtra("masp", 0);
        presenterChiTietSanPham = new PresenterChiTietSanPham(this);
        presenterChiTietSanPham.LayChiTietSanPham(masp);
    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

    }

    @Override
    public void HienThiSlideAnh(String[] linkAnh) {

        fragmentList = new ArrayList<>();

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

        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    private void ThemDotSlider(int viTriHienTai){
        txtDots = new TextView[fragmentList.size()];
        layout_Dots.removeAllViews();

        for (int i = 0; i < fragmentList.size(); i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActivity));

            layout_Dots.addView(txtDots[i]);
        }

        txtDots[viTriHienTai].setTextColor(getIdColor(R.color.colorSliderActivity));
    }

    private int getIdColor(int idColor){
        int color = 0;

        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this, idColor);
        }else {
            color = getResources().getColor(idColor);
        }

        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

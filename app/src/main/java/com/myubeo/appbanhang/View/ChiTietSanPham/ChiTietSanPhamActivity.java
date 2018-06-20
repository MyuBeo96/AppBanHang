package com.myubeo.appbanhang.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myubeo.appbanhang.Adapter.AdapterDanhGia;
import com.myubeo.appbanhang.Adapter.AdapterViewPagerSlider;
import com.myubeo.appbanhang.Model.ObjectClass.ChiTietSanPham;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.DanhGia.DanhGiaActivity;
import com.myubeo.appbanhang.View.DanhGia.ThemDanhGiaActivity;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener{

    ViewPager viewPager;
    PresenterChiTietSanPham presenterChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layout_Dots;
    List<Fragment> fragmentList;
    TextView txt_TenSanPham;
    TextView txt_GiaTien;
    TextView txt_TenCuaHangDongGoi;
    TextView txt_ThongTinChiTiet;
    Toolbar toolbar;
    ImageView img_ThemCT;
    Boolean kiemTraCT = false;
    LinearLayout ln_ThongSoKyThuat;
    TextView txt_VietDanhGia;
    int masp;
    List<DanhGia> danhGiaList;
    RecyclerView rcv_DanhGiaChiTiet;
    TextView txt_XemTatCaNhanXet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        viewPager = findViewById(R.id.viewPagerSlideShow);
        txt_TenSanPham = findViewById(R.id.txt_TenSanPham);
        txt_GiaTien = findViewById(R.id.txt_GiaTien);
        toolbar = findViewById(R.id.toolbar);
        layout_Dots = findViewById(R.id.layout_Dots);
        txt_TenCuaHangDongGoi = findViewById(R.id.txt_TenCuaHangDongGoi);
        txt_ThongTinChiTiet = findViewById(R.id.txt_ThongTinChiTiet);
        img_ThemCT = findViewById(R.id.img_ThemCT);
        ln_ThongSoKyThuat = findViewById(R.id.ln_ThongSoKyThuat);
        txt_VietDanhGia = findViewById(R.id.txt_VietDanhGia);
        rcv_DanhGiaChiTiet = findViewById(R.id.rcv_DanhGiaChiTiet);
        txt_XemTatCaNhanXet = findViewById(R.id.txt_XemTatCaNhanXet);

        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp", 0);
        presenterChiTietSanPham = new PresenterChiTietSanPham(this);
        presenterChiTietSanPham.LayChiTietSanPham(masp);
        presenterChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp, 0);

        txt_VietDanhGia.setOnClickListener(this);
        txt_XemTatCaNhanXet.setOnClickListener(this);
    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();

        txt_TenSanPham.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        txt_GiaTien.setText(gia + " VNĐ");

        txt_TenCuaHangDongGoi.setText(sanPham.getTENNV());
        txt_ThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));

        if(sanPham.getTHONGTIN().length() < 100){
            img_ThemCT.setVisibility(View.GONE);
        }else {
            img_ThemCT.setVisibility(View.VISIBLE);

            img_ThemCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemTraCT = !kiemTraCT;
                    if(kiemTraCT){
                        txt_ThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        img_ThemCT.setImageDrawable(getHinh(R.drawable.ic_keyboard_arrow_up_black));
                        ln_ThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiChiTiet(sanPham);
                    }else {
                        txt_ThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));
                        img_ThemCT.setImageDrawable(getHinh(R.drawable.ic_keyboard_arrow_down_black));
                        ln_ThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
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

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGiaList, 3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_DanhGiaChiTiet.setLayoutManager(layoutManager);
        rcv_DanhGiaChiTiet.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();

    }

    private void HienThiChiTiet(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhamList();
        ln_ThongSoKyThuat.removeAllViews();

        TextView txt_TieuDeChiTiet = new TextView(this);
        txt_TieuDeChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        txt_TieuDeChiTiet.setText("Thông số kỹ thuật");
        ln_ThongSoKyThuat.addView(txt_TieuDeChiTiet);

        for (int i = 0; i < chiTietSanPhamList.size(); i++){
            LinearLayout ln_ChiTiet = new LinearLayout(this);
            ln_ChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ln_ChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txt_TenChiTiet = new TextView(this);
            txt_TenChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txt_TenChiTiet.setText(chiTietSanPhamList.get(i).getTENCHITIET());

            TextView txt_GiaTri = new TextView(this);
            txt_GiaTri.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txt_GiaTri.setText(chiTietSanPhamList.get(i).getGIATRI());

            ln_ChiTiet.addView(txt_TenChiTiet);
            ln_ChiTiet.addView(txt_GiaTri);

            ln_ThongSoKyThuat.addView(ln_ChiTiet);
        }
    }

    private Drawable getHinh(int idDrawable){
        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable = ContextCompat.getDrawable(this, idDrawable);
        }else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txt_VietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("masp", masp);
                startActivity(iThemDanhGia);
                break;


                case R.id.txt_XemTatCaNhanXet:
                    Intent iDanhGiaSanPham = new Intent(ChiTietSanPhamActivity.this, DanhGiaActivity.class);
                    iDanhGiaSanPham.putExtra("masp", masp);
                    startActivity(iDanhGiaSanPham);
                    break;
        }
    }
}

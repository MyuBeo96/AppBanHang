package com.myubeo.appbanhang.View.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.myubeo.appbanhang.Adapter.AdapterDanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.ILoadMore;
import com.myubeo.appbanhang.Model.ObjectClass.LoadMoreScroll;
import com.myubeo.appbanhang.Presenter.DanhGia.PresenterDanhGia;
import com.myubeo.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

public class DanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {

    RecyclerView rcv_DanhGiaSanPham;
    ProgressBar progress_bar;
    int masp = 0;
    PresenterDanhGia presenterDanhGia;
    List<DanhGia> danhGias;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_danhgia);

        rcv_DanhGiaSanPham = findViewById(R.id.rcv_DanhGiaSanPham);
        progress_bar= findViewById(R.id.progress_bar);

        masp = getIntent().getIntExtra("masp", 0);

        danhGias = new ArrayList<>();

        presenterDanhGia = new PresenterDanhGia(this);
        presenterDanhGia.LayDanhSachDanhGiaSanPham(masp, 0, progress_bar);

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList) {
        danhGias.addAll(danhGiaList);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGias, 0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_DanhGiaSanPham.setLayoutManager(layoutManager);
        rcv_DanhGiaSanPham.setAdapter(adapterDanhGia);
        rcv_DanhGiaSanPham.addOnScrollListener(new LoadMoreScroll(layoutManager, this));

        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongItem) {
        presenterDanhGia.LayDanhSachDanhGiaSanPham(masp, tongItem, progress_bar);

    }
}

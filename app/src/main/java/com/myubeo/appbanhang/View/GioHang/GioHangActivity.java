package com.myubeo.appbanhang.View.GioHang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.myubeo.appbanhang.Adapter.AdapterGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.GioHang.PresenterGioHang;
import com.myubeo.appbanhang.R;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {

    RecyclerView recyclerView;
    PresenterGioHang presenterGioHang;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerView = findViewById(R.id.rcv_GioHang);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        presenterGioHang = new PresenterGioHang(this);
        presenterGioHang.LayDanhSachSPGioHang(this);

    }

    @Override
    public void HienThiDanhSachSPGioHang(List<SanPham> sanPhams) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhams);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
    }
}

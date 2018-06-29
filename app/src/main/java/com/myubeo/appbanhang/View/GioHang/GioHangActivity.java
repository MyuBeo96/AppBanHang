package com.myubeo.appbanhang.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.myubeo.appbanhang.Adapter.AdapterGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.GioHang.PresenterGioHang;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {

    RecyclerView recyclerView;
    PresenterGioHang presenterGioHang;
    Toolbar toolbar;
    Button btn_MuaHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerView = findViewById(R.id.rcv_GioHang);
        toolbar = findViewById(R.id.toolbar);
        btn_MuaHang = findViewById(R.id.btn_MuaHang);

        setSupportActionBar(toolbar);

        presenterGioHang = new PresenterGioHang(this);
        presenterGioHang.LayDanhSachSPGioHang(this);

        btn_MuaHang.setOnClickListener(this);

    }

    @Override
    public void HienThiDanhSachSPGioHang(List<SanPham> sanPhams) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhams);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_MuaHang:
                Intent iMuaHang = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(iMuaHang);
                break;
        }
    }
}

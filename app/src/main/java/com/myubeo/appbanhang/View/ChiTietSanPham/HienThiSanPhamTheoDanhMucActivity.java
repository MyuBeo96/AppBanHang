package com.myubeo.appbanhang.View.ChiTietSanPham;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.myubeo.appbanhang.Adapter.AdapterTopMayTinh;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.PresenterHienThiSPTheoTH;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.ViewHienThiSPTheoTH;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSPTheoTH, View.OnClickListener{

    RecyclerView recyclerView;
    Button btn_list;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterHienThiSPTheoTH hienThiSPTheoTH;
    int maTH;
    boolean kiemTra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthithuonghieusanpham);

        recyclerView = findViewById(R.id.rcv_hienThiDSSanPham);
        btn_list = findViewById(R.id.btn_list);

        Intent intent = getIntent();
        maTH = intent.getIntExtra("MATH", 0);
        String tenTh = intent.getStringExtra("TENTHUONGHIEU");
        kiemTra = intent.getBooleanExtra("KIEMTRA", false);

        hienThiSPTheoTH = new PresenterHienThiSPTheoTH(this);
        hienThiSPTheoTH.LayDanhSachSanPhamTheoMaLoai(maTH, kiemTra);

        btn_list.setOnClickListener(this);


    }

    @Override
    public void HienThiDanhSachSP(List<SanPham> sanPhamList) {
        AdapterTopMayTinh adapterChiTietMayTinh = new AdapterTopMayTinh(HienThiSanPhamTheoDanhMucActivity.this, sanPhamList);

        if(dangGrid){
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this, 2);
        }else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterChiTietMayTinh);
        adapterChiTietMayTinh.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSP() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_list:
                dangGrid = !dangGrid;
                hienThiSPTheoTH.LayDanhSachSanPhamTheoMaLoai(maTH, kiemTra);
                break;

        }
    }
}

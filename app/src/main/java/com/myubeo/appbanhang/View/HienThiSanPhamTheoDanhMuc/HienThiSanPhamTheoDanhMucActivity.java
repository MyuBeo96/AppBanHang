package com.myubeo.appbanhang.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myubeo.appbanhang.Adapter.AdapterTopMayTinh;
import com.myubeo.appbanhang.Model.ObjectClass.ILoadMore;
import com.myubeo.appbanhang.Model.ObjectClass.LoadMoreScroll;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ChiTietSanPham.PresenterChiTietSanPham;
import com.myubeo.appbanhang.Presenter.HienThiSanPhamTheoDanhMuc.PresenterHienThiSPTheoTH;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.ViewHienThiSPTheoTH;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSPTheoTH, View.OnClickListener, ILoadMore{

    RecyclerView recyclerView;
    Button btn_list;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterHienThiSPTheoTH hienThiSPTheoTH;
    int maTH;
    boolean kiemTra;
    AdapterTopMayTinh adapterChiTietMayTinh;
    Toolbar toolbar;
    List<SanPham> sanPhamList1;
    ProgressBar progressBar;
    TextView txt_GioHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthithuonghieusanpham);

        recyclerView = findViewById(R.id.rcv_hienThiDSSanPham);
        btn_list = findViewById(R.id.btn_list);

        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.prb_load);

        Intent intent = getIntent();
        maTH = intent.getIntExtra("MATH", 0);
        String tenTh = intent.getStringExtra("TENTHUONGHIEU");
        kiemTra = intent.getBooleanExtra("KIEMTRA", false);

        hienThiSPTheoTH = new PresenterHienThiSPTheoTH(this);
        hienThiSPTheoTH.LayDanhSachSanPhamTheoMaLoai(maTH, kiemTra);

        btn_list.setOnClickListener(this);

        toolbar.setTitle(tenTh);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        MenuItem menuItem = menu.findItem(R.id.id_Cart);
        View viewGioHang = MenuItemCompat.getActionView(menuItem);
        txt_GioHang = viewGioHang.findViewById(R.id.txt_SoLuongSP);

        PresenterChiTietSanPham presenterChiTietSanPham = new PresenterChiTietSanPham();
        txt_GioHang.setText(String.valueOf(presenterChiTietSanPham.DemSPGioHang(this)));

        return true;
    }

    @Override
    public void HienThiDanhSachSP(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;
//        adapterChiTietMayTinh = new AdapterTopMayTinh(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_recyclerview_laptop, sanPhamList);

        if(dangGrid){
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this, 2);
            adapterChiTietMayTinh = new AdapterTopMayTinh(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_recyclerview_laptop, sanPhamList1);

        }else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
            adapterChiTietMayTinh = new AdapterTopMayTinh(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_list_topdienthoaimaytinh, sanPhamList1);

        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterChiTietMayTinh);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
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

    @Override
    public void LoadMore(int tongItem) {
        List<SanPham> sanPhamsLoadMore = hienThiSPTheoTH.LayDanhSachSanPhamTheoMaLoaiLoadMore(maTH, kiemTra, tongItem, progressBar);
        sanPhamList1.addAll(sanPhamsLoadMore);

        adapterChiTietMayTinh.notifyDataSetChanged();
    }
}

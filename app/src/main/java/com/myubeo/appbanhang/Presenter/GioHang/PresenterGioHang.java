package com.myubeo.appbanhang.Presenter.GioHang;

import android.content.Context;

import com.myubeo.appbanhang.Model.GioHang.ModelGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterGioHang implements ImpPresenterGioHang{

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterGioHang(ViewGioHang viewGioHang){
        this.viewGioHang = viewGioHang;
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayDanhSachSPGioHang(Context context) {

        modelGioHang.MoKetNoiSQLite(context);

        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamGioHang();

        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSPGioHang(sanPhamList);
        }
    }
}

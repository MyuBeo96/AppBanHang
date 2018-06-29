package com.myubeo.appbanhang.Presenter.ThanhToan;

import android.content.Context;

import com.myubeo.appbanhang.Model.GioHang.ModelGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.HoaDon;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Model.ThanhToan.ModelThanhToan;
import com.myubeo.appbanhang.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterThanhToan implements ImpPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;

    public  PresenterThanhToan(ViewThanhToan viewThanhToan){
        this.viewThanhToan = viewThanhToan;

        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if(kiemtra){
            viewThanhToan.DatHangThanhCong();
        }else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSPGioHang(Context context) {
        modelGioHang.MoKetNoiSQLite(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}

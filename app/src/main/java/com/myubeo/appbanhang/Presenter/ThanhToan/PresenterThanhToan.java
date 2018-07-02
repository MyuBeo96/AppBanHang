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
    List<SanPham> sanPhamList;

    public  PresenterThanhToan(ViewThanhToan viewThanhToan, Context context){
        this.viewThanhToan = viewThanhToan;

        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQLite(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if(kiemtra){
            viewThanhToan.DatHangThanhCong();

            int dem = sanPhamList.size();

            for(int i = 0; i < dem; i++){
                modelGioHang.XoaSanPhamGioHang(sanPhamList.get(i).getMASP());
            }
        }else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSPGioHang() {
        sanPhamList = modelGioHang.LayDanhSachSanPhamGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}

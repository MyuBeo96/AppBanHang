package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import android.content.Context;

import com.myubeo.appbanhang.Model.ObjectClass.SanPham;

public interface ImpPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);
}

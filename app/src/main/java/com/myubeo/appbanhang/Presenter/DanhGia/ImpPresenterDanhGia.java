package com.myubeo.appbanhang.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;

public interface ImpPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaSanPham(int masp, int limit, ProgressBar progressBar);
}

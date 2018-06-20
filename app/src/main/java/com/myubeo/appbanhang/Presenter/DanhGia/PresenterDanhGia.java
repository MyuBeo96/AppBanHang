package com.myubeo.appbanhang.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.myubeo.appbanhang.Model.DanhGia.ModelDanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.View.DanhGia.ViewDanhGia;

import java.util.List;

public class PresenterDanhGia implements  ImpPresenterDanhGia {

    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterDanhGia(ViewDanhGia viewDanhGia){
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if(kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else {
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);

        List<DanhGia> danhGiaList = modelDanhGia.LayDanhSachDanhGiaSanPham(masp, limit);

        if(danhGiaList.size() > 0){
            progressBar.setVisibility(View.GONE);
            viewDanhGia.HienThiDanhSachDanhGiaTheoSP(danhGiaList);
        }
    }
}

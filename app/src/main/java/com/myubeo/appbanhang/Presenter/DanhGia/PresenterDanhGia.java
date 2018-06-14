package com.myubeo.appbanhang.Presenter.DanhGia;

import com.myubeo.appbanhang.Model.DanhGia.ModelDanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.View.DanhGia.ViewDanhGia;

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
}

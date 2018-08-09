package com.myubeo.appbanhang.Presenter.DangKy;

import com.myubeo.appbanhang.Model.DangNhap.ModelDangKy;
import com.myubeo.appbanhang.Model.ObjectClass.NhanVien;
import com.myubeo.appbanhang.View.DangNhap.ViewDangKy;

public class PresenterDangKy implements ImpPresenterDangKy {

    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterDangKy(ViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        modelDangKy.DangKyThanhVien(nhanVien);

    }
}

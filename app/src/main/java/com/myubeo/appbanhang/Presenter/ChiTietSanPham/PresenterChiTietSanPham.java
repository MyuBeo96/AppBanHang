package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import com.myubeo.appbanhang.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.ChiTietSanPham.ViewChiTietSanPham;

public class PresenterChiTietSanPham implements ImpPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVaChiTietTheoMaSP", "CHITIETSANPHAM", masp);
        if(sanPham.getMASP() > 0){
            String[] linkAnh = sanPham.getHINHNHO().split(",");
            viewChiTietSanPham.HienThiSlideAnh(linkAnh);
        }
    }
}

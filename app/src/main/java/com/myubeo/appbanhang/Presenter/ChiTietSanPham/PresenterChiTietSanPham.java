package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import com.myubeo.appbanhang.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterChiTietSanPham implements ImpPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPhamTheoMaSP("LaySanPhamVaChiTietTheoMaSP", "CHITIETSANPHAM", masp);
        if(sanPham.getMASP() > 0){
            String[] linkAnh = sanPham.getHINHNHO().split(",");
            viewChiTietSanPham.HienThiSlideAnh(linkAnh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = modelChiTietSanPham.LayDanhSachDanhGiaSanPham(masp, limit);

        if (danhGiaList.size() > 0){
            viewChiTietSanPham.HienThiDanhGia(danhGiaList);
        }
    }
}

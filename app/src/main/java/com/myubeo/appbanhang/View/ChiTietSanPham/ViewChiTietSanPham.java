package com.myubeo.appbanhang.View.ChiTietSanPham;

import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSlideAnh(String[] linkAnh);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
}

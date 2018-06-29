package com.myubeo.appbanhang.View.ThanhToan;

import com.myubeo.appbanhang.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhams);
}

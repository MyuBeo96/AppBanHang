package com.myubeo.appbanhang.View.TrangChu;

import com.myubeo.appbanhang.Model.ObjectClass.DienTu;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(List<DienTu> dienTuList);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList);
    void HienThiDanhSachSPMoiVe(List<SanPham> sanPhamList);
}

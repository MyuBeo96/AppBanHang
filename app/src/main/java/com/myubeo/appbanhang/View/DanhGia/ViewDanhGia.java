package com.myubeo.appbanhang.View.DanhGia;


import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList);
}

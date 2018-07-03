package com.myubeo.appbanhang.Presenter.KhuyenMai;

import com.myubeo.appbanhang.Model.KhuyenMai.ModelKhuyenMai;
import com.myubeo.appbanhang.Model.ObjectClass.KhuyenMai;
import com.myubeo.appbanhang.View.TrangChu.ViewKhuyenMai;

import java.util.List;

public class PresenterKhuyenMai implements ImpPresenterKhuyenMai {

    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSanPhamKhuyenMai("LayDanhSachKhuyenMai", "DANHSACHKHUYENMAI");
        if(khuyenMaiList.size() > 0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}

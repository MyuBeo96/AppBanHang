package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import com.myubeo.appbanhang.Model.ChiTietSanPham.ModelHienThiSanPhamTheoMaDanhMuc;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.TrangChu.ViewHienThiSPTheoTH;

import java.util.ArrayList;
import java.util.List;

public class PresenterHienThiSPTheoTH implements ImpPresenterHienThiSPTheoTH {

    ViewHienThiSPTheoTH viewHienThiSPTheoTH;
    ModelHienThiSanPhamTheoMaDanhMuc modelHienThiSanPhamTheoMaDanhMuc;

    public PresenterHienThiSPTheoTH(ViewHienThiSPTheoTH viewHienThiSPTheoTH){
        this.viewHienThiSPTheoTH = viewHienThiSPTheoTH;
        modelHienThiSanPhamTheoMaDanhMuc = new ModelHienThiSanPhamTheoMaDanhMuc();
    }

    @Override
    public void LayDanhSachSanPhamTheoMaLoai(int masp, boolean kiemTra) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemTra) {
            sanPhamList = modelHienThiSanPhamTheoMaDanhMuc.LayDanhSachSanPhamTheoMaLoaiSP(masp, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM",20);
        } else {
            sanPhamList = modelHienThiSanPhamTheoMaDanhMuc.LayDanhSachSanPhamTheoMaLoaiSP(masp, "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", 20);

        }
        if(sanPhamList.size() > 0){
            viewHienThiSPTheoTH.HienThiDanhSachSP(sanPhamList);
        }else {
            viewHienThiSPTheoTH.LoiHienThiDanhSachSP();
        }
    }
}

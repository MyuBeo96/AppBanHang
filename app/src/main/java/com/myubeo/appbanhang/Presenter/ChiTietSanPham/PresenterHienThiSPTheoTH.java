package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import android.view.View;
import android.widget.ProgressBar;

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
            sanPhamList = modelHienThiSanPhamTheoMaDanhMuc.LayDanhSachSanPhamTheoMaLoaiSP(masp, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM",0);
        } else {
            sanPhamList = modelHienThiSanPhamTheoMaDanhMuc.LayDanhSachSanPhamTheoMaLoaiSP(masp, "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", 0);

        }
        if(sanPhamList.size() > 0){
            viewHienThiSPTheoTH.HienThiDanhSachSP(sanPhamList);
        }else {
            viewHienThiSPTheoTH.LoiHienThiDanhSachSP();
        }
    }

    public List<SanPham> LayDanhSachSanPhamTheoMaLoaiLoadMore(int masp, boolean kiemTra, int limit, ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemTra) {
            sanPhamList = modelHienThiSanPhamTheoMaDanhMuc.LayDanhSachSanPhamTheoMaLoaiSP(masp, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM",limit);
        } else {
            sanPhamList = modelHienThiSanPhamTheoMaDanhMuc.LayDanhSachSanPhamTheoMaLoaiSP(masp, "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", limit);

        }

        if(sanPhamList.size() != 0){
            progressBar.setVisibility(View.GONE);
        }

        return sanPhamList;
    }
}

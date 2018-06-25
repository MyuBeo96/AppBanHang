package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import android.content.Context;

import com.myubeo.appbanhang.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.myubeo.appbanhang.Model.GioHang.ModelGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterChiTietSanPham implements ImpPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresenterChiTietSanPham(){
        modelGioHang = new ModelGioHang();
    }

    public PresenterChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
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

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQLite(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if(kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    public int DemSPGioHang(Context context) {
        modelGioHang.MoKetNoiSQLite(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamGioHang();

        int dem = sanPhamList.size();

        return dem;
    }
}

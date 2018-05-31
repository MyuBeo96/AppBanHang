package com.myubeo.appbanhang.Presenter.TrangChuDienTu;

import android.util.Log;
import android.view.View;

import com.myubeo.appbanhang.Model.ObjectClass.DienTu;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Model.ObjectClass.ThuongHieu;
import com.myubeo.appbanhang.Model.TrangDienTu.ModelDienTu;
import com.myubeo.appbanhang.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterDienTu implements ImpPresenterDienTu {

    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public PresenterDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTuList = new ArrayList<>();

        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieu("LayDanhSachThuongHieuLon", "DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopMayTinh", "TOPMAYTINH");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTenNoiBat("DANH SÁCH THƯƠNG HIỆU LỚN");
        dienTu.setTenTopNoiBat("TOP MÁY TÍNH");
        dienTu.setThuongHieu(true);
        dienTuList.add(dienTu);

        if(thuongHieuList.size() > 0 && sanPhamList.size() > 0){
            viewDienTu.HienThiDanhSach(dienTuList);
        }

    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieu("LayLogoThuongHieu", "DANHSACHLOGOTHUONGHIEU");

        if(thuongHieuList.size() > 0){
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        }
    }

    @Override
    public void LayDanhSachSanPhamMoiVe() {
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachSanPhamMoi", "DANHSACHSANPHAMMOIVE");

        if(sanPhamList.size() > 0){
            viewDienTu.HienThiDanhSachSPMoiVe(sanPhamList);
        }
    }
}

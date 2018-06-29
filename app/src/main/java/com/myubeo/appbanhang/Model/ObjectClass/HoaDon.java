package com.myubeo.appbanhang.Model.ObjectClass;

import java.util.List;

public class HoaDon {

    int MAHD;
    int CHUYENKHOAN;
    String NGAYDAT;
    String NGAYGIAO;
    String TRANGTHAI;
    String TENNGUOINHAN;
    String SODIENTHOAI;
    String DIACHI;
    String MACHUYENKHOAN;
    List<ChiTietHoaDon> chiTietHoaDons;

    public int getMAHD() {
        return MAHD;
    }

    public void setMAHD(int MAHD) {
        this.MAHD = MAHD;
    }

    public int getCHUYENKHOAN() {
        return CHUYENKHOAN;
    }

    public void setCHUYENKHOAN(int CHUYENKHOAN) {
        this.CHUYENKHOAN = CHUYENKHOAN;
    }

    public String getNGAYDAT() {
        return NGAYDAT;
    }

    public void setNGAYDAT(String NGAYDAT) {
        this.NGAYDAT = NGAYDAT;
    }

    public String getNGAYGIAO() {
        return NGAYGIAO;
    }

    public void setNGAYGIAO(String NGAYGIAO) {
        this.NGAYGIAO = NGAYGIAO;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getTENNGUOINHAN() {
        return TENNGUOINHAN;
    }

    public void setTENNGUOINHAN(String TENNGUOINHAN) {
        this.TENNGUOINHAN = TENNGUOINHAN;
    }

    public String getSODIENTHOAI() {
        return SODIENTHOAI;
    }

    public void setSODIENTHOAI(String SODIENTHOAI) {
        this.SODIENTHOAI = SODIENTHOAI;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getMACHUYENKHOAN() {
        return MACHUYENKHOAN;
    }

    public void setMACHUYENKHOAN(String MACHUYENKHOAN) {
        this.MACHUYENKHOAN = MACHUYENKHOAN;
    }

    public List<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }
}

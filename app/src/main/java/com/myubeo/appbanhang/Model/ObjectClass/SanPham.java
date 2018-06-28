package com.myubeo.appbanhang.Model.ObjectClass;

import java.util.List;

public class SanPham {
    int MASP;
    String TENSP;
    int GIA;
    String HINHLON;
    String HINHNHO;
    String THONGTIN;
    int SOLUONG;
    int MALOAISP;
    int MATHUONGHIEU;
    int LUOTMUA;
    int MANV;
    String TENNV;
    List<ChiTietSanPham> chiTietSanPhamList;
    byte[] hinhGioHang;
    int SOLUONGTONKHO;

    public int getSOLUONGTONKHO() {
        return SOLUONGTONKHO;
    }

    public void setSOLUONGTONKHO(int SOLUONGTONKHO) {
        this.SOLUONGTONKHO = SOLUONGTONKHO;
    }

    public byte[] getHinhGioHang() {
        return hinhGioHang;
    }

    public void setHinhGioHang(byte[] hinhGioHang) {
        this.hinhGioHang = hinhGioHang;
    }

    public String getTENNV() {
        return TENNV;
    }

    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public String getHINHLON() {
        return HINHLON;
    }

    public void setHINHLON(String HINHLON) {
        this.HINHLON = HINHLON;
    }

    public String getHINHNHO() {
        return HINHNHO;
    }

    public void setHINHNHO(String HINHNHO) {
        this.HINHNHO = HINHNHO;
    }

    public String getTHONGTIN() {
        return THONGTIN;
    }

    public void setTHONGTIN(String THONGTIN) {
        this.THONGTIN = THONGTIN;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMATHUONGHIEU() {
        return MATHUONGHIEU;
    }

    public void setMATHUONGHIEU(int MATHUONGHIEU) {
        this.MATHUONGHIEU = MATHUONGHIEU;
    }

    public int getLUOTMUA() {
        return LUOTMUA;
    }

    public void setLUOTMUA(int LUOTMUA) {
        this.LUOTMUA = LUOTMUA;
    }
}

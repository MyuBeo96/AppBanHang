package com.myubeo.appbanhang.Model.ObjectClass;

import java.util.List;

/**
 * Created by as1 on 3/29/2018.
 */

public class LoaiSanPham {
    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<LoaiSanPham> getLoaiSanPhams() {
        return loaiSanPhams;
    }

    public void setLoaiSanPhams(List<LoaiSanPham> loaiSanPhams) {
        this.loaiSanPhams = loaiSanPhams;
    }

    int MALOAISP;
    int MALOAICHA;
    String TENLOAISP;
    List<LoaiSanPham> loaiSanPhams;


}

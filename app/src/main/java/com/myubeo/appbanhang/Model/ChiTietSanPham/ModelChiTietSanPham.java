package com.myubeo.appbanhang.Model.ChiTietSanPham;

import android.util.Log;

import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.ChiTietKhuyenMai;
import com.myubeo.appbanhang.Model.ObjectClass.ChiTietSanPham;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

    public List<DanhGia> LayDanhSachDanhGiaSanPham(int masp, int limit){
        List<DanhGia> danhGiaArrayList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        String dataJSON = "";

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachDanhGiaTheoMaSP");

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(masp));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit",String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaSP);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("DANHGIASANPHAM");
            int count = jsonArray.length();
            for(int i = 0; i < count; i++){
                JSONObject value = jsonArray.getJSONObject(i);

                DanhGia dataDanhGia = new DanhGia();
                dataDanhGia.setMASP(value.getInt("MASP"));
                dataDanhGia.setMADG(value.getString("MADG"));
                dataDanhGia.setTENTHIETBI(value.getString("TENTHIETBI"));
                dataDanhGia.setTIEUDE(value.getString("TIEUDE"));
                dataDanhGia.setNOIDUNG(value.getString("NOIDUNG"));
                dataDanhGia.setNGAYDANHGIA(value.getString("NGAYDANHGIA"));
                dataDanhGia.setSOSAO(value.getInt("SOSAO"));
                danhGiaArrayList.add(dataDanhGia);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGiaArrayList;
    }

    public SanPham LayChiTietSanPhamTheoMaSP(String tenHam, String tenMang, int masp){
        SanPham sanPham = new SanPham();

        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        String dataJSON = "";

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenHam);

        HashMap<String,String> hsMasp = new HashMap<>();
        hsMasp.put("masp",String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            Log.d("Kiem tra", dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenMang);
            int count = jsonArray.length();
            for(int i = 0; i < count; i++){
                JSONObject value = jsonArray.getJSONObject(i);
                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(value.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                sanPham.setMASP(value.getInt("MASP"));
                sanPham.setTENSP(value.getString("TENSP"));
                sanPham.setTHONGTIN(value.getString("THONGTIN"));
                sanPham.setGIA(value.getInt("GIA"));
                sanPham.setHINHLON(value.getString("HINHLON"));
                sanPham.setHINHNHO(value.getString("HINHNHO"));
                sanPham.setSOLUONG(value.getInt("SOLUONG"));
                sanPham.setMALOAISP(value.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(value.getInt("MATHUONGHIEU"));
                sanPham.setLUOTMUA(value.getInt("LUOTMUA"));
                sanPham.setMANV(value.getInt("MANV"));
                sanPham.setTENNV(value.getString("TENNV"));

                JSONArray jsonArrayThongSo = value.getJSONArray("THONGSOKYTHUAT");
                for(int j = 0; j < jsonArrayThongSo.length(); j++){
                    JSONObject jsonObject1 = jsonArrayThongSo.getJSONObject(j);

                    for (int k = 0; k < jsonObject1.names().length(); k++){
                        String tenChiTiet = jsonObject1.names().getString(k);

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenChiTiet);
                        chiTietSanPham.setGIATRI(jsonObject1.getString(tenChiTiet));

                        chiTietSanPhamList.add(chiTietSanPham);
                    }
                }

                sanPham.setChiTietSanPhamList(chiTietSanPhamList);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }
}

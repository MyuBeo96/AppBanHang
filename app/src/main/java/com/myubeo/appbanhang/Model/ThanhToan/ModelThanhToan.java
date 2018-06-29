package com.myubeo.appbanhang.Model.ThanhToan;

import android.util.Log;

import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.ChiTietHoaDon;
import com.myubeo.appbanhang.Model.ObjectClass.HoaDon;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelThanhToan {

    public Boolean ThemHoaDon(HoaDon hoaDon){
        List<HashMap<String, String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        String link = TrangChuActivity.SERVER_NAME ;

        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDons();

        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";
        for (int i = 0; i < chiTietHoaDonList.size(); i++){
            chuoijson += "{";
            chuoijson += "\"masp\": " + chiTietHoaDonList.get(i).getMASP() + ",";
            chuoijson += "\"soluong\": " + chiTietHoaDonList.get(i).getSOLUONG();

            if(i == (chiTietHoaDonList.size() - 1)) {
                chuoijson += "}";
            }else {
                chuoijson += "},";
            }
        }
        chuoijson += "]}";

        Log.d("Chuoijson", chuoijson);

//        HashMap<String,String> hsHam = new HashMap<>();
//        hsHam.put("ham","ThemHoaDon");
//
//        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
//        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);
//
//        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
//        hsTenNguoiNhan.put("tennguoinhan",hoaDon.getTENNGUOINHAN());
//
//        HashMap<String,String> hsSoDT = new HashMap<>();
//        hsSoDT.put("sodt",hoaDon.getSODIENTHOAI());
//
//        HashMap<String,String> hsDiaChi = new HashMap<>();
//        hsDiaChi.put("diachi",hoaDon.getDIACHI());
//
//        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
//        hsChuyenKhoan.put("chuyenkhoan","0");
//
////        HashMap<String,String> hsMaChuyenKhoan = new HashMap<>();
////        hsMaChuyenKhoan.put("machuyenkhoan",hoaDon.getMACHUYENKHOAN());
//
//        attrs.add(hsHam);
//        attrs.add(hsDanhSachSanPham);
//        attrs.add(hsTenNguoiNhan);
//        attrs.add(hsSoDT);
//        attrs.add(hsDiaChi);
//        attrs.add(hsChuyenKhoan);
////        attrs.add(hsMaChuyenKhoan);
//
//        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);
//
//        downloadJSON.execute();
//
//        try {
//            String duLieuJSON = downloadJSON.get();
//            JSONObject jsonObject = new JSONObject(duLieuJSON);
//            String ketqua = jsonObject.getString("ketqua");
//            Log.d("Kiểm tra", ketqua);
//
//            if(ketqua.equals("true")){
//                kiemtra = true;
//            }else {
//                kiemtra = false;
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return kiemtra;
    }

}

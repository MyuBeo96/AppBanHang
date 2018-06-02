package com.myubeo.appbanhang.Model.ChiTietSanPham;

import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelHienThiSanPhamTheoMaDanhMuc {

    public static List<SanPham> LayDanhSachSanPhamTheoMaLoaiSP(int maSP, String tenHam, String tenMang, int limit){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        String dataJSON = "";

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenHam);

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp",String.valueOf(maSP));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit",String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenMang);
            int count = jsonArray.length();
            for(int i = 0; i < count; i++){
                JSONObject value = jsonArray.getJSONObject(i);

                SanPham dataSanPham = new SanPham();
                dataSanPham.setMASP(value.getInt("MASP"));
                dataSanPham.setTENSP(value.getString("TENSP"));
                dataSanPham.setGIA(value.getInt("GIA"));
                dataSanPham.setHINHLON(value.getString("HINHLON"));
//                dataSanPham.setHINHNHO(value.getString("HINHNHO"));
                sanPhamList.add(dataSanPham);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
}

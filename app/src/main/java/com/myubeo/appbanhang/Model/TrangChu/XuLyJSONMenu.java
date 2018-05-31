package com.myubeo.appbanhang.Model.TrangChu;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.LoaiSanPham;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by as1 on 3/29/2018.
 */

public class XuLyJSONMenu {

    String useName = "";

    public List<LoaiSanPham> ParserJSONMenu(String dataJSON){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("LOAISANPHAM");
            int count = jsonArray.length();
            for(int i = 0; i < count; i++){
                JSONObject value = jsonArray.getJSONObject(i);

                LoaiSanPham dataLoaiSP = new LoaiSanPham();
                dataLoaiSP.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataLoaiSP.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataLoaiSP.setTENLOAISP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataLoaiSP);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return loaiSanPhamList;
    }

    public List<LoaiSanPham> LayDanhSachSP(int maLoaiCha){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        String dataJSON = "";

//        String link = "http://192.168.43.79:8080/webLazada/loaisanpham.php";
        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDSMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maLoaiCha));

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

}

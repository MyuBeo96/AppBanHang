package com.myubeo.appbanhang.Model.TrangDienTu;

import android.util.Log;

import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Model.ObjectClass.ThuongHieu;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDienTu {

    public List<SanPham> LayDanhSachSanPhamTop(String tenHam, String tenMang){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        String dataJSON = "";

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenHam);

        attrs.add(hsHam);

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

    public List<ThuongHieu> LayDanhSachThuongHieu(String tenHam, String tenMang){

        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        String dataJSON = "";

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenHam);

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray(tenMang);
            int count = jsonArray.length();
            for(int i = 0; i < count; i++){
                JSONObject value = jsonArray.getJSONObject(i);

                ThuongHieu dataThuongHieu = new ThuongHieu();
                dataThuongHieu.setMATHUONGHIEU(Integer.parseInt(value.getString("MATHUONGHIEU")));
                dataThuongHieu.setTENTHUONGHIEU(value.getString("TENTHUONGHIEU"));
                dataThuongHieu.setHINHTHUONGHIEU(value.getString("HINHTHUONGHIEU"));

                thuongHieuList.add(dataThuongHieu);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }
}

package com.myubeo.appbanhang.Model.KhuyenMai;

import android.util.Log;

import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.ChiTietKhuyenMai;
import com.myubeo.appbanhang.Model.ObjectClass.ChiTietSanPham;
import com.myubeo.appbanhang.Model.ObjectClass.KhuyenMai;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelKhuyenMai {

    public static List<KhuyenMai> LayDanhSachSanPhamKhuyenMai(String tenHam, String tenMang){
        List<KhuyenMai> khuyenMaiList = new ArrayList<>();
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

                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMAKM(value.getInt("MAKM"));
                khuyenMai.setTENLOAISP(value.getString("TENLOAISP"));
                khuyenMai.setTENKM(value.getString("TENKM"));
                khuyenMai.setHINHKHUYENMAI(TrangChuActivity.SERVER + value.getString("HINHKHUYENMAI"));

                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDanhSachSanPham = value.getJSONArray("DANHSACHSANPHAM");
                int demSP = arrayDanhSachSanPham.length();

                for(int j = 0; j < demSP; j++){
                    JSONObject object = arrayDanhSachSanPham.getJSONObject(j);

                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(object.getInt("MASP"));
                    sanPham.setTENSP(object.getString("TENSP"));
                    sanPham.setGIA(object.getInt("GIA"));
                    sanPham.setHINHLON(TrangChuActivity.SERVER + object.getString("HINHLON"));
                    sanPham.setHINHNHO(TrangChuActivity.SERVER + object.getString("HINHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                    sanPhamList.add(sanPham);


                    Log.d("Khuyen mai:", String.valueOf(sanPhamList));
                }

                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMaiList.add(khuyenMai);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return khuyenMaiList;
    }

}

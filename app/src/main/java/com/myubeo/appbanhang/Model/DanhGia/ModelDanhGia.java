package com.myubeo.appbanhang.Model.DanhGia;

import android.util.Log;

import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDanhGia {

    public Boolean ThemDanhGia(DanhGia danhGia){
        List<HashMap<String, String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDanhGia");

        HashMap<String,String> hsMaDG = new HashMap<>();
        hsMaDG.put("madg",danhGia.getMADG());

        HashMap<String,String> hsMasp = new HashMap<>();
        hsMasp.put("masp",String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());

        HashMap<String,String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTIEUDE());

        HashMap<String,String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao",String.valueOf(danhGia.getSOSAO()));

        attrs.add(hsHam);
        attrs.add(hsMaDG);
        attrs.add(hsMasp);
        attrs.add(hsTenThietBi);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            String duLieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(duLieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("Kiểm tra", ketqua);

            if(ketqua.equals("true")){
                kiemtra = true;
            }else {
                kiemtra = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}

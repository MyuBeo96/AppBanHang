package com.myubeo.appbanhang.Presenter.TrangChu.XuLyMenu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.myubeo.appbanhang.ConnectInternet.DownloadJSON;
import com.myubeo.appbanhang.Model.DangNhap.ModelDangNhap;
import com.myubeo.appbanhang.Model.ObjectClass.LoaiSanPham;
import com.myubeo.appbanhang.Model.TrangChu.XuLyJSONMenu;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;
import com.myubeo.appbanhang.View.TrangChu.XuLyMenuView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by as1 on 3/30/2018.
 */

public class XuLyMenuLogic implements XuLyMenuImpl {

    XuLyMenuView xuLyMenuView;
    String useName = "";

    public XuLyMenuLogic(XuLyMenuView xuLyMenuView){
        this.xuLyMenuView = xuLyMenuView;
    }

    @Override
    public void LayDanhSachMenu() {

        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();

//        String link = "http://192.168.43.79:3307/webLazada/loaisanpham.php?maloaicha=0";
//
//        DownloadJSON downloadJSON = new DownloadJSON(link);

        String link = TrangChuActivity.SERVER_NAME ;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDSMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
            xuLyMenuView.HienThiDanhSachMenu(loaiSanPhamList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken LayUseFaceBook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenDangNhap();

        return accessToken;
    }
}

package com.myubeo.appbanhang.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myubeo.appbanhang.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ModelGioHang {

    SQLiteDatabase database;

    public void MoKetNoiSQLite(Context context){
        DataSanPham dataSanPham = new DataSanPham(context);
        database = dataSanPham.getWritableDatabase();
    }

    public boolean XoaSanPhamGioHang(int masp){
        int kiemtra = database.delete(DataSanPham.TB_GIOHANG, DataSanPham.TB_GIOHANG_MASP + " = " + masp, null);

        if(kiemtra > 0){
            return true;
        }else {
            return false;
        }

    }

    public boolean CapNhatGioHang(int masp, int soluong){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG, soluong);

        int id = database.update(DataSanPham.TB_GIOHANG, contentValues, DataSanPham.TB_GIOHANG_MASP + " = " + masp, null );
        if(id > 0){
            return true;
        }else {
            return false;
        }

    }

    public boolean ThemGioHang(SanPham sanPham){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_MASP, sanPham.getMASP());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN, sanPham.getGIA());
        contentValues.put(DataSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhGioHang());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG, sanPham.getSOLUONG());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONGTONKHO, sanPham.getSOLUONGTONKHO());

        long id = database.insert(DataSanPham.TB_GIOHANG, null, contentValues);
        if(id > 0){
            return true;
        }else {
            return false;
        }
    }

    public List<SanPham> LayDanhSachSanPhamGioHang(){
        List<SanPham> sanPhamList = new ArrayList<>();

        String truyVan = "SELECT * FROM " + DataSanPham.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyVan, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONGTONKHO));

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhGioHang(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSOLUONGTONKHO(soluongtonkho);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }

        return sanPhamList;
    }
}

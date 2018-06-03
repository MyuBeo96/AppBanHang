package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.DienTu;
import com.myubeo.appbanhang.R;

import java.util.List;

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolderDienTu>{

    Context context;
    List<DienTu> dienTuList;

    public AdapterDienTu(Context context, List<DienTu> dienTuList){
        this.context = context;
        this.dienTuList = dienTuList;
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView img_giamGia;
        RecyclerView rv_thuonghieu;
        RecyclerView rv_topLaptop;
        TextView txt_TenNoiBat;
        TextView txt_TenTopNoiBat;

        public ViewHolderDienTu(View itemView) {
            super(itemView);

            rv_thuonghieu = itemView.findViewById(R.id.rv_ThuongHieu);
            rv_topLaptop = itemView.findViewById(R.id.rv_Laptop);
            img_giamGia = itemView.findViewById(R.id.img_giamGia);
            txt_TenNoiBat = itemView.findViewById(R.id.txt_tenNoiBat);
            txt_TenTopNoiBat = itemView.findViewById(R.id.txt_tenTopNoiBat);
        }
    }

    @Override
    public ViewHolderDienTu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recyclerview_dientu,parent,false);

        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);

        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienTu holder, int position) {
        DienTu dienTu = dienTuList.get(position);

        holder.txt_TenNoiBat.setText(dienTu.getTenNoiBat().toString());
        holder.txt_TenTopNoiBat.setText(dienTu.getTenTopNoiBat().toString());

        //Xu ly hien thi thuong hieu lon o dien tu
        AdapterThuongHieu adapterThuongHieu = new AdapterThuongHieu(context, dienTu.getThuongHieus(), dienTu.isThuongHieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        holder.rv_thuonghieu.setLayoutManager(layoutManager);
        holder.rv_thuonghieu.setAdapter(adapterThuongHieu);
        adapterThuongHieu.notifyDataSetChanged();

        //Xu ly hien thi top laptop o dien tu
        AdapterTopMayTinh adapterTopMayTinh = new AdapterTopMayTinh(context,R.layout.custom_recyclerview_laptop, dienTu.getSanPhams());

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.rv_topLaptop.setLayoutManager(layoutManager1);
        holder.rv_topLaptop.setAdapter(adapterTopMayTinh);
        adapterTopMayTinh.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

}

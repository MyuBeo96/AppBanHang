package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.R;

import java.util.List;

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {

    Context context;
    List<DanhGia> danhGiaList;
    int limit;

    public AdapterDanhGia(Context context, List<DanhGia> danhGiaList, int limit){
        this.context = context;
        this.danhGiaList = danhGiaList;
        this.limit = limit;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {

        TextView txt_TieuDeDanhGia;
        RatingBar rb_DanhGiaSao;
        TextView txt_DuocDangBoi;
        TextView txt_NoiDungDanhGia;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);

            txt_TieuDeDanhGia = itemView.findViewById(R.id.txt_TieuDeDanhGia);
            rb_DanhGiaSao = itemView.findViewById(R.id.rb_DanhGiaSao);
            txt_DuocDangBoi = itemView.findViewById(R.id.txt_DuocDangBoi);
            txt_NoiDungDanhGia = itemView.findViewById(R.id.txt_NoiDungDanhGia);
        }
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_danhgia,parent,false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);

        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhGia holder, int position) {

        DanhGia danhGia = danhGiaList.get(position);

        holder.txt_TieuDeDanhGia.setText(danhGia.getTIEUDE());
        holder.txt_DuocDangBoi.setText("Được đánh giá bởi: " + danhGia.getTENTHIETBI() + ", ngày " + danhGia.getNGAYDANHGIA());
        holder.txt_NoiDungDanhGia.setText(danhGia.getNOIDUNG());
        holder.rb_DanhGiaSao.setRating(danhGia.getSOSAO());

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(danhGiaList.size() < limit){
            count = danhGiaList.size();
        }else {
            if(limit != 0){
                count = limit;
            }else {
                count = danhGiaList.size();
            }
        }
        return count;
    }
}

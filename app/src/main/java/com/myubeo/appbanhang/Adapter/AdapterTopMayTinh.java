package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopMayTinh extends RecyclerView.Adapter<AdapterTopMayTinh.ViewHolderTopMayTinh> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterTopMayTinh(Context context, int layout,List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopMayTinh extends RecyclerView.ViewHolder {

        ImageView img_HinhSP;
        TextView txt_tensp;
        TextView txt_giasp;
        TextView txt_giamgia;
        CardView cardView;

        public ViewHolderTopMayTinh(View itemView) {
            super(itemView);

            img_HinhSP = itemView.findViewById(R.id.img_Laptop);
            txt_tensp = itemView.findViewById(R.id.txt_Laptop);
            txt_giasp = itemView.findViewById(R.id.txt_GiaLaptop);
            txt_giamgia = itemView.findViewById(R.id.txt_LaptopGiamGia);
            cardView = itemView.findViewById(R.id.cardView_Laptop);
        }
    }

    @Override
    public ViewHolderTopMayTinh onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);

        ViewHolderTopMayTinh viewHolderTopMayTinh = new ViewHolderTopMayTinh(view);

        return viewHolderTopMayTinh;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopMayTinh holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.get().load(sanPham.getHINHLON()).resize(150,150).centerCrop().into(holder.img_HinhSP);
        holder.txt_tensp.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txt_giasp.setText(gia + " VNĐ");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", (Integer) v.getTag());
                context.startActivity(iChiTietSanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}

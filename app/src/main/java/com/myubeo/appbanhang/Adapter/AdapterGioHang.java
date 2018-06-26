package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.GioHang.ModelGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhams;

    public AdapterGioHang(Context context, List<SanPham> sanPhams){
        this.context = context;
        this.sanPhams = sanPhams;
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        ImageView img_HinhGioHang;
        TextView txt_TieuDeGioHang;
        TextView txt_GiaGioHang;
        TextView txt_GiamGiaGioHang;
        ImageView img_XoaGioHang;

        public ViewHolderGioHang(View itemView) {
            super(itemView);

            img_HinhGioHang = itemView.findViewById(R.id.img_HinhGioHang);
            txt_TieuDeGioHang = itemView.findViewById(R.id.txt_TieuDeGioHang);
            txt_GiaGioHang = itemView.findViewById(R.id.txt_GiaGioHang);
            txt_GiamGiaGioHang = itemView.findViewById(R.id.txt_GiamGiaGioHang);
            img_XoaGioHang = itemView.findViewById(R.id.img_XoaGioHang);
        }
    }

    @Override
    public ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang,parent,false);

        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(ViewHolderGioHang holder, final int position) {

        final SanPham sanPham = sanPhams.get(position);

        holder.txt_TieuDeGioHang.setText(sanPham.getTENSP());

        final NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txt_GiaGioHang.setText(gia + " VNĐ");

        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(sanPham.getHinhGioHang(),0, sanPham.getHinhGioHang().length);
        holder.img_HinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.img_XoaGioHang.setTag(sanPham.getMASP());

        holder.img_XoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQLite(context);
                modelGioHang.XoaSanPhamGioHang((int)v.getTag());
                sanPhams.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }


}

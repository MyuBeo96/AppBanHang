package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myubeo.appbanhang.Model.ObjectClass.ThuongHieu;
import com.myubeo.appbanhang.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterLogoThuongHieu extends RecyclerView.Adapter<AdapterLogoThuongHieu.ViewHolderLogoThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdapterLogoThuongHieu(Context context, List<ThuongHieu> thuongHieuList){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }

    public class ViewHolderLogoThuongHieu extends RecyclerView.ViewHolder {

        ImageView img_logoThuongHieu;

        public ViewHolderLogoThuongHieu(View itemView) {
            super(itemView);

            img_logoThuongHieu = itemView.findViewById(R.id.img_logoThuongHieu);
        }
    }

    @Override
    public ViewHolderLogoThuongHieu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_logothuonghieu,parent,false);

        ViewHolderLogoThuongHieu viewHolderLogoThuongHieu = new ViewHolderLogoThuongHieu(view);
        return viewHolderLogoThuongHieu;
    }

    @Override
    public void onBindViewHolder(ViewHolderLogoThuongHieu holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);

        Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(180, 90).centerInside().into(holder.img_logoThuongHieu);

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

}

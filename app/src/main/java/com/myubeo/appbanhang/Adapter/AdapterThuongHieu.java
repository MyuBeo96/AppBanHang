package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.ThuongHieu;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.ChiTietSanPham.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieu extends RecyclerView.Adapter<AdapterThuongHieu.ViewHolderThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieuList;
    boolean kiemTra;

    public AdapterThuongHieu(Context context, List<ThuongHieu> thuongHieuList, boolean kiemTra){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
        this.kiemTra = kiemTra;
    }

    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView txt_TieuDe;
        ImageView img_ThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolderThuongHieu(View itemView) {
            super(itemView);

            txt_TieuDe = itemView.findViewById(R.id.txt_tieude);
            img_ThuongHieu = itemView.findViewById(R.id.img_ThuongHieu);
            progressBar = itemView.findViewById(R.id.prb_load);
            linearLayout = itemView.findViewById(R.id.ln_ThuongHieu);

        }
    }

    @Override
    public ViewHolderThuongHieu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycleview_thuonghieulon,parent,false);

        ViewHolderThuongHieu viewHolderThuongHieu = new ViewHolderThuongHieu(view);
        return viewHolderThuongHieu;
    }

    @Override
    public void onBindViewHolder(final ViewHolderThuongHieu holder, int position) {
        final ThuongHieu thuongHieu = thuongHieuList.get(position);
        holder.txt_TieuDe.setText(thuongHieu.getTENTHUONGHIEU());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiSpTheoTH = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                iHienThiSpTheoTH.putExtra("MATH", thuongHieu.getMATHUONGHIEU());
                iHienThiSpTheoTH.putExtra("TENTHUONGHIEU", thuongHieu.getTENTHUONGHIEU());
                iHienThiSpTheoTH.putExtra("KIEMTRA", kiemTra);
                context.startActivity(iHienThiSpTheoTH);
            }
        });

        Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(120, 120).into(holder.img_ThuongHieu,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


}

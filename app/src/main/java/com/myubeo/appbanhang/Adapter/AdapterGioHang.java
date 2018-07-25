package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myubeo.appbanhang.Model.GioHang.ModelGioHang;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import static com.myubeo.appbanhang.View.GioHang.GioHangActivity.txt_tongTien;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhams;
    ModelGioHang modelGioHang;
    String giaSP;

    public AdapterGioHang(Context context, List<SanPham> sanPhams){
        this.context = context;
        this.sanPhams = sanPhams;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQLite(context);
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        ImageView img_HinhGioHang;
        TextView txt_TieuDeGioHang;
        TextView txt_GiaGioHang;
        TextView txt_GiamGiaGioHang;
        ImageView img_XoaGioHang;
        ImageButton btn_GiamSL;
        TextView txt_SoLuong;
        ImageButton btn_TangSL;

        public ViewHolderGioHang(View itemView) {
            super(itemView);

            img_HinhGioHang = itemView.findViewById(R.id.img_HinhGioHang);
            txt_TieuDeGioHang = itemView.findViewById(R.id.txt_TieuDeGioHang);
            txt_GiaGioHang = itemView.findViewById(R.id.txt_GiaGioHang);
            txt_GiamGiaGioHang = itemView.findViewById(R.id.txt_GiamGiaGioHang);
            img_XoaGioHang = itemView.findViewById(R.id.img_XoaGioHang);
            btn_GiamSL = itemView.findViewById(R.id.btn_GiamSL);
            txt_SoLuong = itemView.findViewById(R.id.txt_SoLuong);
            btn_TangSL = itemView.findViewById(R.id.btn_TangSL);
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
    public void onBindViewHolder(final ViewHolderGioHang holder, final int position) {

        final SanPham sanPham = sanPhams.get(position);

        holder.txt_TieuDeGioHang.setText(sanPham.getTENSP());

        final NumberFormat numberFormat = new DecimalFormat("###,###");
        final String gia = numberFormat.format(sanPham.getGIA()).toString();
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

        holder.txt_SoLuong.setText(String.valueOf(sanPham.getSOLUONG()));

        holder.btn_GiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txt_SoLuong.getText().toString());

                if(soluong > 1) {
                    soluong--;
                }

                modelGioHang.CapNhatGioHang(sanPham.getMASP(), soluong);
                holder.txt_SoLuong.setText(String.valueOf(soluong));

                Log.d("Số lượng", " - " + soluong);
                long giaHienTai = sanPham.getGIA();
                long tongTienSP = giaHienTai * soluong;
                NumberFormat numberFormat = new DecimalFormat("###,###");
                giaSP = numberFormat.format(tongTienSP);
                holder.txt_GiaGioHang.setText(giaSP + " VNĐ");
                Log.d("Giá tiền", " - " + giaSP);
//                long thanhTien = tongTienSP;
//                for(int i = 0; i < sanPhams.size(); i++){
//                    thanhTien -= sanPhams.get(i).getGIA();
//                }
//
//                NumberFormat numberFormat1 = new DecimalFormat("###,###");
//                String giaTongTien = numberFormat1.format(thanhTien);
                txt_tongTien.setText(giaSP + " VNĐ");
//                Log.d("Giá tiền tong", " - " + giaTongTien + " - " + thanhTien);

            }
        });

        holder.btn_TangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txt_SoLuong.getText().toString());
                int soLuongTonKho = sanPham.getSOLUONGTONKHO();

                if(soluong < soLuongTonKho){
                    soluong++;
                }else {
                    Toast.makeText(context, "Số lượng sản phẩm bạn mua quá số lượng có trong kho của cửa hàng", Toast.LENGTH_LONG).show();
                }

                modelGioHang.CapNhatGioHang(sanPham.getMASP(), soluong);
                holder.txt_SoLuong.setText(String.valueOf(soluong));

                Log.d("Số lượng", " - " + soluong);
                long giaHienTai = sanPham.getGIA();
                long tongTienSP = giaHienTai * soluong;
                NumberFormat numberFormat = new DecimalFormat("###,###");
                giaSP = numberFormat.format(tongTienSP);
                holder.txt_GiaGioHang.setText(giaSP + " VNĐ");
                Log.d("Giá tiền", " - " + giaSP);
//                long thanhTien = tongTienSP;
//                for(int i = 0; i < sanPhams.size(); i++){
//                    thanhTien += sanPhams.get(i).getGIA();
//                }
//
//                NumberFormat numberFormat1 = new DecimalFormat("###,###");
//                String giaTongTien = numberFormat1.format(thanhTien);
                txt_tongTien.setText(giaSP + " VNĐ");
//                Log.d("Giá tiền tong", " - " + giaTongTien + " - " + thanhTien);

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

}

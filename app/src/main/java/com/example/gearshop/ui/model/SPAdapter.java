package com.example.gearshop.ui.model;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearshop.R;
import com.example.gearshop.ui.chitietsanpham.ChiTietSanPham;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SPAdapter extends RecyclerView.Adapter<SPAdapter.SPAdapterHolder>{
    private List<SanPham> listSP;
    Context mContext;
    View view;
    public void setListSP(Context context,  List<SanPham> list){
        this.mContext = context;
        this.listSP = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SPAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.san_pham, parent, false);
        return new SPAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPAdapterHolder holder, int position) {
        final SanPham sp = listSP.get(position);
        if(sp == null){
            return;
        }
        holder.tvTenSP.setText(sp.getTenSP());

        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        String giaSP = formatter.format(sp.getGiaSP())+" VNĐ";

        holder.tvGiaSP.setText(giaSP);
        holder.tvThuongHieuSP.setText(sp.getMaTH());
        if(sp.getHinhAnh1().length() > 32){
            Picasso.get()
                    .load("https://drive.google.com/uc?id=" + sp.getHinhAnh1().substring(32,sp.getHinhAnh1().lastIndexOf('/')))
                    .into(holder.ivSanPham);
        }else{
            holder.ivSanPham.setImageResource(R.drawable.baseline_help_center_24);
        }

        holder.llSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToChiTietSanPham(sp);
            }
        });
    }

    private void onClickGoToChiTietSanPham(SanPham sp) {
        Intent intent = new Intent(mContext, ChiTietSanPham.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("san_pham", sp);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (listSP != null){
            return listSP.size();
        }
        return 0;
    }

    public class SPAdapterHolder extends RecyclerView.ViewHolder{
        LinearLayout llSanPham;
        TextView tvTenSP, tvGiaSP, tvThuongHieuSP;
        ImageView ivSanPham;

        public SPAdapterHolder(@NonNull View itemView) {
            super(itemView);
            setControl(itemView);
        }

        private void setControl(View itemView) {
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvThuongHieuSP = itemView.findViewById(R.id.tvThuongHieuSP);
            ivSanPham = itemView.findViewById(R.id.ivSanPham);
            llSanPham = itemView.findViewById(R.id.llSanPham);
        }
    }
}

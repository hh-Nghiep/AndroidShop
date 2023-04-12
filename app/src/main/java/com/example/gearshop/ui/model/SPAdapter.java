package com.example.gearshop.ui.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearshop.R;

import java.util.List;

public class SPAdapter extends RecyclerView.Adapter<SPAdapter.SPAdapterHolder>{
    private List<SanPham> listSP;

    public void setListSP(List<SanPham> list){
        this.listSP = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SPAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.san_pham, parent, false);
        return new SPAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPAdapterHolder holder, int position) {
        SanPham sp = listSP.get(position);
        if(sp == null){
            return;
        }
        holder.tvTenSP.setText(sp.getTenSP());
        holder.tvGiaSP.setText(sp.getGiaSP().toString());
        holder.tvThuongHieuSP.setText(sp.getMaTH().toString());
    }

    @Override
    public int getItemCount() {
        if (listSP != null){
            return listSP.size();
        }
        return 0;
    }

    public class SPAdapterHolder extends RecyclerView.ViewHolder{
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
        }

    }


}

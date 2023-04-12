package com.example.gearshop.ui.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearshop.R;

import java.util.ArrayList;

public class CustomAdapter_SP extends ArrayAdapter<SanPham> {
    Context context;
    int resource;
    ArrayList<SanPham> dataSP;

    public CustomAdapter_SP(Context context, int resource, ArrayList<SanPham> dataSP) {
        super(context, resource, dataSP);
        this.context = context;
        this.resource = resource;
        this.dataSP = dataSP;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SanPham sp = dataSP.get(position);
        viewHolder.tvTenSP.setText(sp.getTenSP());
        viewHolder.tvGiaSP.setText(sp.getGiaSP().toString());
        if(sp.getHinhAnh1() == null)
            viewHolder.imHinhAnhSP.setImageResource(R.drawable.baseline_help_center_24);
        else
            viewHolder.imHinhAnhSP.setImageResource(R.drawable.baseline_inventory_24);
        return convertView;
    }

    private class ViewHolder {
        TextView tvTenSP, tvGiaSP;
        ImageView imHinhAnhSP;

        public ViewHolder(View view) {
            this.tvTenSP = view.findViewById(R.id.tvTenSP);
            this.tvGiaSP = view.findViewById(R.id.tvGiaSP);
            this.imHinhAnhSP = view.findViewById(R.id.ivSanPham);
        }
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemView = layoutInflater.inflate(R.layout.san_pham, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.tvTenSP.setText(dataSP.get(position).getTenSP());
//        holder.tvGiaSP.setText(dataSP.get(position).getGiaSP().toString());
//        holder.imHinhAnhSP.setImageResource(R.drawable.baseline_help_center_24);
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataSP.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTenSP, tvGiaSP;
//        ImageView imHinhAnhSP;
//        public ViewHolder(View view) {
//            super(view);
//            this.tvTenSP = view.findViewById(R.id.tvTenSP);
//            this.tvGiaSP = view.findViewById(R.id.tvGiaSP);
//            this.imHinhAnhSP = view.findViewById(R.id.ivSanPham);
//        }
//    }
}

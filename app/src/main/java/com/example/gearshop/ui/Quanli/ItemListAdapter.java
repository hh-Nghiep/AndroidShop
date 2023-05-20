package com.example.gearshop.ui.Quanli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.SanPham;
import com.squareup.picasso.Picasso;

public class ItemListAdapter extends ArrayAdapter<ItemList> {
    private Context mContex;
    private int mResource;

    public ItemListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemList> objects) {
        super(context, resource, objects);
        this.mContex = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater  layoutInflater = LayoutInflater.from(mContex);

        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView txtName = convertView.findViewById(R.id.txtName);

        TextView txtGia = convertView.findViewById(R.id.txtGia);


//        imageView.setImageResource(getItem(position).getImage());
        if(getItem(position).getHinh1().length() > 32){
            Picasso.get()
                    .load("https://drive.google.com/uc?id=" + getItem(position).getHinh1().substring(32,getItem(position).getHinh1().lastIndexOf('/')))
                    .into(imageView);
        }else{
            imageView.setImageResource(R.drawable.baseline_help_center_24);
        }
        txtName.setText(getItem(position).getName());

        txtGia.setText(getItem(position).getPrice().toString());

        return convertView;
    }

}

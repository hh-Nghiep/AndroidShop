package com.example.gearshop.ui.cart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gearshop.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class CartArrayAdapter extends ArrayAdapter<CartItem> {
    Activity context;
    int IdLayout;
    ArrayList<CartItem> cartItemsList;

    public CartArrayAdapter( Activity context, int idLayout, ArrayList<CartItem> cartItemsList) {
        super(context, idLayout, cartItemsList);
        this.context = context;
        IdLayout = idLayout;
        this.cartItemsList = cartItemsList;
    }
    // hien thi du lieu

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myFlater = context.getLayoutInflater();

        convertView = myFlater.inflate(IdLayout, null);
        CartItem item = cartItemsList.get(position);
        ImageView cartImg = convertView.findViewById(R.id.picCart);
        cartImg.setImageResource(item.getImg());

//        Picasso.get()
//                .load("https://drive.google.com/uc?id=" + item.getImg().substring(32,item.getImg().lastIndexOf('/')))
//                .into(cartImg);

        TextView cartName = convertView.findViewById(R.id.cartItemTitleTxt);
        cartName.setText(item.getName());

        TextView initPriceItem = convertView.findViewById(R.id.priceEachItem);
        initPriceItem.setText((String.format("%,d",item.getInitPrice())));

        TextView totalPriceItem = convertView.findViewById(R.id.priceTotalItem);
        totalPriceItem.setText(String.format("%,d",item.getTotalPrice()));

        TextView amout = convertView.findViewById(R.id.numberItemTxt);
        amout.setText(String.format("%,d",item.getAmout()));

        return convertView;
    }
}

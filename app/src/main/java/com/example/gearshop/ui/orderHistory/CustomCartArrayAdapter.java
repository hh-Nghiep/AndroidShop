package com.example.gearshop.ui.orderHistory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.giohang.CartListActivity;

import java.util.ArrayList;

public class CustomCartArrayAdapter extends BaseAdapter {
    Activity context;

    ArrayList<CartItem> cartItemsList;

    public CustomCartArrayAdapter(Activity context, ArrayList<CartItem> cartItemsList) {
        this.context = context;
        this.cartItemsList = cartItemsList;
    }
// hien thi du lieu

    @Override
    public int getCount() {
        return cartItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
       public ImageView picCart;
       public TextView cartItemTitleTxt, priceEachItem, priceTotalItem, numberItemTxt;
       public ImageButton minusCartBtn, plusCartBtn;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(view == null){
        viewHolder = new ViewHolder();
            LayoutInflater inFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inFlater.inflate(R.layout.viewholder_cart, null);
            viewHolder.picCart = view.findViewById(R.id.picCart);
            viewHolder.cartItemTitleTxt = view.findViewById(R.id.cartItemTitleTxt);
            viewHolder.priceEachItem = view.findViewById(R.id.priceEachItem);
            viewHolder.priceTotalItem = view.findViewById(R.id.priceTotalItem);
            viewHolder.numberItemTxt = view.findViewById(R.id.numberItemTxt);
            viewHolder.minusCartBtn = view.findViewById(R.id.minusCartBtn);
            viewHolder.plusCartBtn = view.findViewById(R.id.plusCartBtn);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }



        CartItem item = (CartItem) getItem(position);
        viewHolder.picCart.setImageResource(item.getImg());
        viewHolder.cartItemTitleTxt.setText(item.getName());
        viewHolder.priceEachItem.setText(String.format("%,d",item.getInitPrice()));
        viewHolder.priceTotalItem.setText(String.format("%,d",item.getTotalPrice()));
        viewHolder.numberItemTxt.setText(String.format("%,d",item.getAmout()));

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.plusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.numberItemTxt.getText().toString()) + 1;
                int slhtai = CartListActivity.cartItemsList.get(position).getAmout();
                int giaTongMoi = slmoinhat * CartListActivity.cartItemsList.get(position).getInitPrice();
                CartListActivity.cartItemsList.get(position).setAmout(slmoinhat);
                CartListActivity.cartItemsList.get(position).setTotalPrice(giaTongMoi);
                finalViewHolder.priceTotalItem.setText(String.format("%,d",giaTongMoi));

                finalViewHolder.numberItemTxt.setText(String.valueOf(slmoinhat));
            }
        });

        viewHolder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slhtai = Integer.parseInt(finalViewHolder.numberItemTxt.getText().toString());
                if(slhtai > 0) {
                    slhtai -= 1;
                    CartListActivity.cartItemsList.get(position).setAmout(slhtai);
                    int giaTongMoi = slhtai * CartListActivity.cartItemsList.get(position).getInitPrice();
                    CartListActivity.cartItemsList.get(position).setTotalPrice(giaTongMoi);
                    finalViewHolder.priceTotalItem.setText(String.format("%,d",giaTongMoi));
                    finalViewHolder.numberItemTxt.setText(String.valueOf(slhtai));
                }

            }
        });

        return view;
    }
}

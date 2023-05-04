package com.example.gearshop.ui.orderHistory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;

import java.util.ArrayList;

public class OrderHistoryAdapter extends ArrayAdapter<OrderHistoryItem> {
    Activity context;
    int idLayout;
    ArrayList<OrderHistoryItem> orderHistoryItemsList;
    CartArrayAdapter cartArrayAdapter;
    ListView orderHistoryLv;
    public OrderHistoryAdapter(Activity context, int idLayout, ArrayList<OrderHistoryItem> orderHistoryItemsList) {
        super(context, idLayout, orderHistoryItemsList);
        this.context = context;
        this.idLayout = idLayout;
        this.orderHistoryItemsList = orderHistoryItemsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlater = context.getLayoutInflater();

        for(int j = 0; j< orderHistoryItemsList.size(); j++) {


        }


        convertView = myFlater.inflate(idLayout, null);
        OrderHistoryItem item = orderHistoryItemsList.get(position);
        TextView totalPrice = convertView.findViewById(R.id.orderHistoryTotalPriceTxt);

        ArrayList <CartItem> dsSanPham = item.getDsSanPham();
        System.out.println("item "+ item);
        orderHistoryLv = convertView.findViewById(R.id.OrderHistoryLv);

        int total = 0;
        for (int i = 0; i < dsSanPham.size(); i++) {
            total+= (dsSanPham.get(i).getInitPrice() * dsSanPham.get(i).getAmout());
        }
        totalPrice.setText(String.format("%,d",total));

        cartArrayAdapter = new CartArrayAdapter(context, R.layout.san_pham_da_dat, dsSanPham);

        orderHistoryLv.setAdapter(cartArrayAdapter);


        return convertView;
    }
}

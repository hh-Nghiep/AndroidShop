package com.example.gearshop.ui.orderHistory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.giohang.DetailOrderActivity;
import com.example.gearshop.ui.giohang.OrderHistoryActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderHistoryAdapter extends ArrayAdapter<OrderHistoryItem> {
    Activity context;
    int idLayout;
    ArrayList<OrderHistoryItem> orderHistoryItemsList;
    CustomCartArrayAdapter cartArrayAdapter;
    ListView orderHistoryLv;
    TextView statusValueTxt, statusMessageTxt;
    Button btnCapNhatTrangThai, btnChiTietDonHang;
    Connection connection;
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

        convertView = myFlater.inflate(idLayout, null);
        OrderHistoryItem item = orderHistoryItemsList.get(position);
        TextView totalPrice = convertView.findViewById(R.id.orderHistoryTotalPriceTxt);
        statusValueTxt= convertView.findViewById(R.id.statusValueTxt);
        statusMessageTxt= convertView.findViewById(R.id.statusMessageTxt);
        btnCapNhatTrangThai = convertView.findViewById(R.id.btnCapNhatTrangThai);
        switch (item.getTrangThai()) {
            case 0: {
                statusValueTxt.setText("Chờ Duyệt");
                statusMessageTxt.setText("Đơn hàng đang được xét duyệt");
                btnCapNhatTrangThai.setText("Hủy đơn");
                break;
            }
            case 1: {
                int red = 76;
                int green = 107;
                int blue = 229;
                int color = Color.rgb(red, green, blue);
                statusValueTxt.setText("Đang vận chuyển");
                statusMessageTxt.setText("Đơn hàng đang được vận chuyển");
                btnCapNhatTrangThai.setText("Đã nhận hàng");
                statusValueTxt.setTextColor(color);
                break;
            }
            case 2: {
                int red = 75;
                int green = 236;
                int blue = 102;
                int color = Color.rgb(red, green, blue);
                statusValueTxt.setText("Đã giao");
                statusMessageTxt.setText("Đơn hàng đã được giao");
                btnCapNhatTrangThai.setVisibility(View.INVISIBLE);
                statusValueTxt.setTextColor(color);
                break;
            }
            case 3: {
                int red = 208;
                int green = 31;
                int blue = 31;
                int color = Color.rgb(red, green, blue);
                statusValueTxt.setText("Đã Hủy");
                statusMessageTxt.setText("Đơn hàng đã bị hủy");
                btnCapNhatTrangThai.setVisibility(View.INVISIBLE);
                statusValueTxt.setTextColor(color);
                break;
            }
        }
        ArrayList <CartItem> dsSanPham = item.getDsSanPham();
        orderHistoryLv = convertView.findViewById(R.id.OrderHistoryLv);

        int total = 0;
        for (int i = 0; i < dsSanPham.size(); i++) {
            total+= (dsSanPham.get(i).getInitPrice() * dsSanPham.get(i).getAmout());
        }
        totalPrice.setText(String.format("%,d",total));

        cartArrayAdapter = new CustomCartArrayAdapter(context, dsSanPham, "order");

        orderHistoryLv.setAdapter(cartArrayAdapter);
        btnChiTietDonHang = convertView.findViewById(R.id.btnChiTietDonHang);
        btnChiTietDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailOrderActivity.xemChiTietDonHang(item.getMaDH());
                Intent intent = new Intent(context, DetailOrderActivity.class);
                context.startActivity(intent);
            }
        });

        btnCapNhatTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(item.getTrangThai() == 0){
                    changeStatus(3, item.getMaDH());
                } else if(item.getTrangThai() == 1){
                    changeStatus(2, item.getMaDH());
                }
            }
        });

        return convertView;
    }
    public  void changeStatus(int status, int MaDH) {
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                ArrayList<CartItem> list = new ArrayList<>();
                String query = "UPDATE DonHang SET TrangThaiDH = ? WHERE MaDH = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, status);
                stmt.setInt(2, MaDH);
                stmt.executeUpdate();
                Toast.makeText( context.getApplicationContext(), "huy or nhan hang thanh cong!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, OrderHistoryActivity.class);
                context.startActivity(intent);

            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
            Toast.makeText( context.getApplicationContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}

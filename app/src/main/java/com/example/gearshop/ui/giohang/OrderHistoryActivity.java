package com.example.gearshop.ui.giohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gearshop.MainActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.home.HomeFragment;
import com.example.gearshop.ui.orderHistory.OrderHistoryAdapter;
import com.example.gearshop.ui.orderHistory.OrderHistoryItem;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnThanhToanHoaDon;
    TextView tongTientxt;
    ArrayList<OrderHistoryItem> orderHistoryItemsList;
    OrderHistoryAdapter orderHistoryAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
    }
    private ArrayList<OrderHistoryItem> createMockup () {
        orderHistoryItemsList = new ArrayList<>();
        ArrayList<CartItem> cartList = new ArrayList<>();
        cartList.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 1", 1221323, 21332123, 2));
        cartList.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 2", 1223, 21323, 12));

        ArrayList<CartItem> cartList1 = new ArrayList<>();
        cartList1.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 1 new", 1221323, 21332123, 2));
        cartList1.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 2 new",1223, 21323, 12));


        orderHistoryItemsList.add(new OrderHistoryItem("Phong", "123 abc", "Phong@gmail.com", "1231233123", cartList));
        orderHistoryItemsList.add(new OrderHistoryItem("Phong134", "12323 abc", "Phong1@gmail.com", "1231233123", cartList1));

        return orderHistoryItemsList;
    }
    private void setControl() {

        tongTientxt = findViewById(R.id.orderHistoryTotalPriceTxt);
        orderHistoryItemsList = createMockup();



        lv = findViewById(R.id.OrdersHistoryActivityLv);

        orderHistoryAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, R.layout.danh_sach_don_hang_da_dat, orderHistoryItemsList);

        lv.setAdapter(orderHistoryAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(OrderHistoryActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
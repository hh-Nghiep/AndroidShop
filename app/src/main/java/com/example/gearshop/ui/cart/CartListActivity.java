package com.example.gearshop.ui.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnThanhToanHoaDon;
    TextView tongTientxt;
    ArrayList<CartItem> cartItemsList;
    CartArrayAdapter cartArrayAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnThanhToanHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this, OrderListActivity.class);
                CartListActivity.this.startActivity(intent);
            }
        });
    }
    private ArrayList<CartItem> createMockup () {
        cartItemsList = new ArrayList<>();

        cartItemsList.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 1", 123, 2123, 1));
        cartItemsList.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 2", 1223, 21323, 12));

        return cartItemsList;
    }
    private void setControl() {
        btnThanhToanHoaDon = findViewById(R.id.btnThanhToanHoaDon);
        tongTientxt = findViewById(R.id.totalPriceTxt);
        cartItemsList = createMockup();

        int total = 0;
        for (int i = 0; i < cartItemsList.size(); i++) {
            total+= (cartItemsList.get(i).getInitPrice() * cartItemsList.get(i).getAmout());
        }
        tongTientxt.setText(String.format("%,d",total));

        lv = findViewById(R.id.CartLV);

        cartArrayAdapter = new CartArrayAdapter(CartListActivity.this, R.layout.viewholder_cart, cartItemsList);

        lv.setAdapter(cartArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
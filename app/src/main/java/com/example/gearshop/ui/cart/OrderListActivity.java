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

import com.example.gearshop.MainActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {
    Button btnDatHang;
    Toolbar toolbar;
    TextView totalPriceNoShipTxt, shipPriceTxt, totalPriceTxt;
    ArrayList<CartItem> cartItemsList;
    CartArrayAdapter cartArrayAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderListActivity.this, MainActivity.class);
                OrderListActivity.this.startActivity(intent);
            }
        });
    }
    private ArrayList<CartItem> createMockup () {
        cartItemsList = new ArrayList<>();

        cartItemsList.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 1", 123, 2123, 1));
        cartItemsList.add(new CartItem(R.drawable.baseline_logout_24, "sản phẩm 2", 1223, 21323, 12));

        return cartItemsList;
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

    private void setControl() {
        btnDatHang = findViewById(R.id.btnDatHang);
        totalPriceNoShipTxt = findViewById(R.id.totalPriceNoShipTxt);
        shipPriceTxt= findViewById(R.id.shipPriceTxt);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);

        lv = findViewById(R.id.orderLv);
        cartItemsList = createMockup();

        int total = 0;
        for (int i = 0; i < cartItemsList.size(); i++) {
            total+= (cartItemsList.get(i).getInitPrice() * cartItemsList.get(i).getAmout());
        }
        totalPriceNoShipTxt.setText(String.format("%,d",total));
        shipPriceTxt.setText("25,000");
        totalPriceTxt.setText((String.format("%,d", total + 25000)));

        cartArrayAdapter = new CartArrayAdapter(OrderListActivity.this, R.layout.viewholder_cart, cartItemsList);

        lv.setAdapter(cartArrayAdapter);
    }
}
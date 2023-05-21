package com.example.gearshop.ui.giohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.orderHistory.CustomCartArrayAdapter;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {
    Button btnDatHang;
    Toolbar toolbar;
    TextView totalPriceNoShipTxt, shipPriceTxt, totalPriceTxt, addressTxt;
    ArrayList<CartItem> cartItemsList;
    CustomCartArrayAdapter cartArrayAdapter;
    ListView lv;
    ImageButton addressBtn;

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
                Intent intent = new Intent(OrderListActivity.this, OrderHistoryActivity.class);
                OrderListActivity.this.startActivity(intent);
            }
        });
        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderListActivity.this, AddressActivity.class);
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
        addressBtn = findViewById(R.id.addressBtn);
        addressTxt = findViewById(R.id.addressTxt);

        totalPriceNoShipTxt = findViewById(R.id.totalPriceNoShipTxt);
        shipPriceTxt= findViewById(R.id.shipPriceTxt);
        totalPriceTxt = findViewById(R.id.orderHistoryTotalPriceTxt);



        lv = findViewById(R.id.orderLv);
        cartItemsList = createMockup();

        int total = 0;
        for (int i = 0; i < CartListActivity.cartItemsList.size(); i++) {
            total+= (CartListActivity.cartItemsList.get(i).getInitPrice() * CartListActivity.cartItemsList.get(i).getAmout());
        }
        totalPriceNoShipTxt.setText(String.format("%,d",total));
        shipPriceTxt.setText("25,000");
        totalPriceTxt.setText((String.format("%,d", total + 25000)));
        if(CartOfUser.customerAddress != null) {
            System.out.println("CartOfUser.customerAddress.getAddress() " + CartOfUser.customerAddress.getAddress() );

                    addressTxt.setText(CartOfUser.customerAddress.getAddress().toString());

        }

        cartArrayAdapter = new CustomCartArrayAdapter(OrderListActivity.this,  CartListActivity.cartItemsList);

        lv.setAdapter(cartArrayAdapter);
    }
}
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
    static TextView totalPriceNoShipTxt;
    static TextView shipPriceTxt;
    static TextView totalPriceTxt;
    TextView addressTxt;
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
        String img1 = "https://drive.google.com/file/d/1zM6-e3FuDZGeQbCCjcarb1wJ65_Dki8A/view?usp=sharing";
        String img2 = "https://drive.google.com/file/d/1njPEQmMEGokZ0gJUN3VYFtNEgr5RviLD/view?usp=share_link";

        cartItemsList.add(new CartItem(img1, "sản phẩm 1", 123, 2123, 1, 1));
        cartItemsList.add(new CartItem(img2, "sản phẩm 2", 1223, 21323, 12, 2));

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

        tongTienThanhToan(CartListActivity.cartItemsList);

        lv = findViewById(R.id.orderLv);
        cartItemsList = createMockup();
        if(CartOfUser.customerAddress != null) {
            System.out.println("CartOfUser.customerAddress.getAddress() " + CartOfUser.customerAddress.getAddress() );

            addressTxt.setText(CartOfUser.customerAddress.getAddress().toString());

        }


        cartArrayAdapter = new CustomCartArrayAdapter(OrderListActivity.this,  CartListActivity.cartItemsList, "order");


        lv.setAdapter(cartArrayAdapter);
    }

    public static void tongTienThanhToan (ArrayList<CartItem> cartItemsList) {
        int total = 0;
        for (int i = 0; i < cartItemsList.size(); i++) {
            total+= (cartItemsList.get(i).getInitPrice() * cartItemsList.get(i).getAmout());
        }
        totalPriceNoShipTxt.setText(String.format("%,d",total));
        shipPriceTxt.setText("25,000");
        totalPriceTxt.setText((String.format("%,d", total + 25000)));
    }
}
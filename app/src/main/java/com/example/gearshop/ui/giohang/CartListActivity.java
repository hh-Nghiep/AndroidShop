package com.example.gearshop.ui.giohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.cart.ListItemCart;
import com.example.gearshop.ui.model.SanPham;
import com.example.gearshop.ui.orderHistory.CustomCartArrayAdapter;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnThanhToanHoaDon;
    public static TextView tongTientxt, numberItemTxt;
    public static  ArrayList<CartItem> cartItemsList;

    ListItemCart listItemCart = new ListItemCart();
    CustomCartArrayAdapter CustomCartArrayAdapter;
    ListView lv;
    ImageButton minusCartBtn, plusCartBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<SanPham> dataCart = new ArrayList<>();
        dataCart = CartOfUser.CartUser;
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

    private void setControl() {
        btnThanhToanHoaDon = findViewById(R.id.btnThanhToanHoaDon);
        minusCartBtn = findViewById(R.id.minusCartBtn);
        plusCartBtn = findViewById(R.id.plusCartBtn);
        numberItemTxt = findViewById(R.id.numberItemTxt);
        tongTientxt = findViewById(R.id.totalPriceTxt);
        cartItemsList = new ArrayList<>();

        cartItemsList = CartOfUser.globalCart;

        tinhTongTien(cartItemsList);
        lv = findViewById(R.id.CartLV);

        CustomCartArrayAdapter = new CustomCartArrayAdapter(CartListActivity.this, cartItemsList, "cart");

        lv.setAdapter(CustomCartArrayAdapter);



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

    public static void tinhTongTien(ArrayList<CartItem> cartItemsList) {

        int total = 0;
        for (int i = 0; i < cartItemsList.size(); i++) {
            total+= (cartItemsList.get(i).getInitPrice() * cartItemsList.get(i).getAmout());
        }
        tongTientxt.setText(String.format("%,d",total));
    }
}
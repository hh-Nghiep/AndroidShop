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
import com.example.gearshop.ui.model.SanPham;
import com.example.gearshop.ui.orderHistory.CustomCartArrayAdapter;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnThanhToanHoaDon;
    public static TextView tongTientxt, numberItemTxt;
    public static  ArrayList<CartItem> cartItemsList;
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

//        minusCartBtn.setOnClickListener(new AdapterView<>().OnClickListener() {
//
//            @Override
//            public void onI {
//                int number = Integer.parseInt(numberItemTxt.getText().toString());
//                if(number > 0) {
//                    number -= 1;
//                }
//                numberItemTxt.setText(String.valueOf(number));
//                cartArrayAdapter.notifyDataSetChanged();
//            }
//        });
//
//        plusCartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CartListActivity.this, OrderListActivity.class);
//                CartListActivity.this.startActivity(intent);
//            }
//        });
    }
    private ArrayList<CartItem> createMockup () {
        cartItemsList = new ArrayList<>();
        String img1 = "https://drive.google.com/file/d/1zM6-e3FuDZGeQbCCjcarb1wJ65_Dki8A/view?usp=sharing";
        String img2 = "https://drive.google.com/file/d/1njPEQmMEGokZ0gJUN3VYFtNEgr5RviLD/view?usp=share_link";

        cartItemsList.add(new CartItem(img1, "sản phẩm 1", 123, 2123, 1));
        cartItemsList.add(new CartItem(img2, "sản phẩm 2", 122223, 21323, 12));

        int tong1 = cartItemsList.get(0).getAmout() * cartItemsList.get(0).getInitPrice();
        cartItemsList.get(0).setTotalPrice(tong1);

        int tong2 = cartItemsList.get(1).getAmout() * cartItemsList.get(1).getInitPrice();
        cartItemsList.get(1).setTotalPrice(tong2);

        return cartItemsList;
    }
    private void setControl() {
        btnThanhToanHoaDon = findViewById(R.id.btnThanhToanHoaDon);
        minusCartBtn = findViewById(R.id.minusCartBtn);
        plusCartBtn = findViewById(R.id.plusCartBtn);
        numberItemTxt = findViewById(R.id.numberItemTxt);
        tongTientxt = findViewById(R.id.totalPriceTxt);

        cartItemsList = createMockup();


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
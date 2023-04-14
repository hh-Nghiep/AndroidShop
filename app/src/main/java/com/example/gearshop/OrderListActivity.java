package com.example.gearshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderListActivity extends AppCompatActivity {
    Button btnDatHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderListActivity.this, CartListActivity.class);
                OrderListActivity.this.startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnDatHang = findViewById(R.id.btnDatHang);
    }
}
package com.example.gearshop.ui.giohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gearshop.R;

public class CartListActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnThanhToanHoaDon;
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

    private void setControl() {
        btnThanhToanHoaDon = findViewById(R.id.btnThanhToanHoaDon);
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
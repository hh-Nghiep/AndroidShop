package com.example.gearshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gearshop.ui.Quanli.*;
import com.example.gearshop.ui.Chart.*;


public class ActivityQuanLySanPham extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);


        findViewById(R.id.btnQuanliSP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ItemListActivity.class));
            }
        });

        findViewById(R.id.btnThongke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChartActivity.class));
            }
        });
    }
}
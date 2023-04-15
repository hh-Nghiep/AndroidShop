package com.example.gearshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityQuanLySanPham extends AppCompatActivity {
    Button btnChonSP, btnThemSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        btnChonSP = findViewById(R.id.btnChonSP);
        btnThemSP = findViewById(R.id.btnThemSP);

        btnChonSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khai báo intent Bài 1
                Intent intentChonSP = new Intent(ActivityQuanLySanPham.this, UpdateActivity.class);
                // Khởi động == chuyển màn hình
                startActivity(intentChonSP);
            }
        });

        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khai báo intent Bài 1
                Intent intentThemSP = new Intent(ActivityQuanLySanPham.this, Them_SP.class);
                // Khởi động == chuyển màn hình
                startActivity(intentThemSP);
            }
        });
    }
}
package com.example.gearshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    Button btnChonSP, btnThemSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btnChonSP = findViewById(R.id.btnChonSP);
        btnThemSP = findViewById(R.id.btnThemSP);

        btnChonSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khai báo intent Bài 1
                Intent intentChonSP = new Intent(TestActivity.this, UpdateActivity.class);
                // Khởi động == chuyển màn hình
                startActivity(intentChonSP);
            }
        });

        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khai báo intent Bài 1
                Intent intentThemSP = new Intent(TestActivity.this, Them_SP.class);
                // Khởi động == chuyển màn hình
                startActivity(intentThemSP);
            }
        });
    }
}
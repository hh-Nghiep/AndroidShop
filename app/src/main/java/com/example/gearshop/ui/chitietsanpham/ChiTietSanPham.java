package com.example.gearshop.ui.chitietsanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gearshop.CartListActivity;
import com.example.gearshop.OrderListActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.Image_Adapter;
import com.example.gearshop.ui.model.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tvSoLuongCTSP, tvGiaCTSP;
    ViewPager vpChiTietSanPham;
    Image_Adapter mViewPagerAdapter;
    String[] imageUrls;
    Button btnThemVaoGioHang;

    Toolbar toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
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

    private void setEvent() {
        setThongTinSanPham();
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietSanPham.this, CartListActivity.class);
                ChiTietSanPham.this.startActivity(intent);
            }
        });


    }

    private void setThongTinSanPham(){
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        SanPham sp = (SanPham) bundle.get("san_pham");

        tvGiaCTSP.setText(sp.getGiaSP().toString());

        imageUrls = new String[]{
                "https://drive.google.com/uc?id=" + sp.getHinhAnh1().substring(32,sp.getHinhAnh1().lastIndexOf('/')),
                "https://drive.google.com/uc?id=" + sp.getHinhAnh2().substring(32,sp.getHinhAnh2().lastIndexOf('/')),
                "https://drive.google.com/uc?id=" + sp.getHinhAnh3().substring(32,sp.getHinhAnh3().lastIndexOf('/'))
        };
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new Image_Adapter(ChiTietSanPham.this, imageUrls);

        // Adding the Adapter to the ViewPager
        vpChiTietSanPham.setAdapter(mViewPagerAdapter);
    }

    private void setControl() {
        tvSoLuongCTSP = findViewById(R.id.tvSoLuongCTSP);
        tvGiaCTSP = findViewById(R.id.tvGiaCTSP);
        vpChiTietSanPham = findViewById(R.id.vpChiTietSanPham);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
    }


}
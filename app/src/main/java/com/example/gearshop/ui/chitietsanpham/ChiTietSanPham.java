package com.example.gearshop.ui.chitietsanpham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gearshop.R;
import com.example.gearshop.ui.model.Image_Adapter;
import com.example.gearshop.ui.model.SanPham;
import com.squareup.picasso.Picasso;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tvSoLuongCTSP, tvGiaCTSP;
    ImageView ivChiTietSanPham;
    ViewPager vpChiTietSanPham;
    Image_Adapter mViewPagerAdapter;
    String[] imageUrls;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        setControl();
        setEvent();
    }

    private void setEvent() {
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
    }


}
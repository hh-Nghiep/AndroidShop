package com.example.gearshop.ui.chitietsanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.giohang.CartListActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.Image_Adapter;
import com.example.gearshop.ui.model.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tvSoLuongCTSP, tvGiaCTSP, tvTenChiTietSP, tvChiTietSP;
    ViewPager vpChiTietSanPham;
    Image_Adapter mViewPagerAdapter;
    String[] imageUrls;
    Button btnThemVaoGioHang;
    ImageButton btnCong, btnTru;
    Toolbar toolbar;
    SanPham sp;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
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
                CartItem cartItem = new CartItem();
//                cartItem.setImg(sp.getHinhAnh1());
                cartItem.setName(sp.getTenSP());

//                cartItem.setAmout(sp.getSLSP); // chỗ này cần viết 1 cái rule thêm sản phẩm vào giỏ hàng
//                Nếu sp chưa có thì thêm mới vào list nếu sp đã có thì tăng số lượng
//                ds giỏ hàng dùng một số field khác như số lượng tổng giá nên dùng cái class CartItem mà lưu
//                CartOfUser.globalCart.add(cartItem);

                CartOfUser.CartUser.add(sp);
                Intent intent = new Intent(ChiTietSanPham.this, CartListActivity.class);
                ChiTietSanPham.this.startActivity(intent);
            }
        });

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(tvSoLuongCTSP.getText().toString());
                tvSoLuongCTSP.setText(String.valueOf(soLuong+1));
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(tvSoLuongCTSP.getText().toString());
                if(soLuong > 1){
                    tvSoLuongCTSP.setText(String.valueOf(soLuong-1));
                }else{
                    Toast.makeText(ChiTietSanPham.this, "Số lượng không thể nhỏ hơn 1 !!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setThongTinSanPham(){
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        sp = (SanPham) bundle.get("san_pham");
        String giaSP = formatter.format(sp.getGiaSP())+" VNĐ";
        tvGiaCTSP.setText(giaSP);
        tvTenChiTietSP.setText(sp.getTenSP());
        tvChiTietSP.setText(sp.getMieuTaSP());

        imageUrls = new String[3];
        if(sp.getHinhAnh1().length() > 32){
            imageUrls[0] = "https://drive.google.com/uc?id=" + sp.getHinhAnh1().substring(32,sp.getHinhAnh1().lastIndexOf('/'));
        }
        if(sp.getHinhAnh2().length() > 32){
            imageUrls[1] = "https://drive.google.com/uc?id=" + sp.getHinhAnh2().substring(32,sp.getHinhAnh2().lastIndexOf('/'));
        }
        if(sp.getHinhAnh3().length() > 32){
            imageUrls[2] = "https://drive.google.com/uc?id=" + sp.getHinhAnh3().substring(32,sp.getHinhAnh1().lastIndexOf('/'));
        }
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new Image_Adapter(ChiTietSanPham.this, imageUrls);

        // Adding the Adapter to the ViewPager
        vpChiTietSanPham.setAdapter(mViewPagerAdapter);
    }

    private void setControl() {
        tvSoLuongCTSP = findViewById(R.id.tvSoLuongCTSP);
        tvTenChiTietSP = findViewById(R.id.tvTenChiTietSP);
        tvChiTietSP = findViewById(R.id.tvChiTietSanPham);
        tvGiaCTSP = findViewById(R.id.tvGiaCTSP);
        vpChiTietSanPham = findViewById(R.id.vpChiTietSanPham);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        btnCong = findViewById(R.id.btnCongSoLuongCTSP);
        btnTru = findViewById(R.id.btnTruSoLuongCTSP);
    }
}
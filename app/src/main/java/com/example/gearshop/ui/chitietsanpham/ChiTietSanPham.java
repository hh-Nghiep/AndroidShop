package com.example.gearshop.ui.chitietsanpham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
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
import com.example.gearshop.ui.cart.ListItemCart;
import com.example.gearshop.ui.giohang.CartListActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.model.Image_Adapter;
import com.example.gearshop.ui.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tvSoLuongCTSP, tvGiaCTSP, tvTenChiTietSP, tvChiTietSP, tvsoLuongSanCo;
    ViewPager vpChiTietSanPham;
    Image_Adapter mViewPagerAdapter;
    String[] imageUrls;
    Button btnThemVaoGioHang;
    ImageButton btnCong, btnTru;
    Toolbar toolbar;
    SanPham sp;

    ListItemCart ListItemCart;

    Integer soLuongSanCo;
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
                if(InfoUser.id_user != 0){
                    CartItem cartItem = new CartItem();
//
                    cartItem.setImg(sp.getHinhAnh1());
                    cartItem.setName(sp.getTenSP());
                    cartItem.setAmout(Integer.parseInt(tvSoLuongCTSP.getText().toString()));
                    cartItem.setInitPrice(sp.getGiaSP());
                    cartItem.setTotalPrice(cartItem.getInitPrice()*cartItem.getAmout());
                    cartItem.setId(sp.getMaSP());
//
//                ArrayList<CartItem> ListItem = ListItemCart.getCartItemsList();
//                ListItem.add(cartItem);
//                ListItemCart.setCartItemsList(ListItem);
//                cartItem.setAmout(sp.getSLSP); // chỗ này cần viết 1 cái rule thêm sản phẩm vào giỏ hàng
//                Nếu sp chưa có thì thêm mới vào list nếu sp đã có thì tăng số lượng
//                ds giỏ hàng dùng một số field khác như số lượng tổng giá nên dùng cái class CartItem mà lưu
//                CartOfUser.globalCart.add(cartItem);
                    if(CartOfUser.globalCart.size() > 0){
                        Boolean check = false;
                        for( Integer i = 0 ; i < CartOfUser.globalCart.size() ; i++){
                            if(CartOfUser.globalCart.get(i).getId().equals(sp.getMaSP())){
                                check = true;
                                Integer SLBanDau = CartOfUser.globalCart.get(i).getAmout();
                                cartItem.setAmout(SLBanDau + Integer.parseInt(tvSoLuongCTSP.getText().toString()));
                                CartOfUser.globalCart.set(i, cartItem);
                                break;
                            }
                        }
                        if(check == false){
                            CartOfUser.globalCart.add(cartItem);
                        }
                    }else{
                        CartOfUser.globalCart.add(cartItem);
                    }

//                CartOfUser.globalCart.add(cartItem);
                    Intent intent = new Intent(ChiTietSanPham.this, CartListActivity.class);
                    ChiTietSanPham.this.startActivity(intent);
                }else{
                    Toast.makeText(ChiTietSanPham.this, "Vui Lòng đăng nhập trước khi thêm sản phẩm vào giỏ hàng !!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(tvSoLuongCTSP.getText().toString());
                if(soLuong == soLuongSanCo){
                    Toast.makeText(ChiTietSanPham.this, "Số lượng sẵn có trong kho không đủ !!!",Toast.LENGTH_LONG).show();
                } else if (soLuong < soLuongSanCo) {
                    tvSoLuongCTSP.setText(String.valueOf(soLuong+1));
                }
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
        soLuongSanCo = sp.getSoLuong();
        String giaSP = formatter.format(sp.getGiaSP())+" VNĐ";
        tvGiaCTSP.setText(giaSP);
        tvTenChiTietSP.setText(sp.getTenSP());
        tvChiTietSP.setText(sp.getMieuTaSP());
        tvsoLuongSanCo.setText(sp.getSoLuong().toString());
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
        tvsoLuongSanCo = findViewById(R.id.soLuongSanCo);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
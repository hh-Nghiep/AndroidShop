package com.example.gearshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gearshop.ui.banphim.BanPhimFragment;
import com.example.gearshop.ui.chuot.ChuotFragment;
import com.example.gearshop.ui.giohang.CartListActivity;
import com.example.gearshop.ui.home.HomeFragment;
import com.example.gearshop.ui.Quanli.ItemListActivity;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.login_register.ui.login.LoginActivity;
import com.example.gearshop.ui.login_register.ui.login.RegisterActivity;
import com.example.gearshop.ui.model.SanPham;
import com.example.gearshop.ui.nguoidung.HoTroFragment;
import com.example.gearshop.ui.nguoidung.InfoFragment;
import com.example.gearshop.ui.nguoidung.PasswordFragment;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_CHUOT = 1;
    private static final int FRAGMENT_BANPHIM = 2;

    private static final int FRAGMENT_GIOHANG = 3;

    private static final int FRAGMENT_THONGTINNGUOIDUNG = 4;

    private static final int FRAGMENT_THAYDOIMATKHAU = 5;

    private static final int FRAGMENT_HOTRO = 6;


    private int currentFragment = FRAGMENT_HOME;

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    Connection connection;
    TextView tvTenNguoiDung, tvIdNguoiDung, tvSDTNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        } else if (id == R.id.nav_chuot) {
            if(currentFragment != FRAGMENT_CHUOT){
                replaceFragment(new ChuotFragment());
                currentFragment = FRAGMENT_CHUOT;
            }
        }else if (id == R.id.nav_banphim) {
            if(currentFragment != FRAGMENT_BANPHIM){
                replaceFragment(new BanPhimFragment());
                currentFragment = FRAGMENT_BANPHIM;
            }
        }else if (id == R.id.nav_giohang) {
            Intent intent = new Intent(this, CartListActivity.class);
            this.startActivity(intent);
        }else if (id == R.id.nav_ThongTinUser) {
            if(currentFragment != FRAGMENT_THONGTINNGUOIDUNG){
                replaceFragment(new InfoFragment());
                currentFragment = FRAGMENT_THONGTINNGUOIDUNG;
            }
        }else if (id == R.id.nav_ThayDoiMK) {
            if(currentFragment != FRAGMENT_THAYDOIMATKHAU){
                replaceFragment(new PasswordFragment());
                currentFragment = FRAGMENT_THAYDOIMATKHAU;
            }
        }else if (id == R.id.nav_HoTro) {
            if(currentFragment != FRAGMENT_HOTRO){
                replaceFragment(new HoTroFragment());
                currentFragment = FRAGMENT_HOTRO;
            }
        }else if (id == R.id.nav_quanlysanpham) {
            Intent intent = new Intent(this, ActivityQuanLySanPham.class);
            this.startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
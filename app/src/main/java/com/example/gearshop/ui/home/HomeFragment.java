package com.example.gearshop.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.gearshop.R;
import com.example.gearshop.ui.MuaSamNgay.MuaSamNgayActivity;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.login_register.ui.login.LoginActivity;
import com.example.gearshop.ui.login_register.ui.login.RegisterActivity;
import com.example.gearshop.ui.model.Image_Adapter;

public class HomeFragment extends Fragment {
    View view;
    LinearLayout llDangNhap, llDangKy;
    TextView tvDangNhap, tvDangKy, tvXinChao;
    ViewPager vpHome;

    Image_Adapter mViewPagerAdapter;

    String[] imageUrls;

    Button btnMuaSamNgay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        if(InfoUser.id_user != 0){
            llDangNhap = view.findViewById(R.id.llDangNhap);
            llDangKy = view.findViewById(R.id.llDangKy);
            tvXinChao = view.findViewById(R.id.tvXinChao);
            llDangKy.setVisibility(View.INVISIBLE);
            llDangNhap.setVisibility(View.INVISIBLE);
            tvXinChao.setText("Xin Chào: " + InfoUser.name_user);
        }
        setControl();
        setEvent();
        return view;
    }
    private void setEvent() {
        KhoiTao();
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), LoginActivity.class);
                HomeFragment.this.startActivity(intent);
            }
        });

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), RegisterActivity.class);
                HomeFragment.this.startActivity(intent);
            }
        });

        btnMuaSamNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), MuaSamNgayActivity.class);
                HomeFragment.this.startActivity(intent);
            }
        });
    }

    private void KhoiTao(){
        imageUrls = new String[5];
        imageUrls[0] = "https://drive.google.com/uc?id=" + "14oBbR5E0cwzH4fVnQuIt78087EydsRBX";
        imageUrls[1] = "https://drive.google.com/uc?id=" + "1DJvzwcKwcspr-0ab8vCzMkEZTCMIRhjI";
        imageUrls[2] = "https://drive.google.com/uc?id=" + "1bmFwvTkgYHt6AQHRuHnNZObg3YvZlPPO";
        imageUrls[3] = "https://drive.google.com/uc?id=" + "1KK5ybqtUjWqrGMHvcKOa0ZbUd0AMsNHI";
        imageUrls[4] = "https://drive.google.com/uc?id=" + "1Q1NEJXMqFYOaJorlgBzQLVPOVt_dZ3LB";
        mViewPagerAdapter = new Image_Adapter(this.getContext(), imageUrls);
        vpHome.setAdapter(mViewPagerAdapter);
    }

    private void setControl() {
        vpHome = view.findViewById(R.id.vphome);
        tvDangNhap = view.findViewById(R.id.tvDangNhap);
        tvDangKy = view.findViewById(R.id.tvDangKy);
        btnMuaSamNgay = view.findViewById(R.id.imagebtnMuaSamNgay);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
package com.example.gearshop.ui.login_register.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.home.HomeFragment;
import com.example.gearshop.ui.model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    String EMAIL_REGEX =   "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    String PHONE_REGEX =   "^[0-9._-]{12}$";
    String CMND_REGEX =   "^[0-9._-]{9}$";
    String CCCD_REGEX =   "^[0-9._-]{12}$";
    Toolbar toolbar;
    EditText etEmail, etMatKhau, etTenNguoiDung, etSDT, etCMND, etDiaChi;
    TextView tvThongBao;
    Button btnregister;
    String email, matKhau, tenNguoiDung, SDT, CMND, diaChi;

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                matKhau = etMatKhau.getText().toString();
                tenNguoiDung = etTenNguoiDung.getText().toString();
                SDT =  etSDT.getText().toString();
                CMND = etCMND.getText().toString();
                diaChi = etDiaChi.getText().toString();
                Pattern ptEmail = Pattern.compile(EMAIL_REGEX);
                Pattern ptPhone = Pattern.compile(PHONE_REGEX);
                Pattern ptCMND = Pattern.compile(CMND_REGEX);
                Pattern ptCCCD = Pattern.compile(CCCD_REGEX);
                if(email.equals("") || matKhau.equals("") ||  tenNguoiDung.equals("") ||  SDT.equals("") ||  CMND.equals("") ||  diaChi.equals("")){
                    tvThongBao.setText("Vui Lòng Điền Đầy Đủ Thông Tin !!!");
                }else if(ptEmail.matcher(email).matches()){
                    tvThongBao.setText("Email không hợp lệ. Vui lòng kiểm tra lại Email !!!");
                } else if (ptCMND.matcher(CMND).matches() && ptCCCD.matcher(CMND).matches()) {
                    tvThongBao.setText("CMND/CCCD không hợp lệ. Vui lòng kiểm tra lại CMND/CCCD !!!");
                } else if (ptPhone.matcher(SDT).matches()) {
                    tvThongBao.setText("Số điện thoại không hợp lệ. Vui lòng kiểm tra lại số điện thoại !!!");
                }else{
                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String sql ="INSERT INTO TaiKhoan values (?,?,?,?,?,?,?) ";
                            try {
                                PreparedStatement ps = connection.prepareStatement(sql);
                                ps.setString(1, email);
                                ps.setString(2, matKhau);
                                ps.setString(3, tenNguoiDung);
                                ps.setString(4, SDT);
                                ps.setString(5, CMND);
                                ps.setString(6, diaChi);
                                ps.setString(7, "1");
                                ps.executeUpdate();
                                ps.close();
                                connection.close();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } catch (SQLException ex) {
                                Log.d("err", ex.getMessage());
                            }
                        }
                    }catch (Exception ex){
                        Log.d("err", ex.getMessage());
                    }
                }
            }
        });
    }

    private void setControl() {
        etEmail = findViewById(R.id.etEmail);
        etMatKhau = findViewById(R.id.etDangKyPassword);
        etTenNguoiDung = findViewById(R.id.etDangKyTenNguoiDung);
        etSDT = findViewById(R.id.etDangKySDT);
        etCMND = findViewById(R.id.etDangKyCMND);
        etDiaChi = findViewById(R.id.etDangKyDiaChi);
        tvThongBao = findViewById(R.id.tvThongBao);
        btnregister = findViewById(R.id.btnregister);
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
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
import android.widget.Toast;

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
    String EMAIL_REGEX =   "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
    String PHONE_REGEX =   "^[0-9._-]{10}$";
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
                Boolean checkEmail = true, checkSDT = true, checkCMND = true;
                if(email.equals("") || matKhau.equals("") ||  tenNguoiDung.equals("") ||  SDT.equals("") ||  CMND.equals("") ||  diaChi.equals("")){
                    tvThongBao.setText("Vui Lòng Điền Đầy Đủ Thông Tin !!!");
                }else if(!ptEmail.matcher(email).matches()){
                    tvThongBao.setText("Email không hợp lệ. Vui lòng kiểm tra lại Email !!!");
                } else if (!ptCMND.matcher(CMND).matches() && !ptCCCD.matcher(CMND).matches()) {
                    tvThongBao.setText("CMND/CCCD không hợp lệ. Vui lòng kiểm tra lại CMND/CCCD !!!");
                } else if (!ptPhone.matcher(SDT).matches()) {
                    tvThongBao.setText("Số điện thoại không hợp lệ. Vui lòng kiểm tra lại số điện thoại !!!");
                }else{
                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where email = '" + email + "'";
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkEmail = false;
                            }
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }

                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where SoDienThoai = '" + SDT + "'";
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkSDT = false;
                            }
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }

                    try {
                        ConnectSQL con = new ConnectSQL();
                        connection = con.CONN();
                        if(connection != null){
                            String query = "select * from TaiKhoan where CMND = '" + CMND + "'";
                            Statement statement = connection.createStatement();
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()){
                                checkCMND = false;
                            }
                        }
                    }catch (Exception ex){
                        System.err.print(ex.getMessage());
                    }


                    if(checkEmail && checkSDT && checkCMND){
                        try {
                            ConnectSQL con = new ConnectSQL();
                            connection = con.CONN();
                            if(connection != null){
                                String sql ="INSERT INTO TaiKhoan values (?,?,?,?,?,?,?) ";
                                try {
                                    PreparedStatement ps = connection.prepareStatement(sql);
                                    ps.setString(1, email.trim());
                                    ps.setString(2, matKhau.trim());
                                    ps.setString(3, tenNguoiDung.trim());
                                    ps.setString(4, SDT.trim());
                                    ps.setString(5, CMND.trim());
                                    ps.setString(6, diaChi.trim());
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
                    }else{
                        if(!checkEmail){
                            Toast.makeText(RegisterActivity.this, "Email đã tồn tại. Vui lòng chọn email khác !!!",Toast.LENGTH_LONG).show();
                        }
                        if(!checkSDT){
                            Toast.makeText(RegisterActivity.this, "SDT đã tồn tại. Vui lòng chọn SDT khác !!!",Toast.LENGTH_LONG).show();
                        }
                        if(!checkCMND){
                            Toast.makeText(RegisterActivity.this, "CMND đã tồn tại. Vui lòng chọn CMND khác !!!",Toast.LENGTH_LONG).show();
                        }
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
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
import com.example.gearshop.MainActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.SanPham;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;

    EditText etTaiKhoan, etMatKhau;
    Button btnLogin;
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean flag = false;
                try {
                    ConnectSQL con = new ConnectSQL();
                    connection = con.CONN();
                    if(connection != null){
                        String query ="select * from TaiKhoan where email='" + etTaiKhoan.getText() + "' and MatKhau='" + etMatKhau.getText() + "'";
                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery(query);
                        while (rs.next()){
                            flag = true;
                            InfoUser.id_user = rs.getInt(1);
                            InfoUser.Email_user = rs.getString(2);
                            InfoUser.name_user = rs.getString(4);
                            InfoUser.SDT_user = rs.getString(5);
                            InfoUser.CMND_user = rs.getString(6);
                            InfoUser.DiaChi_user = rs.getString(7);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.startActivity(intent);
                        }
                    }
                    if(!flag){
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không hợp lệ !!!",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    System.err.print(ex.getMessage());
                }
            }
        });
    }

    private void setControl() {
        etMatKhau = findViewById(R.id.etpassword);
        etTaiKhoan = findViewById(R.id.ettaikhoan);
        btnLogin = findViewById(R.id.btnlogin);
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
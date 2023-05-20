package com.example.gearshop.ui.Quanli;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateActivity extends AppCompatActivity {

    Connection connection;

    Integer MaSP,MaTL,MaTH;
    String TenSP,DesSP,Hinh1,Hinh2,Hinh3;
    Float GiaSP;

    EditText editMaSP, editTenSP, editMaTL, editMaTH, editPrice, etHinh1,etHinh2,etHinh3;

    TextInputEditText editDesc;
    Button btnUpdate, btnXoa, btnHuy;
    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setControl();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapNhatDL();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XoaDL();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ItemListActivity.class));
            }
        });
    }

    public void CapNhatDL(){
        MaSP = Integer.valueOf(editMaSP.getText().toString());
        TenSP = editTenSP.getText().toString();
        MaTL = Integer.valueOf(editMaTL.getText().toString());
        MaTH = Integer.valueOf(editMaTH.getText().toString());
        GiaSP = Float.valueOf(editPrice.getText().toString());
        DesSP = editDesc.getText().toString();
        Hinh1 = etHinh1.getText().toString();
        Hinh2 = etHinh2.getText().toString();
        Hinh3 = etHinh3.getText().toString();
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            String query = "UPDATE SanPham SET TenSP = ?, GiaSP = ?, MaTL = ?, MaTH = ?, MieuTaSP = ?, HinhAnh1 = ?, HinhAnh2 = ?, HinhAnh3 = ? WHERE MaSP = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, TenSP);
            stmt.setFloat(2, GiaSP);
            stmt.setInt(3, MaTL);
            stmt.setInt(4, MaTH);
            stmt.setString(5, DesSP);
            stmt.setString(6, Hinh1);
            stmt.setString(7, Hinh2);
            stmt.setString(8, Hinh3);
            stmt.setInt(9, MaSP);
            stmt.executeUpdate();
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }

    public void XoaDL(){
        MaSP = Integer.valueOf(editMaSP.getText().toString());
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            String query = "DELETE FROM SanPham WHERE MaSP = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaSP);
            stmt.executeUpdate();
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }
    private void setControl(){
        editMaSP = findViewById(R.id.editMaSP);
        editTenSP = findViewById(R.id.editTenSP);
        editMaTL = findViewById(R.id.editMaTL);
        editMaTH = findViewById(R.id.editMaTH);
        editPrice = findViewById(R.id.editPrice);
        editDesc = findViewById(R.id.editDesc);
        btnUpdate = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnDelete);
        btnHuy = findViewById(R.id.btnCancel);
        etHinh1 = findViewById(R.id.etHinh1);
        etHinh2 = findViewById(R.id.etHinh2);
        etHinh3 = findViewById(R.id.etHinh3);
    }
}
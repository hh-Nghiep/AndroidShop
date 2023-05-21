package com.example.gearshop.ui.Quanli;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setControl();

        Intent intent = getIntent();
        String maSP = intent.getStringExtra("MaSP");
        String tenSP = intent.getStringExtra("TenSP");
        String maTL = intent.getStringExtra("MaTL");
        String maTH = intent.getStringExtra("MaTH");
        String price = intent.getStringExtra("Price");
        String desc = intent.getStringExtra("Desc");
        String Hinh1 = intent.getStringExtra("Hinh1");
        String Hinh2 = intent.getStringExtra("Hinh2");
        String Hinh3 = intent.getStringExtra("Hinh3");

        editMaSP.setText(maSP);
        editTenSP.setText(tenSP);
        editMaTL.setText(maTL);
        editMaTH.setText(maTH);
        editPrice.setText(price);
        editDesc.setText(desc);
        etHinh1.setText(Hinh1);
        etHinh2.setText(Hinh2);
        etHinh3.setText(Hinh3);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Xác nhận thay đổi");
                dialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CapNhatDL();
                    }
                });
                dialog.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Xác nhận xoá");
                dialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XoaDL();
                        Toast.makeText(UpdateActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                dialog.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void CapNhatDL(){
        if (editTenSP.getText().toString().equals("") || editMaTL.getText().toString().equals("") || editMaTH.getText().toString().equals("") || editPrice.getText().toString().equals("")){
            editTenSP.setError("Bắc buộc");
            editMaTL.setError("Bắc buộc");
            editMaTH.setError("Bắc buộc");
            editPrice.setError("Bắc buộc");
        }else
        {
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
                Toast.makeText(UpdateActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                finish();
            }catch (Exception ex){
                System.err.print(ex.getMessage());
            }
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
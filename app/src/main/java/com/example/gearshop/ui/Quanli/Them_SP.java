package com.example.gearshop.ui.Quanli;

//import static com.example.gearshop.R.id.edtId;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.SanPham;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.Integer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import android.widget.Toast;


public class Them_SP extends AppCompatActivity {

    Connection connection;
    Button btnThem, btnHuy;

    Integer MaSP,MaTL,MaTH;
    String TenSP,DesSP,Hinh1,Hinh2,Hinh3;
    Float GiaSP;
    SanPham sp;

    ArrayList<SanPham> dataSP = new ArrayList<>();

    EditText edtId, edtName, edtLoai, edtThuonghieu,editTextNumber, edtHinh1,edtHinh2,edtHinh3;
    TextInputEditText edtDesc;

    AlertDialog.Builder dialog;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sp);
        setControl();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ThemDL();
//              adapter.notifyDataSetChanged();
                dialog.setTitle("Thông báo").setMessage("Xác nhận").setCancelable(true)
                                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ThemDL();
//                                        adapter.notifyDataSetChanged();
//                                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                                        finish();
                                    }
                                })
                        .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void ThemDL(){
        if (edtName.getText().toString().equals("") || edtLoai.getText().toString().equals("") || edtThuonghieu.getText().toString().equals("") || editTextNumber.getText().toString().equals("")){
            edtName.setError("Bắc buộc");
            edtLoai.setError("Bắc buộc");
            edtThuonghieu.setError("Bắc buộc");
            editTextNumber.setError("Bắc buộc");
        } else{
            TenSP = edtName.getText().toString();
            MaTL = Integer.valueOf(edtLoai.getText().toString());
            MaTH = Integer.valueOf(edtThuonghieu.getText().toString());
            GiaSP = Float.valueOf(editTextNumber.getText().toString());
            DesSP = edtDesc.getText().toString();
            Hinh1 = edtHinh1.getText().toString();
            Hinh2 = edtHinh2.getText().toString();
            Hinh3 = edtHinh3.getText().toString();
//        System.out.print(MaSP);
            try {
                ConnectSQL con = new ConnectSQL();
                connection = con.CONN();
//            String query = "insert into SanPham(MaSP, TenSP,GiaSP,MaTL,MaTH) values(?,?,?,?,?)" ;
                String query = "insert into SanPham(TenSP,GiaSP,MaTL,MaTH, MieuTaSP,HinhAnh1,HinhAnh2,HinhAnh3)" +"values(?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setInt(1, Integer.parseInt(edtId.getText().toString()));
//            stmt.setString(2, edtName.getText().toString());
//            stmt.setFloat(3, Float.parseFloat(editTextNumber.getText().toString()));
//            stmt.setInt(4, Integer.parseInt(edtLoai.getText().toString()));
//            stmt.setInt(5, Integer.parseInt(edtThuonghieu.getText().toString()));
                stmt.setString(1, TenSP);
                stmt.setFloat(2, GiaSP);
                stmt.setInt(3, MaTL);
                stmt.setInt(4, MaTH);
                stmt.setString(5, DesSP);
                stmt.setString(6,Hinh1);
                stmt.setString(7,Hinh2);
                stmt.setString(8,Hinh3);
                stmt.execute();
                finish();
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                System.err.print(ex.getMessage());
            }
        }
    }
    private void setControl(){
//        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtLoai = findViewById(R.id.edtLoai);
        edtThuonghieu = findViewById(R.id.edtThuonghieu);
        editTextNumber = findViewById(R.id.editTextNumber);
        edtDesc = findViewById(R.id.edtDes);
        btnThem = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
        dialog = new AlertDialog.Builder(this);
        edtHinh1 = findViewById(R.id.edtHinh1);
        edtHinh2 = findViewById(R.id.edtHinh2);
        edtHinh3 = findViewById(R.id.edtHinh3);
    }
}
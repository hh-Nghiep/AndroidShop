package com.example.gearshop.ui.MuaSamNgay;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.login_register.ui.login.RegisterActivity;
import com.example.gearshop.ui.model.SPAdapter;
import com.example.gearshop.ui.model.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class MuaSamNgayActivity extends AppCompatActivity {
    String[] items = {"Tất cả", "Corsair", "Dare-u", "Fuhlen", "Logitech",  "Razer"};
    AutoCompleteTextView autoComplate;
    ArrayAdapter<String> adapterItems;
    ArrayList<SanPham> dataSP = new ArrayList<>();

    ListView lvDanhSachSP;
    RecyclerView rcvSP;
    SPAdapter spAdapter;
    Toolbar toolbar;
    Connection connection;
    LinearLayout LLO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_danh_sach_san_pham);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        KhoiTao();
        setEvent(dataSP);
        autoComplate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                ArrayList<SanPham> dataSP2 = new ArrayList<>();
                try {
                    ConnectSQL con = new ConnectSQL();
                    connection = con.CONN();
                    if(connection != null){
                        String query = "select sp.MaSP, sp.TenSP, sp.GiaSP, sp.MaTL, th.TenTH, sp.MieuTaSP, sp.HinhAnh1, sp.HinhAnh2, sp.HinhAnh3, sp.SoLuong from SanPham as sp left join ThuongHieu as th on th.MaTH = sp.MaTH where th.TenTH = '" + item + "'";
                        if(item.equals("Tất cả")){
                            query = "select sp.MaSP, sp.TenSP, sp.GiaSP, sp.MaTL, th.TenTH, sp.MieuTaSP, sp.HinhAnh1, sp.HinhAnh2, sp.HinhAnh3, sp.SoLuong from SanPham as sp left join ThuongHieu as th on th.MaTH = sp.MaTH";
                        }
                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery(query);
                        while (rs.next()){
                            dataSP2.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10)));
                        }
                        setEvent(dataSP2);
                    }
                }catch (Exception ex){
                    System.err.print(ex.getMessage());
                }
            }
        });
    }

    private void setEvent(ArrayList<SanPham> dataSP) {
        spAdapter = new SPAdapter();
        rcvSP.setLayoutManager(new GridLayoutManager(this, 2));
        spAdapter.setListSP(MuaSamNgayActivity.this, dataSP);
        rcvSP.setAdapter(spAdapter);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoComplate.setAdapter(adapterItems);
    }

    private void KhoiTao(){
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select sp.MaSP, sp.TenSP, sp.GiaSP, sp.MaTL, th.TenTH, sp.MieuTaSP, sp.HinhAnh1, sp.HinhAnh2, sp.HinhAnh3, sp.SoLuong from SanPham as sp left join ThuongHieu as th on th.MaTH = sp.MaTH";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    dataSP.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10)));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }

    private void setControl() {
        lvDanhSachSP = findViewById(R.id.lvChuot);
        rcvSP = findViewById(R.id.rcvSP);
        autoComplate = findViewById(R.id.autoComplete);
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
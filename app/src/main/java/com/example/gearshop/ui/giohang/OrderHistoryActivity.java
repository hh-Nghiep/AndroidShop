package com.example.gearshop.ui.giohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.MainActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.home.HomeFragment;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.model.SanPham;
import com.example.gearshop.ui.orderHistory.OrderHistoryAdapter;
import com.example.gearshop.ui.orderHistory.OrderHistoryItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button  btnChiTietDonHang;
    TextView tongTientxt;
    ArrayList<OrderHistoryItem> orderHistoryItemsList;
    OrderHistoryAdapter orderHistoryAdapter;
    ListView lv;
    String[] items = {"Tất cả", "Chờ Duyệt", "Đang vận chuyển", "Đã giao",  "Đã hủy"};
    AutoCompleteTextView autoComplate;
    ArrayAdapter<String> adapterItems;
    static Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
        autoComplate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                switch (item) {
                    case "Tất cả": {
                        layDSDonhang(5);
                        break;
                    }
                    case "Chờ Duyệt": {
                        layDSDonhang(0);
                        break;
                    }
                    case "Đang vận chuyển": {
                        layDSDonhang(1);
                        break;
                    }
                    case "Đã giao": {
                        layDSDonhang(2);
                        break;
                    }
                    case "Đã hủy": {
                        layDSDonhang(3);
                        break;
                    }

                }
                orderHistoryAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, R.layout.danh_sach_don_hang_da_dat, orderHistoryItemsList);

                lv.setAdapter(orderHistoryAdapter);
                Toast.makeText( getApplicationContext(), item,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setEvent() {

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoComplate.setAdapter(adapterItems);


    }

    private void setControl() {
        layDSDonhang(5);
        tongTientxt = findViewById(R.id.orderHistoryTotalPriceTxt);
//        orderHistoryItemsList = createMockup();

        autoComplate = findViewById(R.id.statusSelection);

        lv = findViewById(R.id.OrdersHistoryActivityLv);

        orderHistoryAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, R.layout.danh_sach_don_hang_da_dat, orderHistoryItemsList);

        lv.setAdapter(orderHistoryAdapter);
    }

    private void layDSDonhang (int status) {

        orderHistoryItemsList = new ArrayList<>();
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "";
//                String query = "select TenNguoiNhan, DiaChi, SoDienThoai, NgayTaoDH, TrangThaiDH from DonHang where maTK = '" + InfoUser.id_user + "'";
                if (status == 5) {
                    query = "select MaDH, TenNguoiNhan, DiaChi, SoDienThoai, NgayTaoDH, TrangThaiDH from DonHang where maTK = '" + InfoUser.id_user + "'";
                } else {
                    query = "select MaDH, TenNguoiNhan, DiaChi, SoDienThoai, NgayTaoDH, TrangThaiDH from DonHang where maTK = '" + InfoUser.id_user + "' and TrangThaiDH = '" + status + "' ";
                }
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = sdf.parse(rs.getString(5));
                    orderHistoryItemsList.add(new OrderHistoryItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), date, rs.getInt(6)));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
            Toast.makeText( getApplicationContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
        }

        for( Integer i = 0 ; i < orderHistoryItemsList.size() ; i++){
            try {
                ConnectSQL con = new ConnectSQL();
                connection = con.CONN();
                if(connection != null){
                    ArrayList<CartItem> list = new ArrayList<>();
//                String query = "select TenNguoiNhan, DiaChi, SoDienThoai, NgayTaoDH, TrangThaiDH from DonHang where maTK = '" + InfoUser.id_user + "'";
                    String query = "select HinhAnh = (select HinhAnh1 from SanPham where MaSP = ct.MaSP), TenSanPham = (select TenSP from SanPham where MaSP = ct.MaSP), DonGia = (select GiaSP from SanPham where MaSP = ct.MaSP), ct.TongTien, ct.SoLuongSP, ct.MaSP from  ChiTietDH as ct where ct.MaDH = '" + orderHistoryItemsList.get(i).getMaDH() + "'";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()){

                        list.add(new CartItem(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
                    }
                    orderHistoryItemsList.get(i).setDsSanPham(list);
                }
            }catch (Exception ex){
                System.err.print(ex.getMessage());
                Toast.makeText( getApplicationContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(OrderHistoryActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
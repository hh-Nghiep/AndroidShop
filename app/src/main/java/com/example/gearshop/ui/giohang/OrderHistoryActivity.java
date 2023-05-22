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
import com.example.gearshop.ui.model.SanPham;
import com.example.gearshop.ui.orderHistory.OrderHistoryAdapter;
import com.example.gearshop.ui.orderHistory.OrderHistoryItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnThanhToanHoaDon;
    TextView tongTientxt;
    ArrayList<OrderHistoryItem> orderHistoryItemsList;
    OrderHistoryAdapter orderHistoryAdapter;
    ListView lv;
    String[] items = {"Tất cả", "Chờ Duyệt", "Đang vận chuyển", "Đã giao",  "Đã hủy"};
    AutoCompleteTextView autoComplate;
    ArrayAdapter<String> adapterItems;
    Connection connection;
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
                Toast.makeText( getApplicationContext(), item,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setEvent() {

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoComplate.setAdapter(adapterItems);
    }
    private ArrayList<OrderHistoryItem> createMockup () {
        orderHistoryItemsList = new ArrayList<>();
        ArrayList<CartItem> cartList = new ArrayList<>();
        String img1 = "https://drive.google.com/file/d/1zM6-e3FuDZGeQbCCjcarb1wJ65_Dki8A/view?usp=sharing";
        String img2 = "https://drive.google.com/file/d/1njPEQmMEGokZ0gJUN3VYFtNEgr5RviLD/view?usp=share_link";

        cartList.add(new CartItem(img1, "sản phẩm 1", 1221323, 21332123, 2, 1));
        cartList.add(new CartItem(img2, "sản phẩm 2", 1223, 21323, 12, 2));

        ArrayList<CartItem> cartList1 = new ArrayList<>();
        cartList1.add(new CartItem(img2, "sản phẩm 1 new", 1221323, 21332123, 2, 3));
        cartList1.add(new CartItem(img1, "sản phẩm 2 new",1223, 21323, 12, 4));


        orderHistoryItemsList.add(new OrderHistoryItem("Phong", "123 abc", "Phong@gmail.com", "1231233123", cartList));
        orderHistoryItemsList.add(new OrderHistoryItem("Phong134", "12323 abc", "Phong1@gmail.com", "1231233123", cartList1));

        return orderHistoryItemsList;
    }
    private void setControl() {
        layDSDonhang();
        tongTientxt = findViewById(R.id.orderHistoryTotalPriceTxt);
//        orderHistoryItemsList = createMockup();

        autoComplate = findViewById(R.id.statusSelection);

        lv = findViewById(R.id.OrdersHistoryActivityLv);

        orderHistoryAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, R.layout.danh_sach_don_hang_da_dat, orderHistoryItemsList);

        lv.setAdapter(orderHistoryAdapter);
    }

    private void layDSDonhang () {
        orderHistoryItemsList = new ArrayList<>();
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
//                String query = "select TenNguoiNhan, DiaChi, SoDienThoai, NgayTaoDH, TrangThaiDH from DonHang where maTK = '" + InfoUser.id_user + "'";
                String query = "select MaDH, TenNguoiNhan, DiaChi, SoDienThoai, NgayTaoDH, TrangThaiDH from DonHang where maTK = '" + 3 + "'";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = sdf.parse(rs.getString(5));
                    orderHistoryItemsList.add(new OrderHistoryItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), date, rs.getInt(6)));
                }
                Toast.makeText( getApplicationContext(), "lay danh sach don hang!",Toast.LENGTH_LONG).show();
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
                    Toast.makeText( getApplicationContext(), "lay chi tiet danh sach don hang!",Toast.LENGTH_LONG).show();
                }
            }catch (Exception ex){
                System.err.print(ex.getMessage());
                Toast.makeText( getApplicationContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }


//    private void xemChiTietDonHang () {
//        ArrayList<CartItem> cartItemsList = new ArrayList<>();
//        try {
//            ConnectSQL con = new ConnectSQL();
//            connection = con.CONN();
//            if(connection != null){
//                String query = "select sp.HinhAnh1, sp.TenSP, sp.GiaSP, ct.SoLuongSP from ChiTietDH ct left join SanPham sp on sp.MaSP = ct.MaSP where MaDH='"+ maDH +"'";
//                Statement statement = connection.createStatement();
//                ResultSet rs = statement.executeQuery(query);
//                while (rs.next()){
//                    cartItemsList.add(new CartItem(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(3) * rs.getInt(4), rs.getInt(4)));
//                }
//            }
//        }catch (Exception ex){
//            System.err.print(ex.getMessage());
//        }
//    }
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
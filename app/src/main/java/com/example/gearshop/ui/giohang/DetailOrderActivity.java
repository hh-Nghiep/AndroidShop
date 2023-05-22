package com.example.gearshop.ui.giohang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.cart.CustomerAddress;
import com.example.gearshop.ui.orderHistory.CustomCartArrayAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DetailOrderActivity extends AppCompatActivity {
    Button btnDatHang;
    Toolbar toolbar;
    TextView orderNameValueTxt, orderPhoneValueTxt, detailAddressTxt, orderDetailTotalPriceTxt;
    static ArrayList<CartItem> cartItemsList;
    CustomCartArrayAdapter cartArrayAdapter;
    ListView lv;
    ImageButton addressBtn;
    static Connection connection;
    static CustomerAddress customerAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        orderNameValueTxt = findViewById(R.id.orderNameValueTxt);
        orderPhoneValueTxt = findViewById(R.id.orderPhoneValueTxt);
        detailAddressTxt = findViewById(R.id.detailAddressTxt);
        orderDetailTotalPriceTxt = findViewById(R.id.orderDetailTotalPriceTxt);

        orderNameValueTxt.setText(customerAddress.getName());
        orderPhoneValueTxt.setText(customerAddress.getPhoneNumber());
        detailAddressTxt.setText(customerAddress.getAddress());

        int total = 0;
        for (int i = 0; i< cartItemsList.size(); i++) {
            total += cartItemsList.get(i).getTotalPrice();
        }

        orderDetailTotalPriceTxt.setText(String.format("%,d",total));
        lv = findViewById(R.id.orderDetailLv);

        cartArrayAdapter = new CustomCartArrayAdapter(DetailOrderActivity.this, cartItemsList, "order");


        lv.setAdapter(cartArrayAdapter);

    }

    private void setEvent() {

    }

    public static void xemChiTietDonHang (int maDH) {
        customerAddress = new CustomerAddress();
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select TenNguoiNhan, DiaChi, SoDienThoai from DonHang where MaDH='"+ maDH +"'";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    customerAddress.setName(rs.getString(1));
                    customerAddress.setAddress(rs.getString(2));
                    customerAddress.setPhoneNumber(rs.getString(3));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }

        cartItemsList = new ArrayList<>();
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select sp.HinhAnh1, sp.TenSP, sp.GiaSP, ct.TongTien, ct.SoLuongSP from ChiTietDH ct left join SanPham sp on sp.MaSP = ct.MaSP where MaDH='"+ maDH +"'";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    cartItemsList.add(new CartItem(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), maDH));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }
}
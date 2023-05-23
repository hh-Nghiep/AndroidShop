package com.example.gearshop.ui.giohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartArrayAdapter;
import com.example.gearshop.ui.cart.CartItem;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.login_register.ui.login.InfoUser;
import com.example.gearshop.ui.orderHistory.CustomCartArrayAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import android.util.Log;
import android.widget.Toast;

public class OrderListActivity extends AppCompatActivity {
    Button btnDatHang;
    Toolbar toolbar;
    static TextView totalPriceNoShipTxt;
    static TextView shipPriceTxt;
    static TextView totalPriceTxt;
    TextView addressTxt;
    ArrayList<CartItem> cartItemsList;
    CustomCartArrayAdapter cartArrayAdapter;
    ListView lv;
    ImageButton addressBtn;
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taoDonHang();
                Intent intent = new Intent(OrderListActivity.this, OrderHistoryActivity.class);
                OrderListActivity.this.startActivity(intent);
            }
        });
        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderListActivity.this, AddressActivity.class);
                OrderListActivity.this.startActivity(intent);
            }
        });
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

    private void setControl() {
        btnDatHang = findViewById(R.id.btnDatHang);
        addressBtn = findViewById(R.id.addressBtn);
        addressTxt = findViewById(R.id.addressTxt);

        totalPriceNoShipTxt = findViewById(R.id.totalPriceNoShipTxt);
        shipPriceTxt= findViewById(R.id.shipPriceTxt);
        totalPriceTxt = findViewById(R.id.orderHistoryTotalPriceTxt);

        tongTienThanhToan(CartOfUser.globalCart);

        lv = findViewById(R.id.orderLv);



            addressTxt.setText(CartOfUser.customerAddress.getAddress());




        cartArrayAdapter = new CustomCartArrayAdapter(OrderListActivity.this,  CartOfUser.globalCart, "order");


        lv.setAdapter(cartArrayAdapter);
    }
    private void taoDonHang () {
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                long millis=System.currentTimeMillis();
                String sql ="INSERT INTO DonHang values (?,?,?,?,?,?)";
                try {
                    java.sql.Date date = new java.sql.Date(millis);
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, InfoUser.id_user);
                    ps.setDate(2, date);
                    ps.setInt(3, 0);
                    ps.setString(4, CartOfUser.customerAddress.getName());
                    ps.setString(5, CartOfUser.customerAddress.getAddress());
                    ps.setString(6, CartOfUser.customerAddress.getPhoneNumber());
                    ps.executeUpdate();
                    ps.close();
                    connection.close();
                } catch (SQLException ex) {
                    Log.d("err", ex.getMessage());
                    Toast.makeText( getApplicationContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception ex){
            Log.d("err", ex.getMessage());
        }

        Integer maDH = 0;
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
//                String query = "select max(maDH) from DonHang where maTK = '" + InfoUser.id_user + "'";
                String query = "select max(maDH) from DonHang where maTK = '" + InfoUser.id_user + "'";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    maDH = rs.getInt(1);
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        for( Integer i = 0 ; i < CartOfUser.globalCart.size() ; i++){
            try {
                ConnectSQL con = new ConnectSQL();
                connection = con.CONN();
                if(connection != null){
                    long millis=System.currentTimeMillis();
                    String sql ="INSERT INTO ChiTietDH values (?,?,?,?)";
                    try {
                        java.sql.Date date = new java.sql.Date(millis);
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setInt(1, maDH);
                        ps.setInt(2, CartOfUser.globalCart.get(i).getId());
                        ps.setInt(3, CartOfUser.globalCart.get(i).getAmout());
                        ps.setInt(4, CartOfUser.globalCart.get(i).getTotalPrice());
                        ps.executeUpdate();
                        ps.close();
                        connection.close();

                        Toast.makeText( getApplicationContext(), "Đặt hàng thành công!",Toast.LENGTH_LONG).show();
                    } catch (SQLException ex) {
                        Log.d("err", ex.getMessage());
                    }
                }
            }catch (Exception ex){
                Log.d("err", ex.getMessage());
            }
        }
        CartOfUser.globalCart.clear();

    }
    public static void tongTienThanhToan (ArrayList<CartItem> cartItemsList) {
        int total = 0;
        for (int i = 0; i < cartItemsList.size(); i++) {
            total+= (cartItemsList.get(i).getInitPrice() * cartItemsList.get(i).getAmout());
        }
        totalPriceNoShipTxt.setText(String.format("%,d",total));
        shipPriceTxt.setText("25,000");
        totalPriceTxt.setText((String.format("%,d", total + 25000)));
    }
}
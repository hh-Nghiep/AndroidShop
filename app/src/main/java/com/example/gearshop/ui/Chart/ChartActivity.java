package com.example.gearshop.ui.Chart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.Quanli.ItemList;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ChartActivity extends AppCompatActivity {

    Connection connection;

//    ArrayList<DonHang> donHang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


//        khoitao();


        findViewById(R.id.btnYearChartBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChartYeahActivity.class));
            }
        });

        findViewById(R.id.btnMonthChartBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChartMonthActivity.class));
            }
        });
    }

//    public void khoitao(){
//        try {
//            ConnectSQL con = new ConnectSQL();
//            connection = con.CONN();
//            if(connection != null){
//                String query = "select * from DonHang";
//                Statement statement = connection.createStatement();
//                ResultSet rs = statement.executeQuery(query);
//                while (rs.next()){
//                    donHang.add(new DonHang(rs.getInt(1), rs.getInt(2),rs.getDate(3), rs.getInt(4)));
//                }
//            }
//        }catch (Exception ex){
//            System.err.print(ex.getMessage());
//        }
//    }
}
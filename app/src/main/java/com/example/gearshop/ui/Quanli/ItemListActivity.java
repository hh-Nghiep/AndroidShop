package com.example.gearshop.ui.Quanli;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.example.gearshop.ui.model.SanPham;
import com.squareup.picasso.Picasso;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class ItemListActivity extends AppCompatActivity {

    Connection connection;
    ListView listView;

    SearchView searchView;

    ArrayAdapter adapter;
//    ArrayList<SanPham> dataSP = new ArrayList<>();

    ArrayList<ItemList> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sp);

        listView = findViewById(R.id.lvDanhSachSP);


//        arrayList.add(new ItemList(R.drawable.image1, "Chuột Logitech G102" ,1,1,1,350000));
//        arrayList.add(new ItemList(R.drawable.image2, "Chuột Logitech G403" ,2,1,3,500000));
//        arrayList.add(new ItemList(R.drawable.image3, "Chuột Logitech G502" ,3,1,4,700000));
//        arrayList.add(new ItemList(R.drawable.image4, "Bàn Phím Akko 3087_rf" ,4,2,4,1200000));
//        arrayList.add(new ItemList(R.drawable.image5, "Bàn Phím Akko 3089B Multi" ,5,2,4,1500000));
//        arrayList.add(new ItemList(R.drawable.image6, "Bàn Phím Ducky Mini Day Break" ,6,2,4,1800000));
//        arrayList.add(new ItemList(R.drawable.image7, "Bàn Phím iKBC CD87HR" ,7,2,4,1250000));
//        arrayList.add(new ItemList(R.drawable.image8, "Chuột Razer Basilick" ,8,1,4,750000));
//        arrayList.add(new ItemList(R.drawable.image9, "Chuột Corsair M65" ,9,1,4,450000));

        KhoiTao();

        //Item adapter
        ItemListAdapter itemListAdapter = new ItemListAdapter(this, R.layout.list_row,arrayList);

        listView.setAdapter(itemListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected item from the list
                ItemList selectedItem = (ItemList) adapterView.getItemAtPosition(i);

                // Pass the data of the selected item to the UpdateActivity
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                intent.putExtra("MaSP", selectedItem.getMaSP().toString());
                intent.putExtra("TenSP", selectedItem.getName());
                intent.putExtra("MaTL", selectedItem.getMaTL().toString());
                intent.putExtra("MaTH", selectedItem.getMaTH().toString());
                intent.putExtra("Price", selectedItem.getPrice().toString());
                intent.putExtra("Desc", selectedItem.getMieuTaSP());
                intent.putExtra("Hinh1", selectedItem.getHinh1());
                intent.putExtra("Hinh2", selectedItem.getHinh2());
                intent.putExtra("Hinh3", selectedItem.getHinh3());

                // Start the UpdateActivity
                startActivity(intent);
            }
        });

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemListActivity.this, Them_SP.class));
            }
        });

//        findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ItemListActivity.this, UpdateActivity.class));
//            }
//        });
    }

    private void KhoiTao(){
        try {
            ConnectSQL con = new ConnectSQL();
            connection = con.CONN();
            if(connection != null){
                String query = "select * from SanPham";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    arrayList.add(new ItemList(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                }
            }
        }catch (Exception ex){
            System.err.print(ex.getMessage());
        }
    }
}
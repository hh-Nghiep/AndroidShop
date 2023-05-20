package com.example.gearshop.ui.Quanli;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gearshop.ConnectSQL;
import com.example.gearshop.R;
import com.squareup.picasso.Picasso;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    Connection connection;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sp);

        listView = findViewById(R.id.lvDanhSachSP);


//        if(sp.getHinhAnh1().length() > 32){
//            Picasso.get()
//                    .load("https://drive.google.com/uc?id=" + sp.getHinhAnh1().substring(32,sp.getHinhAnh1().lastIndexOf('/')))
//                    .into(holder.ivSanPham);
//        }else{
//            holder.ivSanPham.setImageResource(R.drawable.baseline_help_center_24);
//        }
//      khoi tao du lieu

        ArrayList<ItemList> arrayList = new ArrayList<>();

        arrayList.add(new ItemList(R.drawable.image1, "Chuột Logitech G102" ,1,1,1,350000));
        arrayList.add(new ItemList(R.drawable.image2, "Chuột Logitech G403" ,2,1,3,500000));
        arrayList.add(new ItemList(R.drawable.image3, "Chuột Logitech G502" ,3,1,4,700000));
        arrayList.add(new ItemList(R.drawable.image4, "Bàn Phím Akko 3087_rf" ,4,2,4,1200000));
        arrayList.add(new ItemList(R.drawable.image5, "Bàn Phím Akko 3089B Multi" ,5,2,4,1500000));
        arrayList.add(new ItemList(R.drawable.image6, "Bàn Phím Ducky Mini Day Break" ,6,2,4,1800000));
        arrayList.add(new ItemList(R.drawable.image7, "Bàn Phím iKBC CD87HR" ,7,2,4,1250000));
        arrayList.add(new ItemList(R.drawable.image8, "Chuột Razer Basilick" ,8,1,4,750000));
        arrayList.add(new ItemList(R.drawable.image9, "Chuột Corsair M65" ,9,1,4,450000));


        //Item adapter
        ItemListAdapter itemListAdapter = new ItemListAdapter(this, R.layout.list_row,arrayList);

        listView.setAdapter(itemListAdapter);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Them_SP.class));
            }
        });

        findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UpdateActivity.class));
            }
        });

    }
}
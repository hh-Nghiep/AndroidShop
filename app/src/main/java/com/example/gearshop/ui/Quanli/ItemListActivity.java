package com.example.gearshop.ui.Quanli;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gearshop.R;


import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sp);

        listView = findViewById(R.id.lvDanhSachSP);

//      khoi tao du lieu
        ArrayList<ItemList> arrayList = new ArrayList<>();

        arrayList.add(new ItemList(R.drawable.image1, "Logitech G102" ,199000));
        arrayList.add(new ItemList(R.drawable.image2, "Logitech Keyboard" ,249000));

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
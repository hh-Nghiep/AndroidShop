package com.example.gearshop.ui.giohang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gearshop.R;
import com.example.gearshop.ui.cart.CartOfUser;
import com.example.gearshop.ui.cart.CustomerAddress;

public class AddressActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText name, phoneNumber, address;
    CustomerAddress customerAddress;
    Button saveAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl () {
        name = findViewById(R.id.customerName);
        phoneNumber = findViewById(R.id.customerPhoneNum);
        address = findViewById(R.id.customerAddress);
        saveAddress = findViewById(R.id.saveAddress);

    }

    private void setEvent () {
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartOfUser.customerAddress = new CustomerAddress(name.getText().toString(), phoneNumber.getText().toString(), address.getText().toString());
                Intent intent = new Intent(AddressActivity.this, OrderListActivity.class);
                AddressActivity.this.startActivity(intent);
            }
        });
    }
}
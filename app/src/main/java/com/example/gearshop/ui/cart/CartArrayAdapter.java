package com.example.gearshop.ui.cart;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gearshop.MainActivity;
import com.example.gearshop.R;
import com.example.gearshop.ui.giohang.CartListActivity;
import com.example.gearshop.ui.giohang.OrderListActivity;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class CartArrayAdapter extends ArrayAdapter<CartItem> {
    Activity context;
    int IdLayout;
    ArrayList<CartItem> cartItemsList;
    TextView numberItemTxt;
    ImageButton minusCartBtn, plusCartBtn;

    public CartArrayAdapter( Activity context, int idLayout, ArrayList<CartItem> cartItemsList) {
        super(context, idLayout, cartItemsList);
        this.context = context;
        IdLayout = idLayout;
        this.cartItemsList = cartItemsList;
    }
    // hien thi du lieu

    private void setEvent() {


//        minusCartBtn.setOnClickListener(new AdapterView<>().OnClickListener() {
//
//            @Override
//            public void onI {
//                int number = Integer.parseInt(numberItemTxt.getText().toString());
//                if(number > 0) {
//                    number -= 1;
//                }
//                numberItemTxt.setText(String.valueOf(number));
//                cartArrayAdapter.notifyDataSetChanged();
//            }
//        });
//
//        plusCartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CartListActivity.this, OrderListActivity.class);
//                CartListActivity.this.startActivity(intent);
//            }
//        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("call here");




        LayoutInflater myFlater = context.getLayoutInflater();

        convertView = myFlater.inflate(IdLayout, null);
        CartItem item = cartItemsList.get(position);
        ImageView cartImg = convertView.findViewById(R.id.picCart);

        minusCartBtn = convertView.findViewById(R.id.minusCartBtn);
        plusCartBtn = convertView.findViewById(R.id.plusCartBtn);
        numberItemTxt = convertView.findViewById(R.id.numberItemTxt);

//        plusCartBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int number = CartListActivity.cartItemsList.get(position).getAmout() + 1;
//                   CartListActivity.cartItemsList.get(position).setAmout(number);
//                   numberItemTxt.setText(String.valueOf(number));
//
////              numberItemTxt.setText(String.valueOf(number));
////                CartArrayAdapter.notifyDataSetChanged();
////          }
//            }
//        });
        if(item.getImg().length() > 32){
            Picasso.get()
                    .load("https://drive.google.com/uc?id=" + item.getImg().substring(32,item.getImg().lastIndexOf('/')))
                    .into(cartImg);
        }else{
            cartImg.setImageResource(R.drawable.baseline_help_center_24);
        }

//        cartImg.setImageResource(item.getImg());

//        Picasso.get()
//                .load("https://drive.google.com/uc?id=" + item.getImg().substring(32,item.getImg().lastIndexOf('/')))
//                .into(cartImg);

        TextView cartName = convertView.findViewById(R.id.cartItemTitleTxt);
        cartName.setText(item.getName());

        TextView initPriceItem = convertView.findViewById(R.id.priceEachItem);
        initPriceItem.setText((String.format("%,d",item.getInitPrice())));

        TextView totalPriceItem = convertView.findViewById(R.id.priceTotalItem);
        totalPriceItem.setText(String.format("%,d",item.getTotalPrice()));

        TextView amout = convertView.findViewById(R.id.numberItemTxt);
        amout.setText(String.format("%,d",item.getAmout()));

        return convertView;
    }
}

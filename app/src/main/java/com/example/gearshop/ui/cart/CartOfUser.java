package com.example.gearshop.ui.cart;

import android.app.Application;

import com.example.gearshop.ui.model.SanPham;

import java.util.ArrayList;

public class CartOfUser {
    public static ArrayList<SanPham> CartUser = new ArrayList<>();
    public static ArrayList<CartItem> globalCart = new ArrayList<>();
    public static CustomerAddress customerAddress;
}

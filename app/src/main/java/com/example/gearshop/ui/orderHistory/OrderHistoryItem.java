package com.example.gearshop.ui.orderHistory;

import com.example.gearshop.ui.cart.CartItem;

import java.util.ArrayList;

public class OrderHistoryItem {
    private String hoTen, diaChi, sdt, email;
    ArrayList <CartItem> dsSanPham;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<CartItem> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(ArrayList<CartItem> dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public OrderHistoryItem() {
    }

    public OrderHistoryItem(String hoTen, String diaChi, String sdt, String email, ArrayList<CartItem> dsSanPham) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.dsSanPham = dsSanPham;
    }
}

package com.example.gearshop.ui.orderHistory;

import com.example.gearshop.ui.cart.CartItem;

import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryItem {
    private String hoTen, diaChi, sdt, email;
    Date NgayTao;
    int maDH, TrangThai;
    ArrayList <CartItem> dsSanPham;

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

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

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date ngayTao) {
        NgayTao = ngayTao;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public OrderHistoryItem() {
    }

    public OrderHistoryItem(int maDH, String hoTen, String diaChi, String sdt, Date ngayTao, int trangThai) {
        this.maDH = maDH;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        NgayTao = ngayTao;
        TrangThai = trangThai;
    }

    public OrderHistoryItem(String hoTen, String diaChi, String sdt, String email, ArrayList<CartItem> dsSanPham) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.dsSanPham = dsSanPham;
    }
}

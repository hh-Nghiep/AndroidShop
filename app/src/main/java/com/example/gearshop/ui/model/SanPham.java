package com.example.gearshop.ui.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    String tenSP, mieuTaSP, hinhAnh1, hinhAnh2, hinhAnh3, maTH;
    Integer maSP, maTL, soLuong, giaSP;

    @Override
    public String toString() {
        return "SanPham{" +
                "tenSP='" + tenSP + '\'' +
                ", mieuTaSP='" + mieuTaSP + '\'' +
                ", hinhAnh1='" + hinhAnh1 + '\'' +
                ", hinhAnh2='" + hinhAnh2 + '\'' +
                ", hinhAnh3='" + hinhAnh3 + '\'' +
                ", maSP=" + maSP +
                ", maTL=" + maTL +
                ", maTH=" + maTH +
                ", giaSP=" + giaSP +
                ", soLuong=" + soLuong +
                '}';
    }

    public SanPham(Integer maSP, String tenSP, Integer giaSP, Integer maTL, String maTH, String mieuTaSP, String hinhAnh1, String hinhAnh2, String hinhAnh3, Integer soLuong) {
        this.tenSP = tenSP;
        this.mieuTaSP = mieuTaSP;
        this.hinhAnh1 = hinhAnh1;
        this.hinhAnh2 = hinhAnh2;
        this.hinhAnh3 = hinhAnh3;
        this.maSP = maSP;
        this.maTL = maTL;
        this.maTH = maTH;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
    }

    public SanPham() {

    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMieuTaSP() {
        return mieuTaSP;
    }

    public void setMieuTaSP(String mieuTaSP) {
        this.mieuTaSP = mieuTaSP;
    }

    public String getHinhAnh1() {
        return hinhAnh1;
    }

    public void setHinhAnh1(String hinhAnh1) {
        this.hinhAnh1 = hinhAnh1;
    }

    public String getHinhAnh2() {
        return hinhAnh2;
    }

    public void setHinhAnh2(String hinhAnh2) {
        this.hinhAnh2 = hinhAnh2;
    }

    public String getHinhAnh3() {
        return hinhAnh3;
    }

    public void setHinhAnh3(String hinhAnh3) {
        this.hinhAnh3 = hinhAnh3;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public Integer getMaTL() {
        return maTL;
    }

    public void setMaTL(Integer maTL) {
        this.maTL = maTL;
    }

    public String getMaTH() {
        return maTH;
    }

    public void setMaTH(String maTH) {
        this.maTH = maTH;
    }

    public Integer getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(Integer giaSP) {
        this.giaSP = giaSP;
    }

    public void setSoLuong(Integer soLuong){this.soLuong = soLuong;}

    public Integer getSoLuong(){return soLuong;}
}

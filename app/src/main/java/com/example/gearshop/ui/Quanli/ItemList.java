package com.example.gearshop.ui.Quanli;


public class ItemList {
    int image;
    String name, mieuTaSP, hinh1, hinh2, hinh3;

    Integer maSP, maTL, maTH;
    int price;

    public ItemList(int image, String name, Integer maSP, Integer maTL, Integer maTH, int price) {
        this.image = image;
        this.name = name;
        this.maSP = maSP;
        this.maTH = maTL;
        this.maTH = maTH;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", mieuTaSP='" + mieuTaSP + '\'' +
                ", hinh1='" + hinh1 + '\'' +
                ", hinh2='" + hinh2 + '\'' +
                ", hinh3='" + hinh3 + '\'' +
                ", maSP=" + maSP +
                ", maTL=" + maTL +
                ", maTH=" + maTH +
                ", price=" + price +
                '}';
    }

    public ItemList(int image, String name, String mieuTaSP, String hinh1, String hinh2, String hinh3, Integer maSP, Integer maTL, Integer maTH, int price) {
        this.image = image;
        this.name = name;
        this.mieuTaSP = mieuTaSP;
        this.hinh1 = hinh1;
        this.hinh2 = hinh2;
        this.hinh3 = hinh3;
        this.maSP = maSP;
        this.maTL = maTL;
        this.maTH = maTH;
        this.price = price;
    }

    public String getMieuTaSP() {
        return mieuTaSP;
    }

    public void setMieuTaSP(String mieuTaSP) {
        this.mieuTaSP = mieuTaSP;
    }

    public String getHinh1() {
        return hinh1;
    }

    public void setHinh1(String hinh1) {
        this.hinh1 = hinh1;
    }

    public String getHinh2() {
        return hinh2;
    }

    public void setHinh2(String hinh2) {
        this.hinh2 = hinh2;
    }

    public String getHinh3() {
        return hinh3;
    }

    public void setHinh3(String hinh3) {
        this.hinh3 = hinh3;
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

    public Integer getMaTH() {
        return maTH;
    }

    public void setMaTH(Integer maTH) {
        this.maTH = maTH;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

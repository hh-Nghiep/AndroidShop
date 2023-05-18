package com.example.gearshop.ui.cart;

public class CartItem {
    private String img;
    private String name;
    private int initPrice;
    private int totalPrice;
    private int amout;

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public int getInitPrice() {
        return initPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getAmout() {
        return amout;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitPrice(int initPrice) {
        this.initPrice = initPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    public CartItem() {
    }

    public CartItem(String img, String name, int initPrice, int totalPrice, int amout) {
        this.img = img;
        this.name = name;
        this.initPrice = initPrice;
        this.totalPrice = totalPrice;
        this.amout = amout;
    }
}

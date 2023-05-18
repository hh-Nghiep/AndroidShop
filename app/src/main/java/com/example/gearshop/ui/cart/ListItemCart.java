package com.example.gearshop.ui.cart;

import java.util.ArrayList;

public class ListItemCart {
    private ArrayList<CartItem> cartItemsList = new ArrayList<>();

    public boolean IsEmpty(){
        if(this.cartItemsList.isEmpty()){
            return true;
        }
        return false;
    }
    public ArrayList<CartItem> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(ArrayList<CartItem> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }
}

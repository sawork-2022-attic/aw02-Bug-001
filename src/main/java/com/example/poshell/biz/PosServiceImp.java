package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public double checkout(Cart cart) {
        if (cart == null) return -1;
        double payment = this.total(cart);
        cart.clear();
        return payment;
    }

    @Override
    public double total(Cart cart) {
        if (cart == null) return -1;
        return cart.total();
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {
        if (this.getCart() == null) return false;

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        return this.getCart().addItem(new Item(product, amount));
    }

    @Override
    public boolean del(int index) {
        if (this.getCart() == null) return false;

        return this.getCart().delItem(index);
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}

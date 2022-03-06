package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "list")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "new")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "add")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Show Cart Information", key = "show")
    public String showCart() {
        Cart cart = posService.getCart();
        if (cart == null) {
            return "No available cart";
        }
        return cart.toString();
    }

    @ShellMethod(value = "Clear Cart", key = "empty")
    public String clearCart() {
        posService.newCart();
        return "Cart cleared";
    }

    @ShellMethod(value = "Delete Item from cart, 0-indexed", key = "del")
    public String deleteItem(int i) {
        if (posService.del(i)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }
}

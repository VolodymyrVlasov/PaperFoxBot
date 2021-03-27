package com.paperfox.ua.models.shop;

import com.paperfox.ua.models.products.PrintingProduct;
import com.paperfox.ua.models.customer.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingCart {
    private Customer customer;
    private String orderId;
    private List<PrintingProduct> items;

    public ShoppingCart(Customer customer) {
        orderId = "PF-" + String.valueOf(new Random().nextInt(1000) + 99);
        this.customer = customer;
        items = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    // get last added item (PrintingProduct) to add or change data
    public PrintingProduct getLastItem() {
        return items.get(items.size() - 1);
    }

    // add new item to order cart
    public void addItem(PrintingProduct newItem) {
        items.add(newItem);
    }

    // get total price for this order
    public Double getOrderPrice() {
        double orderPrice = 0;
        for (PrintingProduct e : items) {
            orderPrice += e.getPrice();
        }
        return orderPrice;
    }

    public int getItemsQuantity() {
        return items.size();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Items in cart:\t" + items.size() + " item<br><br>\n");
        int i = 1;
        for (PrintingProduct e : items) {
            stringBuilder.append(i + ". " + e.toString() + "<br><br>\n");
            i++;
        }
        return stringBuilder.toString();
    }

    public String getOrderPath() {
        return  items.get(items.size() - 1).getOrderPath() ;
    }
}

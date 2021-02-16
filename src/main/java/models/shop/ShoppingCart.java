package models.shop;

import models.products.PrintingProduct;
import models.users.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoppingCart {
    private Customer customer;
    private String orderId;
    private List<PrintingProduct> items;

    public ShoppingCart(Customer customer) {
        orderId = "PF-" + new Random(1000)+99;
        this.customer = customer;
        items = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    // get last added item (PrintingProduct) to add or change data
    public PrintingProduct getLastItem() {
        return items.get(items.size()-1);
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
        stringBuilder.append("-> ORDER CART <-\n    " +
                customer.toString() + "\n   " +
                " Items in cart -> " + items.size() + " item\n");
        int i = 1;
        for (PrintingProduct e : items) {
            stringBuilder.append(i + ". " + e.toString()+ "\n");
            i++;
        }
        return stringBuilder.toString();
    }
}

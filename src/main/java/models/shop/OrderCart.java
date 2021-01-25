package models.shop;

import models.products.PrintingProduct;
import org.glassfish.jersey.internal.util.Producer;

import java.util.ArrayList;
import java.util.List;

public class OrderCart {
    private Customer customer;
    private String orderId;
    private List<PrintingProduct> items;

    public OrderCart(Customer customer) {
        this.customer = customer;
        items = new ArrayList<>();
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
        return "OrderCart{" +
                "customer=" + customer.toString() +
                ", items=" + items +
                '}';
    }
}

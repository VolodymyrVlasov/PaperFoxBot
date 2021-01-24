package models.shop;

import models.products.PrintingProduct;
import org.glassfish.jersey.internal.util.Producer;

public class OrderCart {
    private PrintingProduct item;
    private Double orderPrice;
    private int itemQuantity;
    private Customer customer;
    private String orderId;

    public OrderCart(Customer customer){
        this.customer = customer;

    }

    public PrintingProduct getItem() {
        return item;
    }

    public void setItem(PrintingProduct item) {
        this.item = item;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

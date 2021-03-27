package com.ua.paperfox.models.shop;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private static OrderList instance;
    private List<Order> orderList;

    private OrderList() {
        orderList = new ArrayList<>();
    }

    public static OrderList getInstance() {
        if (instance == null) instance = new OrderList();
        return instance;
    }

    public void addOrder(Order newOrder) {
        orderList.add(newOrder);
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}

package org.example.data;

import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static OrderRepository instance = null;
    private List<Order> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
        int newQuantity = order.getProduct().getQuantity() - order.getQuantity();
        order.getProduct().setQuantity(newQuantity);
    }
}

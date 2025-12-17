package org.example;

import java.util.List;

public class OrderService {

    private int nextOrderId = 1;

    public Order createOrder(Customer customer, List<Product> products) {
        Order order = new Order(nextOrderId++, customer);
        for (Product p : products) {
            order.addProduct(p);
        }
        customer.addOrder(order);
        return order;
    }
}
package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<Product> products = new ArrayList<>();

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }


    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }


    public void printSummary() {
        System.out.println("Order ID: " + id);
        System.out.println("Kund: " + customer.getName());
        System.out.println("Produkter:");

        for (Product p : products) {
            System.out.println(" - " + p.getName() + " (" + p.getPrice() + " kr)");
        }

        System.out.println("Totalt pris: " + getTotalPrice() + " kr");
    }
}

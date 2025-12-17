package org.example;

import javax.swing.*;
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

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProduct(int productId) {
        for (Product p : products) {
            if (p.getId() == productId) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printSummary() {
        System.out.println("Order ID: " + id);
        System.out.println("Kund: " + customer.getName());
        System.out.println("Produkter:");
        for (Product p : products) {
            System.out.println("  - " + p.getName() + " (" + p.getPrice() + " kr)");
        }
        System.out.println("Totalt: " + getTotalPrice() + " kr");
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n");
        sb.append("Kund: ").append(customer.getName()).append("\n");
        sb.append("Produkter:\n");
        for (Product p : products) {
            sb.append("  - ").append(p.getId()).append(": ").append(p.getName())
                    .append(" (").append(p.getPrice()).append(" kr)\n");
        }
        sb.append("Totalt: ").append(getTotalPrice()).append(" kr\n");
        return sb.toString();
    }

    public void printSummaryTo(JTextArea area) {
        area.setText(toText());
    }

}
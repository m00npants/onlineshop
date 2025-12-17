package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }


    public void printAll() {
        System.out.println("Tillgängliga produkter:");
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    public List<Product> getAll() {
        return products;
    }

}
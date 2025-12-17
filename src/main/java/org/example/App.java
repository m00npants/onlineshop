package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private List<Customer> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private List<Order> orders = new ArrayList<>();

    public void run() {

        customers.add(new Customer(1, "Andreas", "Andreas@gmail.com"));
        customers.add(new Customer(2, "Stefan", "Stefan@gmail.com"));
        customers.add(new Customer(3, "Maria", "maria@gmail.com"));

        System.out.println("Välkommen till denna lilla shop!");

        boolean running = true;

        while (running) {

            System.out.println("\n--- MENY ---");
            System.out.println("1. Välj kund och se order");
            System.out.println("2. Avsluta");
            System.out.print("Val: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    Customer selectedCustomer = chooseCustomer();

                    Order order = new Order(orders.size() + 1, selectedCustomer);

                    if (selectedCustomer.getId() == 1) {
                        order.addProduct(new Product(1, "Nocco Caribbean", 19.90));
                        order.addProduct(new Product(2, "Nocco Skumtome ", 19.90));
                        order.addProduct(new Product(3, "Nocco Miami", 29.00));
                        order.addProduct(new Product(4, "Nocco Passion", 19.90));
                        order.addProduct(new Product(5, "Nocco Julmust", 19.90));
                        order.addProduct(new Product(6, "Nocco Ice Soda", 29.00));
                        order.addProduct(new Product(7, "Nocco Carnival", 19.90));
                        order.addProduct(new Product(8, "Nocco Del Sol", 19.90));
                        order.addProduct(new Product(9, "Nocco Melon", 39.00));
                        order.addProduct(new Product(10, "Nocco Focus", 39.00));
                    }
                    else if (selectedCustomer.getId() == 2) {
                        order.addProduct(new Product(1, "Celsius Peach Vibe", 29.90));
                        order.addProduct(new Product(2, "Celsius Raspberry ", 49.90));
                        order.addProduct(new Product(3, "Celsius Wild Berry", 39.00));
                        order.addProduct(new Product(4, "Celsius Kiwi", 25.90));
                        order.addProduct(new Product(5, "Celsius Orange", 39.90));
                        order.addProduct(new Product(6, "Celsius Grape", 28.00));
                        order.addProduct(new Product(7, "Celsius Mango", 25.90));
                        order.addProduct(new Product(8, "Celsius Strawberry", 29.90));
                        order.addProduct(new Product(9, "Celsius Citrus", 26.50));
                        order.addProduct(new Product(10, "Celsius Artic Vibe", 25.50));
                    }
                    else if (selectedCustomer.getId() == 3) {
                        order.addProduct(new Product(1, "Cola", 19.90));
                        order.addProduct(new Product(2, "Fanta", 19.90));
                        order.addProduct(new Product(3, "Sprite", 25.50));
                        order.addProduct(new Product(4, "Julmust", 19.90));
                        order.addProduct(new Product(5, "Kaffe", 32.90));
                        order.addProduct(new Product(6, "Latte", 49.00));
                        order.addProduct(new Product(7, "Choklad", 39.90));
                        order.addProduct(new Product(8, "Solkräm", 69.90));
                        order.addProduct(new Product(9, "General lös", 65.00));
                        order.addProduct(new Product(10, "General portion", 59.00));
                    }

                    orders.add(order);
                    order.printSummary();
                    break;

                case 2:
                    System.out.println("Avslutar programmet...");
                    running = false;
                    break;

                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private Customer chooseCustomer() {
        System.out.println("Välj kund:");

        for (Customer customer : customers) {
            System.out.println(customer.getId() + ": " + customer.getName());
        }

        System.out.print("Kundens ID: ");
        int id = scanner.nextInt();

        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }

        System.out.println("Kunden fanns inte, väljer första kund automatiskt.");
        return customers.get(0);
    }
}
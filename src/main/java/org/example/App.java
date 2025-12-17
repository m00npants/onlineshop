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
                        order.addProduct(new Product(2, "Celsius Cola", 19.90));
                        order.addProduct(new Product(3, "Gaming Headset", 799.00));
                    }
                    else if (selectedCustomer.getId() == 2) {
                        order.addProduct(new Product(4, "Penna", 9.90));
                        order.addProduct(new Product(5, "Anteckningsblock", 29.90));
                        order.addProduct(new Product(6, "Kontorsstol", 1299.00));
                    }
                    else if (selectedCustomer.getId() == 3) {
                        order.addProduct(new Product(7, "Handväska", 499.00));
                        order.addProduct(new Product(8, "Solglasögon", 199.00));
                        order.addProduct(new Product(9, "General start lös", 65.00));
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
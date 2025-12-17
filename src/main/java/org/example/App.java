package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private ProductCatalog catalog = new ProductCatalog();
    private OrderService orderService = new OrderService();
    private Scanner scanner = new Scanner(System.in);

    public void run() {

        seedCustomers();
        seedProducts();

        System.out.println("Välkommen till denna lilla shop!");

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENY ---");
            System.out.println("1. Visa kunder");
            System.out.println("2. Visa produktkatalog");
            System.out.println("3. Skapa order för kund");
            System.out.println("4. Visa orders för kund");
            System.out.println("5. Avsluta");
            System.out.print("Val: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    printCustomers();
                    break;
                case 2:
                    catalog.printAll();
                    break;
                case 3:
                    handleCreateOrder();
                    break;
                case 4:
                    handleShowCustomerOrders();
                    break;
                case 5:
                    System.out.println("Avslutar programmet...");
                    running = false;
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private void seedCustomers() {
        customers.add(new Customer(1, "Andreas", "andreas@gmail.com"));
        customers.add(new Customer(2, "Stefan", "stefan@gmail.com"));
        customers.add(new Customer(3, "Maria", "maria@gmail.com"));
    }

    private void seedProducts() {

        catalog.addProduct(new Product(1, "Nocco Caribbean", 19.90, "Nocco"));
        catalog.addProduct(new Product(2, "Nocco Miami Strawberry", 19.90, "Nocco"));
        catalog.addProduct(new Product(3, "Nocco Limón Del Sol", 19.90, "Nocco"));
        catalog.addProduct(new Product(4, "Nocco Blood Orange", 19.90, "Nocco"));
        catalog.addProduct(new Product(5, "Nocco Passion", 19.90, "Nocco"));
        catalog.addProduct(new Product(6, "Nocco Ice Soda", 19.90, "Nocco"));
        catalog.addProduct(new Product(7, "Nocco Blueberry", 19.90, "Nocco"));
        catalog.addProduct(new Product(8, "Nocco Skum Tomte", 19.90, "Nocco"));
        catalog.addProduct(new Product(9, "Nocco Carnival Exotic", 19.90, "Nocco"));
        catalog.addProduct(new Product(10, "Nocco Focus Melon Crush", 19.90, "Nocco"));


        catalog.addProduct(new Product(11, "Celsius Peach Vibe", 19.90, "Celsius"));
        catalog.addProduct(new Product(12, "Celsius Raspberry Acai", 19.90, "Celsius"));
        catalog.addProduct(new Product(13, "Celsius Wild Berry", 19.90, "Celsius"));
        catalog.addProduct(new Product(14, "Celsius Kiwi Guava", 19.90, "Celsius"));
        catalog.addProduct(new Product(15, "Celsius Watermelon", 19.90, "Celsius"));
        catalog.addProduct(new Product(16, "Celsius Strawberry Lemonade", 19.90, "Celsius"));
        catalog.addProduct(new Product(17, "Celsius Orange", 19.90, "Celsius"));
        catalog.addProduct(new Product(18, "Celsius Grape Rush", 19.90, "Celsius"));
        catalog.addProduct(new Product(19, "Celsius Mango Passionfruit", 19.90, "Celsius"));
        catalog.addProduct(new Product(20, "Celsius Arctic Vibe", 19.90, "Celsius"));
    }

    private void printCustomers() {
        System.out.println("Kunder:");
        for (Customer c : customers) {
            System.out.println(c.toString());
        }
    }

    private Customer chooseCustomer() {
        printCustomers();
        System.out.print("Ange kundens ID: ");
        int id = readInt();

        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }

        System.out.println("Kunden fanns inte, väljer första kund automatiskt.");
        return customers.get(0);
    }

    private void handleCreateOrder() {
        Customer customer = chooseCustomer();

        List<Product> chosenProducts = new ArrayList<>();
        boolean adding = true;

        while (adding) {
            catalog.printAll();
            System.out.print("Ange produkt-ID att lägga till (0 för att avsluta): ");
            int productId = readInt();

            if (productId == 0) {
                adding = false;
            } else {
                Product p = catalog.getById(productId);
                if (p != null) {
                    chosenProducts.add(p);
                    System.out.println(p.getName() + " lades till i ordern.");
                } else {
                    System.out.println("Ingen produkt med det ID:t.");
                }
            }
        }

        if (chosenProducts.isEmpty()) {
            System.out.println("Ingen produkt vald, ingen order skapades.");
            return;
        }

        Order order = orderService.createOrder(customer, chosenProducts);
        orders.add(order);

        System.out.println("\nOrder skapad:");
        order.printSummary();
    }

    private void handleShowCustomerOrders() {
        Customer customer = chooseCustomer();

        List<Order> history = customer.getOrderHistory();
        if (history.isEmpty()) {
            System.out.println("Kunden har inga orders ännu.");
            return;
        }

        System.out.println("Orders för " + customer.getName() + ":");
        for (Order o : history) {
            System.out.println("-----------------------------");
            o.printSummary();
        }
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ange ett heltal: ");
            scanner.next(); // rensa felaktig input
        }
        return scanner.nextInt();
    }
}
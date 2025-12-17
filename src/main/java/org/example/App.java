package org.example;

import javax.swing.*;
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
            System.out.println("5. Ta bort produkt från order");
            System.out.println("6. Lägg till ny kund");
            System.out.println("7. Ta bort kund ");
            System.out.println("0. Avsluta");
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
                    handleRemoveProduct();
                    break;
                case 6:
                    handleAddCustomer();
                    break;
                case 7:
                    handleDeleteCustomer();
                    break;
                case 0:
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

    private void handleRemoveProduct() {
        Customer customer = chooseCustomer();

        if (customer.getOrderHistory().isEmpty()) {
            System.out.println("Kunden har inga orders.");
            return;
        }

        System.out.println("Välj order:");
        for (Order o : customer.getOrderHistory()) {
            System.out.println("Order ID: " + o.getId());
        }

        System.out.print("Ange order-ID: ");
        int orderId = readInt();

        Order selectedOrder = null;
        for (Order o : customer.getOrderHistory()) {
            if (o.getId() == orderId) {
                selectedOrder = o;
                break;
            }
        }

        if (selectedOrder == null) {
            System.out.println("Order hittades inte.");
            return;
        }

        System.out.println("Produkter i ordern:");
        for (Product p : selectedOrder.getProducts()) {
            System.out.println(p.getId() + ": " + p.getName() + " (" + p.getPrice() + " kr)");
        }

        System.out.print("Ange produkt-ID att ta bort: ");
        int productId = readInt();

        boolean removed = selectedOrder.removeProduct(productId);

        if (removed) {
            System.out.println("Produkten togs bort.");
        } else {
            System.out.println("Ingen produkt med det ID:t i ordern.");
        }
    }


    //GUI
    public String getCustomersAsText() {
        StringBuilder sb = new StringBuilder("Kunder:\n");
        for (Customer c : customers) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
    public String getProductsAsText() {
        StringBuilder sb = new StringBuilder("Produkter:\n");
        for (Product p : catalog.getAll()) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
    public void createOrderSwing(JFrame parent, JTextArea output) {
        String idStr = JOptionPane.showInputDialog(parent, "Ange kund-ID:");
        if (idStr == null) return;

        int id = Integer.parseInt(idStr);
        Customer customer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            JOptionPane.showMessageDialog(parent, "Kund hittades inte.");
            return;
        }

        List<Product> chosen = new ArrayList<>();
        boolean adding = true;

        while (adding) {
            String productIdStr = JOptionPane.showInputDialog(parent,
                    getProductsAsText() + "\nAnge produkt-ID (0 för att avsluta):");

            if (productIdStr == null) return;

            int pid = Integer.parseInt(productIdStr);

            if (pid == 0) {
                adding = false;
            } else {
                Product p = catalog.getById(pid);
                if (p != null) {
                    chosen.add(p);
                }
            }
        }

        if (chosen.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Ingen produkt vald.");
            return;
        }

        Order order = orderService.createOrder(customer, chosen);
        orders.add(order);

        output.setText("Order skapad:\n");
        order.printSummaryTo(output);
    }
    public void showOrdersSwing(JFrame parent, JTextArea output) {
        String idStr = JOptionPane.showInputDialog(parent, "Ange kund-ID:");
        if (idStr == null) return;

        int id = Integer.parseInt(idStr);

        Customer customer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            JOptionPane.showMessageDialog(parent, "Kund hittades inte.");
            return;
        }

        StringBuilder sb = new StringBuilder("Orders för " + customer.getName() + ":\n");

        for (Order o : customer.getOrderHistory()) {
            sb.append("-----------------------------\n");
            sb.append(o.toText());
        }

        output.setText(sb.toString());
    }
    public void removeProductSwing(JFrame parent, JTextArea output) {
        String idStr = JOptionPane.showInputDialog(parent, "Ange kund-ID:");
        if (idStr == null) return;

        int id = Integer.parseInt(idStr);

        Customer customer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            JOptionPane.showMessageDialog(parent, "Kund hittades inte.");
            return;
        }

        String orderIdStr = JOptionPane.showInputDialog(parent, "Ange order-ID:");
        if (orderIdStr == null) return;

        int orderId = Integer.parseInt(orderIdStr);

        Order order = customer.getOrderHistory().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElse(null);

        if (order == null) {
            JOptionPane.showMessageDialog(parent, "Order hittades inte.");
            return;
        }

        String productIdStr = JOptionPane.showInputDialog(parent,
                order.toText() + "\nAnge produkt-ID att ta bort:");

        if (productIdStr == null) return;

        int productId = Integer.parseInt(productIdStr);

        if (order.removeProduct(productId)) {
            JOptionPane.showMessageDialog(parent, "Produkten togs bort.");
        } else {
            JOptionPane.showMessageDialog(parent, "Produkten fanns inte i ordern.");
        }

        output.setText(order.toText());
    }

    private void handleAddCustomer() {
        System.out.print("Ange nytt kund-ID: ");
        int id = readInt();

        for (Customer c : customers) {
            if (c.getId() == id) {
                System.out.println("En kund med detta ID finns redan.");
                return;
            }
        }

        System.out.print("Ange kundens namn: ");
        String name = scanner.next();

        System.out.print("Ange kundens email: ");
        String email = scanner.next();

        Customer newCustomer = new Customer(id, name, email);
        customers.add(newCustomer);

        System.out.println("Ny kund tillagd:");
        System.out.println(newCustomer);
    }

    private void handleDeleteCustomer() {
        printCustomers();
        System.out.print("Ange ID på kunden som ska tas bort: ");
        int id = readInt();

        Customer toRemove = null;

        for (Customer c : customers) {
            if (c.getId() == id) {
                toRemove = c;
                break;
            }
        }

        if (toRemove == null) {
            System.out.println("Ingen kund med detta ID.");
            return;
        }

        // Optional: block deletion if customer has orders
        if (!toRemove.getOrderHistory().isEmpty()) {
            System.out.println("Kunden har orders och kan inte tas bort.");
            return;
        }

        customers.remove(toRemove);
        System.out.println("Kunden har tagits bort.");
    }

    //-----------------------------GUI-----------------------------
    public void runWithoutConsole() {
        seedCustomers();
        seedProducts();
    }
    public void addCustomerSwing(JFrame parent, JTextArea output) {

        String idStr = JOptionPane.showInputDialog(parent, "Ange nytt kund-ID:");
        if (idStr == null) return;

        int id = Integer.parseInt(idStr);

        // Check if ID already exists
        for (Customer c : customers) {
            if (c.getId() == id) {
                JOptionPane.showMessageDialog(parent, "En kund med detta ID finns redan.");
                return;
            }
        }

        String name = JOptionPane.showInputDialog(parent, "Ange kundens namn:");
        if (name == null || name.isEmpty()) return;

        String email = JOptionPane.showInputDialog(parent, "Ange kundens email:");
        if (email == null || email.isEmpty()) return;

        Customer newCustomer = new Customer(id, name, email);
        customers.add(newCustomer);

        output.setText("Ny kund tillagd:\n" + newCustomer.toString());
    }

    public void deleteCustomerSwing(JFrame parent, JTextArea output) {

        String idStr = JOptionPane.showInputDialog(parent, "Ange ID på kunden som ska tas bort:");
        if (idStr == null) return;

        int id = Integer.parseInt(idStr);

        Customer toRemove = null;

        for (Customer c : customers) {
            if (c.getId() == id) {
                toRemove = c;
                break;
            }
        }

        if (toRemove == null) {
            JOptionPane.showMessageDialog(parent, "Ingen kund med detta ID.");
            return;
        }

        if (!toRemove.getOrderHistory().isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Kunden har orders och kan inte tas bort.");
            return;
        }

        customers.remove(toRemove);

        output.setText("Kunden har tagits bort:\n" + toRemove.toString());
    }




}
package org.example;

public class App {
    public void run() {
        Customer customer1 = new Customer(1, "Andreas", "Andreas@gmail.com");
        Customer customer2 = new Customer(2, "Stefan", "Stefan@gmail.com");


        Order order = new Order(1, customer1 );


        order.addProduct(new Product(1, "Lasersvärd", 29.90));
        order.addProduct(new Product(2, "Penna", 9.90));
        order.addProduct(new Product(3, "Ryggsäck", 399.00));
        order.addProduct(new Product(4, "Latitud 65", 15.00));
        order.addProduct(new Product(5, "RedBull", 25.00));
        order.addProduct(new Product(6, "Airpods", 499.00));
        order.addProduct(new Product(7, "Nocco", 25.00));
        order.addProduct(new Product(8, "Lådbil", 25.00));
        order.addProduct(new Product(9, "kaffekopp", 499.00));
        order.addProduct(new Product(10, "Telefon", 1499.00));


        order.printSummary();


    }



}

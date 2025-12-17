package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShopGUI extends JFrame {

    private App app;

    public ShopGUI(App app) {
        this.app = app;

        setTitle("Online Shop");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- MENU BUTTONS ---
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1));

        JButton btnCustomers = new JButton("Visa kunder");
        JButton btnProducts = new JButton("Visa produkter");
        JButton btnCreateOrder = new JButton("Skapa order");
        JButton btnShowOrders = new JButton("Visa kundens orders");
        JButton btnRemoveProduct = new JButton("Ta bort produkt från order");

        menuPanel.add(btnCustomers);
        menuPanel.add(btnProducts);
        menuPanel.add(btnCreateOrder);
        menuPanel.add(btnShowOrders);
        menuPanel.add(btnRemoveProduct);

        add(menuPanel, BorderLayout.WEST);

        // --- OUTPUT AREA ---
        JTextArea output = new JTextArea();
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);
        add(scroll, BorderLayout.CENTER);

        // --- BUTTON ACTIONS ---
        btnCustomers.addActionListener((ActionEvent e) -> {
            output.setText(app.getCustomersAsText());
        });

        btnProducts.addActionListener((ActionEvent e) -> {
            output.setText(app.getProductsAsText());
        });

        btnCreateOrder.addActionListener((ActionEvent e) -> {
            app.createOrderSwing(this, output);
        });

        btnShowOrders.addActionListener((ActionEvent e) -> {
            app.showOrdersSwing(this, output);
        });

        btnRemoveProduct.addActionListener((ActionEvent e) -> {
            app.removeProductSwing(this, output);
        });

        setVisible(true);
    }
}
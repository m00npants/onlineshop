package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShopGUI extends JFrame {

    private App app;

    public ShopGUI(App app) {
        this.app = app;

        setTitle("Online Shop");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ✅ HEADER BAR
        JPanel header = new JPanel();
        header.setBackground(new Color(20, 20, 25));
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.setPreferredSize(new Dimension(900, 50));

        JLabel title = new JLabel("Online Shop");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        header.add(title);
        add(header, BorderLayout.NORTH);

        // ✅ SIDEBAR (Battle.net style)
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(25, 25, 30));
        sidebar.setLayout(new GridLayout(12, 1, 0, 10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        sidebar.setPreferredSize(new Dimension(220, 600));

        // ✅ Create styled buttons
        JButton btnCustomers = createNavButton("Visa kunder");
        JButton btnProducts = createNavButton("Visa produkter");
        JButton btnCreateOrder = createNavButton("Skapa order");
        JButton btnShowOrders = createNavButton("Visa kundens orders");
        JButton btnRemoveProduct = createNavButton("Ta bort produkt");
        JButton btnAddCustomer = createNavButton("Lägg till kund");
        JButton btnDeleteCustomer = createNavButton("Ta bort kund");
        JButton btnEditCustomer = createNavButton("Redigera kund");

        sidebar.add(btnAddCustomer);
        sidebar.add(btnCustomers);
        sidebar.add(btnProducts);
        sidebar.add(btnCreateOrder);
        sidebar.add(btnShowOrders);
        sidebar.add(btnRemoveProduct);
        sidebar.add(btnDeleteCustomer);
        sidebar.add(btnEditCustomer);

        add(sidebar, BorderLayout.WEST);

        // ✅ CONTENT PANEL
        JPanel content = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(35, 35, 45));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        content.setLayout(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ✅ OUTPUT AREA
        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setBackground(new Color(45, 45, 55));
        output.setForeground(Color.WHITE);
        output.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        output.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBorder(null);

        content.add(scroll, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        // ✅ BUTTON ACTIONS
        btnCustomers.addActionListener(e -> output.setText(app.getCustomersAsText()));
        btnProducts.addActionListener(e -> output.setText(app.getProductsAsText()));
        btnCreateOrder.addActionListener(e -> app.createOrderSwing(this, output));
        btnShowOrders.addActionListener(e -> app.showOrdersSwing(this, output));
        btnRemoveProduct.addActionListener(e -> app.removeProductSwing(this, output));
        btnAddCustomer.addActionListener(e -> app.addCustomerSwing(this, output));
        btnDeleteCustomer.addActionListener(e -> app.deleteCustomerSwing(this, output));
        btnEditCustomer.addActionListener(e -> app.editCustomerSwing(this, output));

        setVisible(true);
    }


    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(40, 40, 50));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(60, 60, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(40, 40, 50));
            }
        });

        return btn;
    }
}
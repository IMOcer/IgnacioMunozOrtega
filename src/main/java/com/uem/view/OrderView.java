package com.uem.view;                     

import javax.swing.*;
import com.uem.model.Article;             
import com.uem.model.Order;                
import java.awt.*;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // ↓ sustituye los 4 comentarios "add ..." por esto
        add(new JLabel("Order ID:"));
        add(searchField);
        add(searchButton);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));
        // ↑

        pack();                            
        setVisible(true);
    }

    public String getSearchId() {
        return searchField.getText().trim(); 
    }

    public JButton getSearchButton() {
        return searchButton;                  
    }

    public void displayOrder(Order order, double rate) {
       
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(order.getId()).append("\n\n");
        for (Article a : order.getArticles()) {
            sb.append(String.format("%s  x%d  %.2f €  (-%.0f%%)%n",
                    a.getName(), a.getQuantity(), a.getPrice() * rate, a.getDiscount()));
        }
        sb.append(String.format("%nGross total: %.2f €%n", order.getGrossTotal() * rate));
        sb.append(String.format("Discounted total: %.2f €", order.getDiscountedTotal() * rate));
        resultArea.setText(sb.toString());
    }

   
    public void displayMessage(String message) {
        resultArea.setText(message);
    }
}
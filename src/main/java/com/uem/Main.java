package com.uem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
iniciimport com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uem.controller.OrderController;
import com.uem.model.Order;
import com.uem.view.OrderView;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static final double RATE = 1.0;

    public static void main(String[] args) {
        List<Order> orders = loadOrders();
        log.debug("Total orders loaded: {}", orders.size());
        for (Order order : orders) {
            log.debug("Loaded order: {}", order.getId());
        }

        // Initialize MVC
        SwingUtilities.invokeLater(() -> {
            OrderView view = new OrderView();
            new OrderController(view, orders);
        });
    }

    private static List<Order> loadOrders() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = Main.class.getResourceAsStream("/orders.json")) {
            if (input == null) {
                throw new IllegalStateException("No se encuentra orders.json en resources");
            }
            return mapper.readValue(input, new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            throw new IllegalStateException("Error leyendo orders.json", e);
        }
    }
}
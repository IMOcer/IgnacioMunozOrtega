package com.uem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uem.model.Order;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<Order> orders = loadOrders();
        log.debug("Total orders loaded: {}", orders.size());
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
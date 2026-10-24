package com.uem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uem.Main;
import com.uem.Searcher;
import com.uem.model.Order;
import com.uem.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    //Atributo de clase OrderView
    OrderView view;
    //Lista de orders
    List<Order> orders;
    //Atributo de clase Searcher
    Searcher searcher;

    public OrderController(OrderView view, List<Order> orders) {
        //Initialize attributes
        this.view = view;
        this.orders = orders;
        this.searcher = new Searcher();

        //Adding action listener to the search button, call searchOrder on click
        view.getSearchButton().addActionListener(e -> searchOrder());
    }

    private void searchOrder() {
        String id = view.getSearchId();

        Order order = searcher.findOrderById(id, orders);

        if (order != null) {
            log.info("Order found: {}", id);
            view.displayOrder(order, Main.RATE);
        } else {
            log.info("Order not found: {}", id);
            view.displayMessage("Order not found.");
        }
    }
}
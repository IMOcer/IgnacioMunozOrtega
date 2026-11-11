package com.uem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uem.Searcher;
import com.uem.model.Order;
import com.uem.Service.ExchangeRateService;
import com.uem.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    
    OrderView view;
    
    List<Order> orders;

    Searcher searcher;
    
    ExchangeRateService exchangeRateService;

    public OrderController(OrderView view, List<Order> orders) {
        //Initialize attributes
        this.view = view;
        this.orders = orders;
        this.searcher = new Searcher();
        this.exchangeRateService = new ExchangeRateService();

        //Adding action listener to the search button, call searchOrder on click
        view.getSearchButton().addActionListener(e -> searchOrder());
    }

    private void searchOrder() {
        String id = view.getSearchId();

        Order order = searcher.findOrderById(id, orders);

        if (order != null) {
            log.info("Order found: {}", id);
            // Se consulta en cada búsqueda para tener el tipo de cambio del momento
            Double rate = exchangeRateService.getEurToUsdRate();
            view.displayOrder(order, rate);
        } else {
            log.info("Order not found: {}", id);
            view.displayMessage("Order not found.");
        }
    }
}
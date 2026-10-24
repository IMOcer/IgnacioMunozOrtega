package com.uem.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uem.Main;
import com.uem.model.Order;
import com.uem.Searcher;
import com.uem.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    //Atributo de clase OrderView
    OrderView view;
    //Lista de orders
    //Atributo de clase Searcher

    public OrderController(OrderView view, List<Order> orders) {
        //Initialize attributes

        //Adding action listener to the search button, call searchOrder on click
        //...
    }

    private void searchOrder() {
        String id = view.getSearchId();

        //... display order if found, else display "Order not found."
    }
}

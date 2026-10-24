package com.uem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class OrderTest {

    private Order crearPedido() {
        Article a1 = new Article("Cuaderno", 2, 10.0, 0.0);   // bruto 20, con descuento 20
        Article a2 = new Article("Mochila", 1, 50.0, 10.0);   // bruto 50, con descuento 45
        return new Order("P-001", Arrays.asList(a1, a2));
    }

    @Test
    void getGrossTotalSumaTodosLosArticulos() {
        assertEquals(70.0, crearPedido().getGrossTotal(), 0.001);
    }

    @Test
    void getDiscountedTotalSumaConDescuento() {
        assertEquals(65.0, crearPedido().getDiscountedTotal(), 0.001);
    }
}
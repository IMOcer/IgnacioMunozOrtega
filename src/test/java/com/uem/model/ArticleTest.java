package com.uem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArticleTest {

    @Test
    void getGrossAmountMultiplicaUnidadesPorPrecio() {
        Article article = new Article("Lápiz", 3, 10.0, 20.0);
        assertEquals(30.0, article.getGrossAmount(), 0.001);
    }

    @Test
    void getDiscountedAmountAplicaElDescuento() {
        Article article = new Article("Lápiz", 3, 10.0, 20.0);
        assertEquals(24.0, article.getDiscountedAmount(), 0.001);
    }

    @Test
    void sinDescuentoElImporteEsElMismo() {
        Article article = new Article("Goma", 2, 5.0, 0.0);
        assertEquals(article.getGrossAmount(), article.getDiscountedAmount(), 0.001);
    }
}
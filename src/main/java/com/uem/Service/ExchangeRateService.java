package com.uem.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);
    private static final String API_URL = "https://api.frankfurter.dev/v1/latest?base=EUR&symbols=USD";

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private final ObjectMapper mapper = new ObjectMapper();


     // Devuelve cuántos dólares vale 1 euro ahora mismo, o null si no se ha podido consultar 
     
    public Double getEurToUsdRate() {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                log.warn("La API de tipo de cambio ha respondido con código {}", response.statusCode());
                return null;
            }

            JsonNode root = mapper.readTree(response.body());
            double rate = root.path("rates").path("USD").asDouble();
            log.info("Tipo de cambio EUR/USD a fecha {}: {}", root.path("date").asText(), rate);
            return rate;
        } catch (IOException e) {
            log.error("No se pudo obtener el tipo de cambio", e);
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Consulta del tipo de cambio interrumpida", e);
            return null;
        }
    }
}
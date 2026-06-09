package co.edu.udea.fabrica.finanzas.stepdefinitions;

import io.cucumber.java.Before;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataCleanupHook {

    private static final String RESET_URL = "http://localhost:8080/api/test/reset";

    @Before(order = 0)
    public void cleanupTestData() {
        try {
            HttpClient http = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(RESET_URL))
                    .DELETE()
                    .build();
            http.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("[cleanup] Warning: " + e.getMessage());
        }
    }
}

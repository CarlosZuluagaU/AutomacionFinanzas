package co.edu.udea.fabrica.finanzas.stepdefinitions;

import io.cucumber.java.Before;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CucumberHooks {

    static final String BASE_URL   = "http://localhost:8080";
    static final String TEST_EMAIL = "test@finanzas.com";
    static final String TEST_PASS  = "Test1234!";
    static final String TEST_NAME  = "Carlos Test";

    private static boolean userEnsured = false;

    @Before(order = 0)
    public void ensureTestUserExists() {
        if (userEnsured) return;
        try {
            HttpClient client = HttpClient.newHttpClient();
            String body = String.format(
                    "{\"name\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}",
                    TEST_NAME, TEST_EMAIL, TEST_PASS
            );
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/api/v1/auth/register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();
            if (status == 201 || status == 409) {
                userEnsured = true;
                System.out.println("[CucumberHooks] Usuario de prueba listo (status " + status + ")");
            } else {
                System.err.println("[CucumberHooks] Respuesta inesperada al registrar usuario: " + status + " - " + response.body());
            }
        } catch (Exception e) {
            System.err.println("[CucumberHooks] No se pudo registrar el usuario de prueba: " + e.getMessage());
        }
    }
}

package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {

    public static String fetchAPIData(String _url, String apiKey) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(_url))
                .GET()
                .header("Accept", "application/json")
                .header("User-Agent", "server")
                .header(apiKey != null ? "app-id" : "null", apiKey != null ? apiKey : "null")
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }

    }
}

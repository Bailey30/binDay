package com.mycompany.app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class DataService {
    String response;

    public String GetData() {
        String url = "https://www.manchester.gov.uk/bincollections/";

        Map<String, String> formData = new HashMap<>();
        formData.put("mcc_bin_dates_uprn", "010014176534");
        formData.put("mcc_bin_dates_submit", "Go");

        String formDataString = buildFormDataString(formData);

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .header("Content-type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formDataString)).build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            this.response = response.body();
            System.out.println("Status Code: " + statusCode);

        } catch (Exception e) {
            System.out.println("error getting data" + " " + e);
        }
        // this.response = response;
        return response;

    }

    private static String buildFormDataString(Map<String, String> formData) {
        StringBuilder formDataString2 = new StringBuilder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            // does this first on each step, so wont add any unnecessary
            // says append, but its still adding to the front of the string
            if (formDataString2.length() > 0) {
                formDataString2.append("&");

            }

            formDataString2.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());

        }
        return formDataString2.toString();
    }
}

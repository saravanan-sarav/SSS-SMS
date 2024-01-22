package com.restapi.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class LocationService {
    private static final long REQUEST_DELAY_MS = 1000;
    public String getLocationName(double latitude, double longitude) {
        String formattedData = null;
        try {
            String apiKey = "45643afc35d449d98ce4c4de1a917528";
            String url = "https://api.opencagedata.com/geocode/v1/json?q=" + latitude + "+" + longitude + "&key=" + apiKey;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                formattedData = jsonResponse.getAsJsonArray("results")
                        .get(0).getAsJsonObject()
                        .get("formatted").getAsString();

                // Print the formatted data
                // Call the method to get the IP address after getting the location name
            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedData;
    }

}

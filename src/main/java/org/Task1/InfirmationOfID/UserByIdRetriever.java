package org.Task1.InfirmationOfID;

import com.google.gson.Gson;
import org.Common.BaseUrlProvider;
import org.Task1.Models.UserEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class UserByIdRetriever {

    private final BaseUrlProvider baseUrlProvider;

    public UserByIdRetriever(BaseUrlProvider baseUrlProvider) {
        this.baseUrlProvider = baseUrlProvider;
    }

    public Optional<UserEntity> getUserById(int id) {
        try {
            String baseUrl = baseUrlProvider.getBaseUrl();
            URL url = new URL(baseUrl + "/users/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    UserEntity user = new Gson().fromJson(response.toString(), UserEntity.class);
                    return Optional.ofNullable(user);
                }
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

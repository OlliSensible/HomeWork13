package org.Task1.InfirmationOfUsername;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.Common.BaseUrlProvider;
import org.Task1.Models.UserEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class UserByUsernameRetriever {

    private final BaseUrlProvider baseUrlProvider;

    public UserByUsernameRetriever(BaseUrlProvider baseUrlProvider) {
        this.baseUrlProvider = baseUrlProvider;
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        try {
            String baseUrl = baseUrlProvider.getBaseUrl();
            URL url = new URL(baseUrl + "/users?username=" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    List<UserEntity> users = new Gson().fromJson(response.toString(), new TypeToken<List<UserEntity>>() {}.getType());
                    if (!users.isEmpty()) {
                        return Optional.of(users.get(0));
                    }
                }
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

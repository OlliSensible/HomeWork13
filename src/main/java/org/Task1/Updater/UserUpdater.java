package org.Task1.Updater;

import org.Task1.AddUsers.UserCrudApp;
import org.Task1.Models.UserEntity;
import org.Common.BaseUrlProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserUpdater {

    private final BaseUrlProvider baseUrlProvider;

    public UserUpdater(BaseUrlProvider baseUrlProvider) {
        this.baseUrlProvider = baseUrlProvider;
    }

    public UserEntity updateUser(int id) {
        try {
            String baseUrl = baseUrlProvider.getBaseUrl();
            URL url = new URL(baseUrl + "/users/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String requestBody = "{\"id\":5,\"name\":\"Bjorn Ironside\",\"username\":\"ironside\",\"email\":\"ragnarsson@example.com\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                return UserCrudApp.getUserEntity(connection);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

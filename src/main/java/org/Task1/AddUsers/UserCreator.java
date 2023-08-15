package org.Task1.AddUsers;

import com.google.gson.Gson;
import org.Task1.Models.UserEntity;
import org.Task1.Models.UsersExample;
import org.Common.DefaultBaseUrlProvider;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserCreator {

    private final String BASE_URL = new DefaultBaseUrlProvider().getBaseUrl();
    private static final Gson gson = new Gson();

    public UserEntity createUser() {
        try {
            UserEntity user = UsersExample.createUserExample();
            String requestBody = gson.toJson(user);

            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_CREATED == responseCode) {
                return UserCrudApp.getUserEntity(connection);
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException("Something goes wrong");
        }
        return null;
    }
}

package org.Task1.ObtainingInformationAboutUsers;

import org.Common.BaseUrlProvider;
import org.Task1.Models.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UserListRetriever {

    private final BaseUrlProvider baseUrlProvider;
    public static final Gson gson = new Gson();

    public UserListRetriever(BaseUrlProvider baseUrlProvider) {
        this.baseUrlProvider = baseUrlProvider;
    }

    public List<UserEntity> getUsers() {
        try {
            URL url = new URL(baseUrlProvider.getBaseUrl());
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
                    Type userListType = new TypeToken<List<UserEntity>>() {}.getType();
                    return gson.fromJson(response.toString(), userListType);
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

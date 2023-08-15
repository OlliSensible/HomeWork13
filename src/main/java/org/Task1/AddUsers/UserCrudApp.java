package org.Task1.AddUsers;

import org.Task1.Models.UserEntity;
import org.Task1.ObtainingInformationAboutUsers.UserListRetriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class UserCrudApp {

    public static UserEntity getUserEntity(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return UserListRetriever.gson.fromJson(response.toString(), UserEntity.class);
        }
    }
}

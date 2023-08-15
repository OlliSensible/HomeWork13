package org.Task1.AddUsers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserDeleter {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    public boolean deleteUser(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

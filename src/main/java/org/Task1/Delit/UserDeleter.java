package org.Task1.Delit;

import org.Common.BaseUrlProvider;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserDeleter {

    private final BaseUrlProvider baseUrlProvider;

    public UserDeleter(BaseUrlProvider baseUrlProvider) {
        this.baseUrlProvider = baseUrlProvider;
    }

    public boolean deleteUser(int id) {
        try {
            String baseUrl = baseUrlProvider.getBaseUrl();
            URL url = new URL(baseUrl + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getResponseCodeAfterDelete(int id) throws IOException {
        String baseUrl = baseUrlProvider.getBaseUrl();
        URL url = new URL(baseUrl + "/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        return responseCode;
    }
}

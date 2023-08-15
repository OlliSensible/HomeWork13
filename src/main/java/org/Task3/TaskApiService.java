package org.Task3;

import com.google.gson.Gson;
import org.Common.HttpClientProvider;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TaskApiService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClientProvider httpClientProvider;

    public TaskApiService(HttpClientProvider httpClientProvider) {
        this.httpClientProvider = httpClientProvider;
    }

    public List<TaskEntity> getOpenTasksForUser(int userId) {
        String userTasksUrl = BASE_URL + "/users/" + userId + "/todos";

        HttpGet request = new HttpGet(userTasksUrl);
        try {
            HttpClient httpClient = httpClientProvider.getHttpClient();
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                TaskEntity[] tasks = new Gson().fromJson(responseBody, TaskEntity[].class);

                return Arrays.stream(tasks)
                        .filter(task -> !task.isCompleted())
                        .toList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

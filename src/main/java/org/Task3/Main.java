package org.Task3;

import org.Common.DefaultHttpClientProvider;
import org.Common.HttpClientProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int userId = 1;

        HttpClientProvider httpClientProvider = new DefaultHttpClientProvider();
        TaskApiService taskApiService = new TaskApiService(httpClientProvider);
        OpenTasksRetriever openTasksRetriever = new OpenTasksRetriever(taskApiService);

        List<TaskEntity> openTasks = openTasksRetriever.getOpenTasksForUser(userId);
        if (openTasks != null) {
            System.out.println("Open Tasks for User ID " + userId + ":");
            for (TaskEntity task : openTasks) {
                System.out.println(task.getId() + ": " + task.getTitle());
            }
        } else {
            System.out.println("Failed to retrieve open tasks for User ID " + userId);
        }
    }
}

package org.Task3;

import java.util.List;

public class OpenTasksRetriever {

    private final TaskApiService TASK_API_SERVICE;

    public OpenTasksRetriever(TaskApiService TASK_API_SERVICE) {
        this.TASK_API_SERVICE = TASK_API_SERVICE;
    }

    public List<TaskEntity> getOpenTasksForUser(int userId) {
        return TASK_API_SERVICE.getOpenTasksForUser(userId);
    }
}

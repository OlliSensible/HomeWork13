package org.Task2;

import com.google.gson.Gson;
import org.Task2.Models.CommentEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CommentFileWriter {

    public static void writeCommentsToFile(int userId, int postNumber, List<CommentEntity> comments) {
        String fileName = "user-" + userId + "-post-" + postNumber + "-comments.json";
        if (comments != null && !comments.isEmpty()) {
            fileName = "user-" + userId + "-post-" + comments.get(0).getPostId() + "-comments.json";
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new Gson();
            gson.toJson(comments, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

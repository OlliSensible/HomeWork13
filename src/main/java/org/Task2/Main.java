package org.Task2;

import org.Common.DefaultHttpClientProvider;
import org.Common.HttpClientProvider;
import org.Task2.LastPost.LastPostCommentsRetriever;
import org.Task2.Models.CommentEntity;
import org.Task2.LastPost.LastPostAndComments;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int userId = 1;

        HttpClientProvider httpClientProvider = new DefaultHttpClientProvider();
        LastPostCommentsRetriever retriever = new LastPostCommentsRetriever(httpClientProvider);

        LastPostAndComments lastPostAndComments = retriever.getLastPostAndCommentsForUser(userId);
        if (lastPostAndComments != null && !lastPostAndComments.getComments().isEmpty()) {
            int selectedPostId = lastPostAndComments.getLastPostId();
            List<CommentEntity> comments = lastPostAndComments.getComments();

            int commentNumber = selectedPostId % comments.size();

            CommentEntity selectedComment = comments.get(commentNumber);

            System.out.println("Selected Comment for User ID " + userId + ", Last Post ID " + selectedPostId + ":");
            System.out.println(selectedComment.getName() + ": " + selectedComment.getBody());

            CommentFileWriter.writeCommentsToFile(userId, selectedPostId, comments);
        } else {
            System.out.println("Failed to retrieve comments for User ID " + userId);
        }
    }
}

package org.Task2.LastPost;

import org.Common.DefaultHttpClientProvider;
import org.Common.HttpClientProvider;
import org.Task2.Models.CommentEntity;

import java.util.List;

public class LastPostCommentsRetriever {

    private final HttpClientProvider httpClientProvider;

    public LastPostCommentsRetriever(HttpClientProvider httpClientProvider) {
        this.httpClientProvider = httpClientProvider;
    }

    public LastPostAndComments getLastPostAndCommentsForUser(int userId) {
        LastPostIdGetter lastPostIdGetter = new LastPostIdGetter(httpClientProvider);
        int lastPostId = lastPostIdGetter.getLastPostId(userId);

        if (lastPostId != -1) {
            LastPostCommentsGetter commentsGetter = new LastPostCommentsGetter(httpClientProvider);
            List<CommentEntity> comments = commentsGetter.getCommentsForPost(lastPostId);
            return new LastPostAndComments(lastPostId, comments);
        }
        return null;
    }

    public static void main(String[] args) {
        int userId = 1;

        HttpClientProvider httpClientProvider = new DefaultHttpClientProvider();
        LastPostCommentsRetriever retriever = new LastPostCommentsRetriever(httpClientProvider);

        LastPostAndComments lastPostAndComments = retriever.getLastPostAndCommentsForUser(userId);
        if (lastPostAndComments != null) {
            int selectedPostId = lastPostAndComments.getLastPostId();
            List<CommentEntity> comments = lastPostAndComments.getComments();

            System.out.println("Comments for User ID " + userId + ", Last Post ID " + selectedPostId + ":");
            for (CommentEntity comment : comments) {
                System.out.println(comment.getId() + ": " + comment.getBody());
            }
        } else {
            System.out.println("Failed to retrieve comments for User ID " + userId);
        }

    }
}

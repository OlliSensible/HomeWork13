package org.Task2;

import java.util.List;

public class LastPostCommentsRetriever {

    public static List<CommentEntity> getLastPostCommentsForUser(int userId) {
        int lastPostId = LastPostIdGetter.getLastPostId(userId);
        if (lastPostId != -1) {
            return LastPostCommentsGetter.getCommentsForPost(lastPostId);
        }
        return null;
    }
}

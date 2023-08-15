package org.Task2.LastPost;

import org.Task2.Models.CommentEntity;

import java.util.List;

public class LastPostAndComments {

    private final int lastPostId;
    private final List<CommentEntity> comments;

    public LastPostAndComments(int lastPostId, List<CommentEntity> comments) {
        this.lastPostId = lastPostId;
        this.comments = comments;
    }

    public int getLastPostId() {
        return lastPostId;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }
}

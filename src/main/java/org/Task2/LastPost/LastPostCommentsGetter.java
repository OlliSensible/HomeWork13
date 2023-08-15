package org.Task2.LastPost;

import com.google.gson.Gson;
import org.Common.HttpClientProvider;
import org.Task2.Models.CommentEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastPostCommentsGetter {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClientProvider httpClientProvider;

    public LastPostCommentsGetter(HttpClientProvider httpClientProvider) {
        this.httpClientProvider = httpClientProvider;
    }

    public List<CommentEntity> getCommentsForPost(int postId) {
        String commentsUrl = BASE_URL + "/posts/" + postId + "/comments";
        HttpGet commentsRequest = new HttpGet(commentsUrl);
        try {
            HttpClient httpClient = httpClientProvider.getHttpClient();
            HttpResponse commentsResponse = httpClient.execute(commentsRequest);
            if (commentsResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String commentsResponseBody = EntityUtils.toString(commentsResponse.getEntity());
                CommentEntity[] comments = new Gson().fromJson(commentsResponseBody, CommentEntity[].class);
                return new ArrayList<>(Arrays.asList(comments));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

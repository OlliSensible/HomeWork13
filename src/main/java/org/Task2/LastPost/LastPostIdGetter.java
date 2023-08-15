package org.Task2.LastPost;

import com.google.gson.Gson;
import org.Common.HttpClientProvider;
import org.Task2.Models.CommentEntity;
import org.Task2.Models.PostEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastPostIdGetter {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClientProvider httpClientProvider;

    public LastPostIdGetter(HttpClientProvider httpClientProvider) {
        this.httpClientProvider = httpClientProvider;
    }

    public int getLastPostId(int userId) {
        String userPostsUrl = BASE_URL + "/users/" + userId + "/posts";
        String lastPostUrl = userPostsUrl + "?_sort=id&_order=desc&_limit=1";

        HttpGet request = new HttpGet(lastPostUrl);
        try {
            HttpClient httpClient = httpClientProvider.getHttpClient();
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                PostEntity[] posts = new Gson().fromJson(responseBody, PostEntity[].class);
                if (posts.length > 0) {
                    return posts[0].getId();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public List<CommentEntity> getCommentsForPost(int postId) {
        String commentsUrl = BASE_URL + "/posts/" + postId + "/comments";
        HttpGet commentsRequest = new HttpGet(commentsUrl);
        try {
            HttpClient httpClient = httpClientProvider.getHttpClient();
            HttpResponse commentsResponse = httpClient.execute(commentsRequest);
            if (commentsResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String commentsResponseBody = EntityUtils.toString(commentsResponse.getEntity());
                System.out.println("Comments response body: " + commentsResponseBody); // Додайте цей вивід
                CommentEntity[] comments = new Gson().fromJson(commentsResponseBody, CommentEntity[].class);
                return new ArrayList<>(Arrays.asList(comments));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

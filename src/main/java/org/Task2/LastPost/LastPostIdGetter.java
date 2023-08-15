package org.Task2;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class LastPostIdGetter {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final HttpClient httpClient = HttpClients.createDefault();

    public static int getLastPostId(int userId) {
        String userPostsUrl = BASE_URL + "/users/" + userId + "/posts";
        String lastPostUrl = userPostsUrl + "?_sort=id&_order=desc&_limit=1";

        HttpGet request = new HttpGet(lastPostUrl);
        try {
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
}

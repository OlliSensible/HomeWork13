package org.Common;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class DefaultHttpClientProvider implements HttpClientProvider {

    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    @Override
    public HttpClient getHttpClient() {
        return HTTP_CLIENT;
    }
}

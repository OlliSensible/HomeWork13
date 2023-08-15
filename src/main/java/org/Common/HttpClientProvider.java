package org.Common;

import org.apache.http.client.HttpClient;

public interface HttpClientProvider {
    HttpClient getHttpClient();
}

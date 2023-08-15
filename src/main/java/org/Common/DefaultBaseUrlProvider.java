package org.Common;

public class DefaultBaseUrlProvider implements BaseUrlProvider {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }
}

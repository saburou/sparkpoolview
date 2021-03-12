package com.example.myapplication1.io.web;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

public interface WebAPI {

    static final String PROP_USER_AGENT = "User-Agent";
    static final String REQUEST_METHOD_GET = "GET";
    static final String REQUEST_METHOD_POST = "POST";
    static final String MESSAGE_INVALID_STATUS = "invalid response status";

    static int DEFAULT_TIMEOUT = 3000;
    static String DEFAULT_AGENT = "Android";

    /**
     * Open HTTPS connection to webapi.
     * @param path API path starts with "/".
     * @return connection
     * @throws IOException connection error.
     */
    HttpsURLConnection open(@NonNull String path) throws IOException;

    /**
     * do GET.
     * @param path API path starts with "/".
     * @return response
     * @throws IOException connection error or invalid response status.
     */
    String get(@NonNull String path) throws IOException;

    /**
     * do POST.
     * @param path API path starts with "/".
     * @return response
     * @throws IOException connection error or invalid response status.
     */
    String post(@NonNull String path);
}

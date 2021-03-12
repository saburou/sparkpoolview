package com.example.myapplication1.io.web;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

public abstract class WebAPI {

    protected String host;
    protected int connectionTimeout;
    protected int readTimeout;
    protected String agent;

    protected static final String PROP_USER_AGENT = "User-Agent";
    protected static final String REQUEST_METHOD_GET = "GET";
    protected static final String REQUEST_METHOD_POST = "POST";
    protected static final String MESSAGE_INVALID_STATUS = "invalid response status";

    protected static int DEFAULT_TIMEOUT = 1000;
    protected static String DEFAULT_AGENT = "Android";

    /**
     * Constructor.
     * @param host web api server address.
     * @param connectionTimeout connectionTimeout
     * @param readTimeout readTimeout
     * @param agent HTTP user-agent
     */
    public WebAPI(@NonNull String host, int connectionTimeout, int readTimeout, @NonNull String agent){
        this.host = host;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.agent = agent;
    }

    /**
     * read responseBody.
     * @param input output from connection.
     * @return responseBody
     * @throws IOException connection error or io error.
     */
    public String read(@NonNull InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String oneline = reader.readLine();
        while (oneline != null){
            builder.append(oneline);
            oneline = reader.readLine();
        }
        reader.close();
        return builder.toString();
    }

    /**
     * Open HTTPS connection to webapi.
     * @param path API path starts with "/".
     * @return connection
     * @throws IOException connection error.
     */
    public abstract HttpsURLConnection open(@NonNull String path) throws IOException;

    /**
     * do GET.
     * @param path API path starts with "/".
     * @return response
     * @throws IOException connection error or invalid response status.
     */
    public abstract String get(@NonNull String path) throws IOException;

    /**
     * do POST.
     * @param path API path starts with "/".
     * @return response
     * @throws IOException connection error or invalid response status.
     */
    public abstract String post(@NonNull String path);

}

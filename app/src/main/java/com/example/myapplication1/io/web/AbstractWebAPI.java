package com.example.myapplication1.io.web;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class AbstractWebAPI implements WebAPI {

    protected String host;
    protected int connectionTimeout;
    protected int readTimeout;
    protected String agent;

    /**
     * Constructor.
     *
     * @param host              web api server address.
     * @param connectionTimeout connectionTimeout
     * @param readTimeout       readTimeout
     * @param agent             HTTP user-agent
     */
    public AbstractWebAPI(@NonNull String host, int connectionTimeout, int readTimeout, @NonNull String agent) {
        this.host = host;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.agent = agent;
    }

    /**
     * read responseBody.
     *
     * @param input output from connection.
     * @return responseBody
     * @throws IOException connection error or io error.
     */
    public String read(@NonNull InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String oneline = reader.readLine();
        while (oneline != null) {
            builder.append(oneline);
            oneline = reader.readLine();
        }
        reader.close();
        return builder.toString();
    }
}


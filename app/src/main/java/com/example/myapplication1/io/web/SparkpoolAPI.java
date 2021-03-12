package com.example.myapplication1.io.web;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SparkpoolAPI extends WebAPI {

    public SparkpoolAPI(String host) {
        super(host, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_AGENT);
    }

    @Override
    public HttpsURLConnection open(@NonNull String path) throws IOException {
        URL url = new URL(super.host + path);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setConnectTimeout(super.connectionTimeout);
        con.setReadTimeout(super.readTimeout);
        con.addRequestProperty(PROP_USER_AGENT, super.agent);
        return con;
    }

    @Override
    public String get(@NonNull String path) throws IOException {
        HttpsURLConnection con = open(path);
        con.setRequestMethod(REQUEST_METHOD_GET);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.connect();
        int status = con.getResponseCode();
        if (status != HttpsURLConnection.HTTP_OK) {
            throw new IOException(MESSAGE_INVALID_STATUS);
        }
        InputStream input = con.getInputStream();
        return read(input);
    }

    @Override
    public String post(@NonNull String path) {
        return "";
    }
}

package com.example.myapplication1.thread;

import androidx.annotation.NonNull;

import com.example.myapplication1.io.PoolStatusParser;
import com.example.myapplication1.io.web.SparkpoolAPI;
import com.example.myapplication1.io.web.WebAPI;
import com.example.myapplication1.model.PoolStatus;
import com.example.myapplication1.model.Settings;

import org.json.JSONException;

import java.io.IOException;

public class PoolStatusTask implements Runnable {

    private Settings settings;
    private PoolStatus poolStatus;
    private String path;

    public PoolStatusTask(@NonNull Settings settings, @NonNull String path) {
        this.settings = settings;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            poolStatus = request();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected PoolStatus request() throws IOException, JSONException {
        if (settings == null || settings.hasNoValue()) return null;
        WebAPI api = new SparkpoolAPI(settings.getUrl().toString());
        String response = api.get(path + "?currency=" + settings.getCurrency().name());
        PoolStatusParser parser = new PoolStatusParser();
        return parser.parse(response);
    }

    public PoolStatus getPoolStatus() {
        return poolStatus;
    }
}

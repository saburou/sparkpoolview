package com.example.myapplication1.io;

import androidx.annotation.NonNull;

import com.example.myapplication1.model.PoolStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PoolStatusParser implements Parser<PoolStatus> {

    protected static BigDecimal THOUSAND = BigDecimal.valueOf(1000);

    @Override
    public PoolStatus parse(@NonNull String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        PoolStatus status = new PoolStatus();
        // parse elements
        JSONObject data = json.getJSONObject("data");
        BigDecimal hashrate = new BigDecimal(data.getString("poolHashrate"));
        hashrate = hashrate.divide(THOUSAND).divide(THOUSAND).divide(THOUSAND).divide(THOUSAND);
        status.setHashrate(hashrate.doubleValue());
        status.setIncome24H(data.getDouble("meanIncome24h"));
        BigDecimal difficulty = BigDecimal.valueOf(data.getDouble("difficulty"));
        difficulty = difficulty.divide(THOUSAND).divide(THOUSAND).divide(THOUSAND).divide(THOUSAND).divide(THOUSAND);
        status.setDifficulty(difficulty.doubleValue());
        BigDecimal networkHashrate = new BigDecimal(data.getString("networkHashrate"));
        networkHashrate = networkHashrate.divide(THOUSAND).divide(THOUSAND).divide(THOUSAND).divide(THOUSAND);
        status.setNetworkHashrate(networkHashrate.doubleValue());
        return status;
    }
}

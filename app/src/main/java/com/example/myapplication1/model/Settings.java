package com.example.myapplication1.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication1.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Settings {
    URL url;
    Currency currency;
    String miner;

    public Settings(){
    }

    public Settings(String url, String currency, String miner) throws MalformedURLException {
        // init url
        this.url = new URL(url);
        // init currency
        this.currency = Currency.valueOf(currency);
        // init miner
        this.miner = miner;
    }

    public boolean hasNoValue(){
        boolean urlIsEmpty = (url == null) || (url.getHost().length() == 0);
        boolean currencyIsEmpty = (currency == null);
        boolean minerIsEmpty = (miner == null) || (miner.length() == 0);
        return urlIsEmpty || currencyIsEmpty || minerIsEmpty;
    }

    public URL getUrl() {
        return url;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getMiner() {
        return miner;
    }
}

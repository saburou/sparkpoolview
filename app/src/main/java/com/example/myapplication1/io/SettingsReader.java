package com.example.myapplication1.io;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.myapplication1.R;
import com.example.myapplication1.model.Settings;

import java.net.MalformedURLException;

public class SettingsReader {
    public static Settings read(SharedPreferences pref, Resources res) {
        // server address
        String address = pref.getString(res.getString(R.string.labelServer), res.getString(R.string.default_serverAddress));
        // currency
        String currency = pref.getString(res.getString(R.string.labelCurrency), res.getString(R.string.text_eth));
        // miner
        String miner = pref.getString(res.getString(R.string.labelMiner), "");

        // init
        try {
            return new Settings(address, currency, miner);
        } catch (MalformedURLException e) {
            // Each Values have default value so normally any exceptions will not occur.
            e.printStackTrace();
            return new Settings();
        }
    }
}

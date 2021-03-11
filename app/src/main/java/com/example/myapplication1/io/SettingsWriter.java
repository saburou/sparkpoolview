package com.example.myapplication1.io;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;

import com.example.myapplication1.R;
import com.example.myapplication1.model.Currency;

public class SettingsWriter {
    public static boolean write(SharedPreferences pref, Resources res, @NonNull String server, @NonNull String currency, @NonNull String miner) {
        SharedPreferences.Editor editor = pref.edit();
        // server address
        editor.putString(res.getString(R.string.labelServer), server);
        // currency
        editor.putString(res.getString(R.string.labelCurrency), currency);
        // miner
        editor.putString(res.getString(R.string.labelMiner), miner);

        // commit settings
        return editor.commit();
    }
}

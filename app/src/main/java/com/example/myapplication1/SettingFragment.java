package com.example.myapplication1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication1.model.Currency;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication1.model.Settings;

import java.net.MalformedURLException;
import java.net.URL;

public class SettingFragment extends Fragment {

    View view;
    LayoutInflater inflater;
    ViewGroup container;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        loadLayout(inflater, container);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // load settings and update layout with settings
        Settings setting = load();
        if (setting.hasNoValue()) return;
        updateLayout(inflater, container, setting);
    }

    protected void loadLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        // load spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item);
        for (Currency c : Currency.values()) adapter.add(c.toString());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerCurrency);
        spinner.setAdapter(adapter);
        // set listener to save button
        View.OnClickListener saveButtonListener = v -> {
            save();
        };
        view.findViewById(R.id.buttonSave).setOnClickListener(saveButtonListener);
    }

    protected void updateLayout(LayoutInflater inflater, ViewGroup container, @NonNull Settings setting) {
        // server address
        EditText textServer = (EditText) view.findViewById(R.id.editTextServer);
        URL url = setting.getUrl();
        if (url != null) textServer.setText(url.toString());
        // miner
        EditText textMiner = (EditText) view.findViewById(R.id.editTextMiner);
        String miner = setting.getMiner();
        if (miner != null) textMiner.setText(miner);
        // spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerCurrency);
        Currency currency = setting.getCurrency();
        int currencyIndex = 0;
        for (Currency c : Currency.values()) {
            if (currency.equals(c)) break;
            currencyIndex++;
        }
        spinner.setSelection(currencyIndex);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public Settings load() {
        SharedPreferences pref = this.getActivity().getSharedPreferences(getString(R.string.name_preference), Context.MODE_PRIVATE);
        // server address
        String address = pref.getString(getResources().getString(R.string.labelServer), getResources().getString(R.string.default_serverAddress));
        // currency
        String currency = pref.getString(getResources().getString(R.string.labelCurrency), getResources().getString(R.string.text_eth));
        // miner
        String miner = pref.getString(getResources().getString(R.string.labelMiner), "");

        // init
        try {
            return new Settings(address, currency, miner);
        } catch (MalformedURLException e) {
            // Each Values have default value so normally any exceptions will not occur.
            e.printStackTrace();
            return new Settings();
        }
    }

    public void save() {
        SharedPreferences pref = this.getActivity().getSharedPreferences(getString(R.string.name_preference), Context.MODE_PRIVATE);
        Editor editor = pref.edit();
        // server address
        EditText textServer = (EditText) view.findViewById(R.id.editTextServer);
        String server = textServer.getText().toString();
        editor.putString(getResources().getString(R.string.labelServer), server);
        // currency
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerCurrency);
        Currency currency = Currency.values()[spinner.getSelectedItemPosition()];
        editor.putString(getResources().getString(R.string.labelCurrency), currency.name());
        // miner
        EditText textMiner = (EditText) view.findViewById(R.id.editTextMiner);
        String miner = textMiner.getText().toString();
        editor.putString(getResources().getString(R.string.labelMiner), miner);

        // commit settings
        boolean saved = editor.commit();
        if (saved) {
            Toast.makeText(getActivity().getApplicationContext(), "settings saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "save failed", Toast.LENGTH_LONG).show();
        }
    }
}
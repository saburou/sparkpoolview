package com.example.myapplication1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication1.io.SettingsReader;
import com.example.myapplication1.model.PoolStatus;
import com.example.myapplication1.model.Settings;
import com.example.myapplication1.thread.PoolStatusTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PoolStatusFragment extends Fragment {

    LayoutInflater inflater;
    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poolstatus, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // load settings
        Settings settings = load();
        // if invalid settings, display issues and do not call web api.
        if (settings.hasNoValue()) {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.message_load_settings_failed), Toast.LENGTH_LONG).show();
            return;
        }
        // get view
        View view = inflater.inflate(R.layout.fragment_poolstatus, container, false);
        // update currency
        TextView currency = (TextView) view.findViewById(R.id.text_poolcurrency);
        currency.setText(settings.getCurrency().name());
        // SYNCHRONIZE thread. call web api and update items.
        ExecutorService pool = Executors.newSingleThreadExecutor();
        try {
            PoolStatusTask task = new PoolStatusTask(settings, getString(R.string.path_pool_status));
            Future<?> future = pool.submit(task);
            // wait for api task complete
            future.get();
            PoolStatus status = task.getPoolStatus();
            if(status != null){
                update(view, status);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (pool != null) pool.shutdown();
        }
    }

    protected void update(View view, PoolStatus status) {
        TextView hashrate = (TextView) view.findViewById(R.id.text_poolhashrate);
        hashrate.setText(String.valueOf(status.getHashrate()));
        TextView networkHashrate = (TextView) view.findViewById(R.id.text_poolnetworkhashrate);
        networkHashrate.setText(String.valueOf(status.getNetworkHashrate()));
        TextView income24h = (TextView) view.findViewById(R.id.text_poolincome24h);
        income24h.setText(String.valueOf(status.getIncome24H()));
        TextView difficulty = (TextView) view.findViewById(R.id.text_pooldifficulty);
        difficulty.setText(String.valueOf(status.getDifficulty()));
    }

    protected Settings load() {
        SharedPreferences pref = this.getActivity().getSharedPreferences(getString(R.string.name_preference), Context.MODE_PRIVATE);
        return SettingsReader.read(pref, getResources());
    }

}
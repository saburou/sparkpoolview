package com.example.myapplication1.io;

import androidx.annotation.NonNull;

import org.json.JSONException;

public interface Parser<T> {

    T parse(@NonNull String str) throws JSONException;
}

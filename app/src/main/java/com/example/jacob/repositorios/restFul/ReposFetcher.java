package com.example.jacob.repositorios.restFul;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.jacob.repositorios.models.SearchResponse;
import com.example.jacob.repositorios.models.Item;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReposFetcher extends AsyncTaskLoader<Item[]> {
    private final String API_URL;

    public ReposFetcher(Context context, String language, int page) {
        super(context);
        API_URL = "https://api.github.com/search/repositories?q=language:"
                + language + "&page=" + page;
    }

    @Override
    public Item[] loadInBackground() {
        try {
            Request.Builder builder = new Request.Builder().url(API_URL);
            Request request = builder.build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                Gson gson = new Gson();
                String json = response.body().string();
                SearchResponse data = gson.fromJson(json, SearchResponse.class);

                return data.getItems();
            } else {
                throw new IOException("Got response code " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deliverResult(Item[] data) {
        super.deliverResult(data);
    }
}
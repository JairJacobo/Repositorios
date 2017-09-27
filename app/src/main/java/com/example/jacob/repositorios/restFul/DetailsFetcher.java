package com.example.jacob.repositorios.restFul;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.jacob.repositorios.detail.FetchData;
import com.example.jacob.repositorios.models.Contributor;
import com.example.jacob.repositorios.models.Issue;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsFetcher extends AsyncTaskLoader<FetchData> {
    private final String CONTRIBUTORS_URL;
    private final String ISSUES_URL;
    private int max;

    public DetailsFetcher(Context context, String name, int max) {
        super(context);
        this.max = max;
        ISSUES_URL = "https://api.github.com/repos/" + name + "/issues";
        CONTRIBUTORS_URL = "https://api.github.com/repos/" + name + "/contributors";
    }

    @Override
    public FetchData loadInBackground() {
        try {
            FetchData detailData = new FetchData(max);
            OkHttpClient client = new OkHttpClient();
            Gson gson = new Gson();

            Request.Builder builder = new Request.Builder().url(CONTRIBUTORS_URL);
            Request request = builder.build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String json = response.body().string();
                Contributor[] contributors = gson.fromJson(json, Contributor[].class);

                detailData.setContributors(contributors);
            }

            builder = new Request.Builder().url(ISSUES_URL);
            request = builder.build();
            response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String json = response.body().string();
                Issue[] issues = gson.fromJson(json, Issue[].class);

                detailData.setIssues(issues);
            }

            return detailData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
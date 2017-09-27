package com.example.jacob.repositorios.detail;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jacob.repositorios.R;
import com.example.jacob.repositorios.restFul.DetailsFetcher;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<FetchData> {

    public static final String REPO_FULL_NAME = "REPO_FULL_NAME";
    public static final String AVATAR_URL = "AVATAR_URL";
    public static final String REPO_DESC = "REPO_DESC";
    public static final String REPO_NAME = "REPO_NAME";
    private final int TASK_ID = 208350304;

    private ListView issuesListView;
    private ListView devListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        issuesListView = (ListView) findViewById(R.id.detail_issuesList);
        devListView = (ListView) findViewById(R.id.detail_collaboratorList);

        TextView nameLabel = (TextView) findViewById(R.id.detail_nameLabel);
        TextView descLabel = (TextView) findViewById(R.id.detail_description);
        ImageView imageView = (ImageView) findViewById(R.id.detail_imageView);

        Intent intent = getIntent();

        Glide.with(this)
                .load(intent.getStringExtra(AVATAR_URL))
                .placeholder(R.drawable.logo_github)
                .into(imageView);

        nameLabel.setText(intent.getStringExtra(REPO_NAME));
        descLabel.setText(intent.getStringExtra(REPO_DESC));
        setTitle("Repositorio " + getIntent().getStringExtra(REPO_NAME));
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLoaderManager().initLoader(TASK_ID, null, this).forceLoad();
    }

    @Override
    public Loader<FetchData> onCreateLoader(int id, Bundle args) {
        String fullName = getIntent().getStringExtra(REPO_FULL_NAME);
        return new DetailsFetcher(this, fullName, 3);
    }

    @Override
    public void onLoadFinished(Loader<FetchData> loader, FetchData data) {
        DevListAdapter devListAdapter = new DevListAdapter(this,
                R.layout.item_contributor, data.getContributors());
        devListView.setAdapter(devListAdapter);

        IssuesListAdapter issuesListAdapter = new IssuesListAdapter(this,
                R.layout.item_issue, data.getIssues());
        issuesListView.setAdapter(issuesListAdapter);
    }

    @Override
    public void onLoaderReset(Loader<FetchData> loader) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
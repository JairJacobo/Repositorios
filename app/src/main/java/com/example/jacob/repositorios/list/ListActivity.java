package com.example.jacob.repositorios.list;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jacob.repositorios.R;
import com.example.jacob.repositorios.detail.DetailActivity;
import com.example.jacob.repositorios.models.Item;
import com.example.jacob.repositorios.restFul.ReposFetcher;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<Item[]>, AbsListView.OnScrollListener {

    public static final String REPO_LANG = "LANGUAGE";
    private final int TASK_ID = 94125522;
    private boolean isLoading = false;
    private int page = 1;

    private ProgressBar progressBar;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        progressBar = (ProgressBar) findViewById(R.id.list_progressbar);
        listAdapter = new ListAdapter(this, R.layout.item_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        listView.setAdapter(listAdapter);

        String title = "Repositorios " + getIntent().getStringExtra(REPO_LANG);
        setTitle(title);

        getLoaderManager().restartLoader(TASK_ID, null, this).forceLoad();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = listAdapter.getItem(position);

        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra(DetailActivity.AVATAR_URL, item.getOwner().getAvatarUrl());
        intent.putExtra(DetailActivity.REPO_FULL_NAME, item.getFullName());
        intent.putExtra(DetailActivity.REPO_DESC, item.getDescription());
        intent.putExtra(DetailActivity.REPO_NAME, item.getName());

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public Loader<Item[]> onCreateLoader(int id, Bundle args) {
        String lang = getIntent().getStringExtra(REPO_LANG);
        return new ReposFetcher(this, lang, page);
    }

    @Override
    public void onLoadFinished(Loader<Item[]> loader, Item[] data) {
        isLoading = false;

        if (data != null) {
            listAdapter.addAll(data);
            progressBar.setVisibility(View.INVISIBLE);
        } else if (progressBar.getVisibility() == View.VISIBLE) {
            Toast.makeText(this, "No se encontraron resultados.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    @Override
    public void onLoaderReset(Loader<Item[]> loader) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if ((totalItemCount - (visibleItemCount + (visibleItemCount / 2)) <= firstVisibleItem)) {
            if (!isLoading) {
                page++;
                isLoading = true;
                getLoaderManager().restartLoader(TASK_ID, null, this).forceLoad();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
package com.example.jacob.repositorios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacob.repositorios.list.ListActivity;
import com.example.jacob.repositorios.utils.NetworkHelper;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private boolean networkOK;
    private TextView textView;
    private Spinner spinner;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.welcome_textView);
        spinner = (Spinner) findViewById(R.id.welcome_spinner);
        button = (Button) findViewById(R.id.welcome_button);

        spinner.setOnItemSelectedListener(this);
        button.setEnabled(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i > 0) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void performSearch(View view) {
        String language = spinner.getSelectedItem().toString();

        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ListActivity.REPO_LANG, language);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onStart() {
        super.onStart();
        networkOK = NetworkHelper.hasNetworkAccess(this);
    }
}

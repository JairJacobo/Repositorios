package com.example.jacob.repositorios.detail;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.jacob.repositorios.R;
import com.example.jacob.repositorios.models.Issue;

class IssuesListAdapter extends ArrayAdapter<Issue> {
    private final int resource;

    public IssuesListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Issue[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(resource, parent, false);
        }

        TextView tvUser = (TextView) convertView.findViewById(R.id.item_user);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.item_title);
        TextView tvUpdate = (TextView) convertView.findViewById(R.id.item_update);

        Issue item = getItem(position);

        tvUser.setText("Desarrollador: " + item.getUser().getLogin());
        tvUpdate.setText("Fecha de reporte: " + item.getUpdated_at());
        tvTitle.setText(item.getTitle());

        return convertView;
    }
}
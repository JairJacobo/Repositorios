package com.example.jacob.repositorios.detail;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jacob.repositorios.R;
import com.example.jacob.repositorios.models.Contributor;

class DevListAdapter extends ArrayAdapter<Contributor> {
    private final int resource;

    public DevListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Contributor[] objects) {
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

        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);
        TextView tvCount = (TextView) convertView.findViewById(R.id.item_count);
        TextView tvName = (TextView) convertView.findViewById(R.id.item_name);

        Contributor item = getItem(position);

        tvName.setText(String.valueOf(item.getLogin()));
        tvCount.setText("Total de contribuciones: " + String.valueOf(item.getContributions()));

        String avatarURL = item.getAvatar_url();

        Glide.with(getContext())
                .load(avatarURL)
                .placeholder(R.drawable.logo_github)
                .into(imageView);

        return convertView;
    }
}
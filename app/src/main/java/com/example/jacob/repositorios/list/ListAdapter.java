package com.example.jacob.repositorios.list;

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
import com.example.jacob.repositorios.models.Item;

class ListAdapter extends ArrayAdapter<Item> {
    private final int resource;

    public ListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
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
        TextView tvUpDate = (TextView) convertView.findViewById(R.id.item_update);
        TextView tvName = (TextView) convertView.findViewById(R.id.item_name);
        TextView tvSize = (TextView) convertView.findViewById(R.id.item_size);

        Item item = getItem(position);

        tvName.setText(item.getName());
        tvUpDate.setText(item.getUpdatedAt());
        tvSize.setText("Líneas de código: " + String.valueOf(item.getSize()));

        String avatarURL = item.getOwner().getAvatarUrl();

        Glide.with(getContext())
                .load(avatarURL)
                .placeholder(R.drawable.logo_github)
                .into(imageView);

        return convertView;
    }
}
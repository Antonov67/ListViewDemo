package com.example.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Lang> {
    public MyAdapter(@NonNull Context context, @NonNull List<Lang> objects) {
        super(context, R.layout.item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Lang lang = getItem(position);
        TextView name, age;
        ImageView imageView;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, null);
        }
        name = convertView.findViewById(R.id.name);
        age = convertView.findViewById(R.id.age);
        imageView = convertView.findViewById(R.id.imageView);

        name.setText(lang.name);
        age.setText(lang.age + "");
        imageView.setImageURI(lang.uri);

        return convertView;
    }
}

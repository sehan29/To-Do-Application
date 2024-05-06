package com.example.to_do_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewTaskName);
        TextView textViewDescription = convertView.findViewById(R.id.textViewTaskDescription);
        ImageView imageViewEdit = convertView.findViewById(R.id.imageViewEdit);
        ImageView imageViewDelete = convertView.findViewById(R.id.imageViewDelete);

        Task task = getItem(position);
        textViewName.setText(task.getName());
        textViewDescription.setText(task.getDescription());

        imageViewEdit.setOnClickListener(v -> {
            // Edit action
        });

        imageViewDelete.setOnClickListener(v -> {
            // Delete action
        });

        return convertView;
    }
}
package com.example.to_do_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AllTask extends Fragment {

    private ListView listView;
    private TaskAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_task, container, false);
        listView = view.findViewById(R.id.listViewTasks);

        MydatabaseHelper dbHelper = new MydatabaseHelper(getActivity());
        List<Task> tasks = dbHelper.getAllTasks();

        adapter = new TaskAdapter(getActivity(), tasks);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task task = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
                intent.putExtra("TaskID", Integer.parseInt(task.getId())); // Make sure IDs are properly parsed as integers
                startActivity(intent);
            }
        });

        return view;
    }


}
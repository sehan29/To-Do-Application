package com.example.to_do_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));
        tasks.add(new Task("Task 1", "Description of Task 1"));
        tasks.add(new Task("Task 2", "Description of Task 2"));

        adapter = new TaskAdapter(getActivity(), tasks);
        listView.setAdapter(adapter);

        return view;
    }

}
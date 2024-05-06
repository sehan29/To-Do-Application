package com.example.to_do_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the ListView by its ID
        ListView listView = view.findViewById(R.id.listViewTasks);

        // Sample data to display in the ListView
        String[] tasks = {"Web Application Development", "Programming II", "Flutter", "Python Tutorial", "Android Learning", "LMS Dev", "Statistics"};

        // Create an ArrayAdapter using the string array and a default list item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tasks);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }
}
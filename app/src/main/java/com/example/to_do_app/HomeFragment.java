package com.example.to_do_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListView listView;
    private ItemAdapter adapter;
    private List<Item> items;

    private  MydatabaseHelper mydatabaseHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.listViewTasks);
        mydatabaseHelper = new MydatabaseHelper(getActivity());

        items = mydatabaseHelper.getHomeTasks();

        adapter = new ItemAdapter(getActivity(), items);
        listView.setAdapter(adapter);


       // items = new ArrayList<>();
        //items.add(new Item("Web Application", "20-11-2024"));
       // items.add(new Item("Programming II", "20-11-2024"));
        //items.add(new Item("Mobile Application", "20-11-2024"));
       // items.add(new Item("Agile Methodology", "20-11-2024"));
       // items.add(new Item("Mini Project", "20-11-2024"));
        //items.add(new Item("Database Manipulation", "20-11-2024"));
        // Add more items as needed

        adapter = new ItemAdapter(getActivity(), items);
        listView.setAdapter(adapter);

        return view;
    }


}
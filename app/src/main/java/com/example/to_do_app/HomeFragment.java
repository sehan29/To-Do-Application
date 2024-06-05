package com.example.to_do_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

      //  adapter = new ItemAdapter(getActivity(), items);
      //  listView.setAdapter(adapter);


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

        TextView textViewDate = view.findViewById(R.id.textView5);
        String currentDate = new SimpleDateFormat("dd - MMMM - yyyy", Locale.getDefault()).format(new Date());
        textViewDate.setText(currentDate);

        // Set the text of the TextView with the number of scheduled tasks
        TextView textViewScheduledTasks = view.findViewById(R.id.textView6);
        int scheduledTasksCount = mydatabaseHelper.getTasksCount();
        String scheduledTasksText = "Scheduled Task - " + scheduledTasksCount;
        textViewScheduledTasks.setText(scheduledTasksText);

        return view;
    }


}
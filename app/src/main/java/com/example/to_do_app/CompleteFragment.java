package com.example.to_do_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;

public class CompleteFragment extends Fragment {

    private DatePickerDialog startDatePickerDialog, endDatePickerDialog;
    private Button startDateButton, endDateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete, container, false);

        startDateButton = view.findViewById(R.id.startDatePickerButton);
        endDateButton = view.findViewById(R.id.endDatePickerButton);

        initDatePickers();
        setInitialDate();

        startDateButton.setOnClickListener(v -> startDatePickerDialog.show());
        endDateButton.setOnClickListener(v -> endDatePickerDialog.show());

        return view;
    }

    private void setInitialDate() {
        String todayDate = getTodaysDate();
        startDateButton.setText(todayDate);
        endDateButton.setText(todayDate);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePickers() {
        DatePickerDialog.OnDateSetListener startDateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String date = makeDateString(dayOfMonth, month, year);
            startDateButton.setText(date);
        };

        DatePickerDialog.OnDateSetListener endDateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String date = makeDateString(dayOfMonth, month, year);
            endDateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        startDatePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, startDateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, endDateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1: return "JAN";
            case 2: return "FEB";
            case 3: return "MAR";
            case 4: return "APR";
            case 5: return "MAY";
            case 6: return "JUN";
            case 7: return "JUL";
            case 8: return "AUG";
            case 9: return "SEP";
            case 10: return "OCT";
            case 11: return "NOV";
            case 12: return "DEC";
            default: return "JAN"; // Fallback to January
        }
    }
}

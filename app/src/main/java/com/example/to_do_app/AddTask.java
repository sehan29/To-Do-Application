package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    private DatePickerDialog startDatePickerDialog, endDatePickerDialog;
    private Button startDateButton, endDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initDatePickers();
        startDateButton = findViewById(R.id.startDatePickerButton);
        endDateButton = findViewById(R.id.endDatePickerButton);

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

        startDatePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, startDateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, endDateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    public void openStartDatePicker(View view) {
        startDatePickerDialog.show();
    }

    public void openEndDatePicker(View view) {
        endDatePickerDialog.show();
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
            default: return "JAN"; // Should never happen
        }
    }
}

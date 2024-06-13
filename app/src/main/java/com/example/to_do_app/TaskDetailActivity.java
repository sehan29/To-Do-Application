package com.example.to_do_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textViewTitle = findViewById(R.id.textViewTaskTitle);
        TextView textViewDescription = findViewById(R.id.textViewTaskDescription);
        Button buttonEdit = findViewById(R.id.buttonEditTask);
        Button buttonDelete = findViewById(R.id.buttonDeleteTask);

        int taskId = getIntent().getIntExtra("TaskID", -1);
        MydatabaseHelper dbHelper = new MydatabaseHelper(this);
        Task task = dbHelper.getTask(taskId);  // Implement this method in MydatabaseHelper

        textViewTitle.setText(task.getName());
        textViewDescription.setText(task.getDescription());

        buttonEdit.setOnClickListener(v -> {
            // Code to edit task
            Intent intent = new Intent(TaskDetailActivity.this, EditTaskActivity.class);
            intent.putExtra("TaskID", taskId);
            startActivity(intent);
        });

        buttonDelete.setOnClickListener(v -> {
            dbHelper.deleteTask(taskId);  // Implement this method in MydatabaseHelper
            Toast.makeText(getApplicationContext(), "Task Deleted", Toast.LENGTH_SHORT).show();
            finish();  // Close this activity and return
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTaskData();
    }

    private void loadTaskData() {
        int taskId = getIntent().getIntExtra("TaskID", -1);
        MydatabaseHelper dbHelper = new MydatabaseHelper(this);
        Task task = dbHelper.getTask(taskId);
        if (task != null) {
            TextView textViewTitle = findViewById(R.id.textViewTaskTitle);
            TextView textViewDescription = findViewById(R.id.textViewTaskDescription);
            textViewTitle.setText(task.getName());
            textViewDescription.setText(task.getDescription());
        } else {
            Toast.makeText(this, "Error: Task not found!", Toast.LENGTH_LONG).show();
        }
    }


}
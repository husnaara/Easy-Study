//package com.example.easystudy;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class addTaskActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_add_task);
//
//    }
//}
package com.example.easystudy;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easystudy.DatabaseHelper;

import java.util.List;

// addTaskActivity.java
public class addTaskActivity extends AppCompatActivity {

    EditText editTextTask;
    Button buttonAddTask;
    LinearLayout taskListContainer;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        taskListContainer = findViewById(R.id.taskListContainer);

        databaseHelper = new DatabaseHelper(this);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    boolean inserted = databaseHelper.insertTask(task);
                    if (inserted) {
                        // Clear EditText after adding task
                        editTextTask.setText("");
                        // Optionally, update UI to display added task
                        displayTasks();
                    } else {
                        Toast.makeText(addTaskActivity.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addTaskActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void displayTasks() {
        // Clear existing views in taskListContainer before adding new ones
        taskListContainer.removeAllViews();

        // Retrieve tasks from database
        List<String> tasks = databaseHelper.getAllTasks();

        // Add retrieved tasks to LinearLayout dynamically
        for (String task : tasks) {
            TextView textView = new TextView(this);
            textView.setText(task);
            textView.setTextSize(18);
            taskListContainer.addView(textView);
        }
    }
}



//package com.example.easystudy;
//
//import android.os.Bundle;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import java.util.List;
//
////public class showTaskActivity extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////        setContentView(R.layout.activity_show_task);
////
////    }
////}
//
//// showTaskActivity.java
//public class showTaskActivity extends AppCompatActivity {
//
//    LinearLayout taskListContainer;
//    DatabaseHelper databaseHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_task);
//
//        taskListContainer = findViewById(R.id.taskListContainer);
//        databaseHelper = new DatabaseHelper(this);
//
//        displayTasks();
//    }
//
//    private void displayTasks() {
//        // Clear existing views in taskListContainer before adding new ones
//        taskListContainer.removeAllViews();
//
//        // Retrieve tasks from database
//        List<String> tasks = databaseHelper.getAllTasks();
//
//        // Add retrieved tasks to LinearLayout dynamically
//        for (String task : tasks) {
//            TextView textView = new TextView(this);
//            textView.setText(task);
//            textView.setTextSize(18);
//            taskListContainer.addView(textView);
//        }
//    }
//}
package com.example.easystudy;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class showTaskActivity extends AppCompatActivity {

    LinearLayout taskListContainer;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        taskListContainer = findViewById(R.id.taskListContainer);
        databaseHelper = new DatabaseHelper(this);

        displayTasks();
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

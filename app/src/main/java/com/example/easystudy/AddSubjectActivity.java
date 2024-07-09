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
//public class AddSubjectActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_subject);
//
//        }
//    }

package com.example.easystudy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSubjectActivity extends AppCompatActivity {

    private EditText subjectNameEditText;
    private EditText subjectCodeEditText;
    private EditText subjectDescriptionEditText;
    private Button addSubjectButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        subjectNameEditText = findViewById(R.id.subject_name);
        subjectCodeEditText = findViewById(R.id.subject_code);
        subjectDescriptionEditText = findViewById(R.id.subject_description);
        addSubjectButton = findViewById(R.id.add_subject_button);

        databaseHelper = new DatabaseHelper(this);

        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubject();
            }
        });
    }

    private void addSubject() {
        String subjectName = subjectNameEditText.getText().toString().trim();
        String subjectCode = subjectCodeEditText.getText().toString().trim();
        String subjectDescription = subjectDescriptionEditText.getText().toString().trim();

        if (TextUtils.isEmpty(subjectName) || TextUtils.isEmpty(subjectCode) || TextUtils.isEmpty(subjectDescription)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean isInserted = databaseHelper.insertSubject(subjectName, subjectCode, subjectDescription);
            if (isInserted) {
                Toast.makeText(this, "Subject added successfully", Toast.LENGTH_SHORT).show();
                // Clear the input fields
                subjectNameEditText.setText("");
                subjectCodeEditText.setText("");
                subjectDescriptionEditText.setText("");
            } else {
                Toast.makeText(this, "Failed to add subject", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

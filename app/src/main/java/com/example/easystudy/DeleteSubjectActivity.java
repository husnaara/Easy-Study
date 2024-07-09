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
//public class DeleteSubjectActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_delete_subject);
//
//    }
//}

package com.example.easystudy;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteSubjectActivity extends AppCompatActivity {

    private EditText subjectNameEditText;
    private Button deleteButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_subject);

        databaseHelper = new DatabaseHelper(this);

        subjectNameEditText = findViewById(R.id.subjectNameEditText);
        deleteButton = findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectName = subjectNameEditText.getText().toString().trim();
                if (!subjectName.isEmpty()) {
                    boolean result = deleteSubject(subjectName);
                    if (result) {
                        Toast.makeText(DeleteSubjectActivity.this, "Subject deleted successfully", Toast.LENGTH_SHORT).show();
                        subjectNameEditText.setText("");
                    } else {
                        Toast.makeText(DeleteSubjectActivity.this, "Subject not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DeleteSubjectActivity.this, "Please enter a subject name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean deleteSubject(String subjectName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int rowsDeleted = db.delete(DatabaseHelper.TABLE_SUBJECT, DatabaseHelper.COL_SUBJECT_NAME + " = ?", new String[]{subjectName});
        return rowsDeleted > 0;
    }
}

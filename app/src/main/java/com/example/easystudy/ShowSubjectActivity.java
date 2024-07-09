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
//public class showSubjectActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_show_subject);
//
//    }
//}

package com.example.easystudy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSubjectActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView subjectNameTextView, subjectCodeTextView, subjectDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject);

        databaseHelper = new DatabaseHelper(this);

        subjectNameTextView = findViewById(R.id.subject_name);
        subjectCodeTextView = findViewById(R.id.subject_code);
        subjectDescriptionTextView = findViewById(R.id.subject_description);

        retrieveSubjectData();
    }

    private void retrieveSubjectData() {
        // For demonstration, we're fetching the subject with ID = 1
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_SUBJECT + " WHERE " + DatabaseHelper.COL_ID + " = 1", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int subjectNameIndex = cursor.getColumnIndex(DatabaseHelper.COL_SUBJECT_NAME);
                int subjectCodeIndex = cursor.getColumnIndex(DatabaseHelper.COL_SUBJECT_CODE);
                int subjectDescriptionIndex = cursor.getColumnIndex(DatabaseHelper.COL_SUBJECT_DESCRIPTION);

                if (subjectNameIndex != -1 && subjectCodeIndex != -1 && subjectDescriptionIndex != -1) {
                    String subjectName = cursor.getString(subjectNameIndex);
                    String subjectCode = cursor.getString(subjectCodeIndex);
                    String subjectDescription = cursor.getString(subjectDescriptionIndex);

                    subjectNameTextView.setText("Subject Name: " + subjectName);
                    subjectCodeTextView.setText("Subject Code: " + subjectCode);
                    subjectDescriptionTextView.setText("Subject Description: " + subjectDescription);
                }
            }
            cursor.close();
        }
    }
}


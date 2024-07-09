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
//public class showNoteActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_show_note);
//
//    }
//}

package com.example.easystudy;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class showNoteActivity extends AppCompatActivity {

    private LinearLayout taskListContainer;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        dbHelper = new DatabaseHelper(this);
        taskListContainer = findViewById(R.id.taskListContainer);

        loadNotesFromDatabase();
    }

    private void loadNotesFromDatabase() {
        Cursor cursor = dbHelper.getAllNotes();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Notes Found", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String noteContent = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTE_CONTENT));
            addNoteToView(noteContent);
        }
        cursor.close();
    }

    private void addNoteToView(String noteContent) {
        EditText noteEditText = new EditText(this);
        noteEditText.setText(noteContent);
        noteEditText.setEnabled(false);
        noteEditText.setBackgroundResource(R.drawable.bg_edit_text);
        noteEditText.setPadding(10, 10, 10, 10);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 16);
        noteEditText.setLayoutParams(params);

        taskListContainer.addView(noteEditText);
    }
}

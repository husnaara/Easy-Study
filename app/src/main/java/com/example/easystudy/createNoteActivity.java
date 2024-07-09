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
//public class createNoteActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_create_note);
//
//    }
//}
package com.example.easystudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class createNoteActivity extends AppCompatActivity {

    private EditText notesEditText;
    private Button saveNotesButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dbHelper = new DatabaseHelper(this);

        notesEditText = findViewById(R.id.notesEditText);
        saveNotesButton = findViewById(R.id.saveNotesButton);

        saveNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteContent = notesEditText.getText().toString();
                saveNoteToDatabase(noteContent);
            }
        });
    }

    private void saveNoteToDatabase(String noteContent) {
        boolean isInserted = dbHelper.insertNote(noteContent);

        if (isInserted) {
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
            notesEditText.setText("");
        } else {
            Toast.makeText(this, "Failed to Save Note", Toast.LENGTH_SHORT).show();
        }
    }
}

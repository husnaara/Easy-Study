package com.example.easystudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomePageActivity extends AppCompatActivity {
    private CardView userprofileCard, addSubjectCard, deleteSubjectCard,  showSubjectCard , addTaskCard, showTaskCard, createNoteCard, showNoteCard,  setAlarmCard, logoutCard;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);


        userprofileCard = findViewById(R.id.userprofileCard);
        addSubjectCard = findViewById(R.id.addSubjectCard);
        deleteSubjectCard = findViewById(R.id.deleteSubjectCard);
        showSubjectCard = findViewById(R.id.showSubjectCard);
        addTaskCard = findViewById(R.id.addTaskCard);
        showTaskCard = findViewById(R.id.showTaskCard);
        createNoteCard = findViewById(R.id.createNoteCard);
        showNoteCard = findViewById(R.id.showNoteCard);
        setAlarmCard = findViewById(R.id.setAlarmCard);
        logoutCard = findViewById(R.id.logoutCard);

        // Set onClickListeners for each CardView
        userprofileCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });

        addSubjectCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, AddSubjectActivity.class);
            startActivity(intent);
        });

        deleteSubjectCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, DeleteSubjectActivity.class);
            startActivity(intent);
        });

        showSubjectCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this,ShowSubjectActivity.class);
            startActivity(intent);
        });

        addTaskCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this,addTaskActivity.class);
            startActivity(intent);
        });

        showTaskCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, showTaskActivity.class);
            startActivity(intent);
        });

       createNoteCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, createNoteActivity.class);
            startActivity(intent);
        });


        showNoteCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, showNoteActivity.class);
            startActivity(intent);
        });

        setAlarmCard.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, SetAlarmActivity.class);
            startActivity(intent);
        });

        logoutCard.setOnClickListener(view -> {
            // Implement logout functionality
            // For example, clear user data and redirect to login screen
            // SharedPreferences.Editor editor = getSharedPreferences("user_prefs", MODE_PRIVATE).edit();
            // editor.clear();
            // editor.apply();
            Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
   }
}

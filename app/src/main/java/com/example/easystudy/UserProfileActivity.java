
package com.example.easystudy;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserProfileActivity extends AppCompatActivity {

    private EditText etUserName, etEmail, etAge;
//    private ImageView ivSelectedImage;
//    private Button btnSelectImage;
    ImageView imageView;
    Button btnPickImage ;
    ActivityResultLauncher<Intent>resultLauncher;
    private DatabaseHelper databaseHelper;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize UI components
        etUserName = findViewById(R.id.et_user_name);
        etEmail = findViewById(R.id.et_email);
        etAge = findViewById(R.id.et_age);
//        ivSelectedImage = findViewById(R.id.iv_selected_image);
//        btnSelectImage = findViewById(R.id.btn_select_image);
        imageView=findViewById(R.id.imageView);
        btnPickImage=findViewById(R.id.btnPickImage);
        resisterResult();
        btnPickImage.setOnClickListener(view->pickImage());

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Retrieve user data and set to views
        retrieveUserData();
    }
    private void pickImage(){
        Intent intent=new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);

    }
    private void resisterResult(){
        resultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageuri=result.getData().getData();
                            imageView.setImageURI(imageuri);
                        }
                        catch (Exception e){
                            Toast.makeText(UserProfileActivity.this, "No selected image", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }

    private void retrieveUserData() {
        // Retrieve the user data from the database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_REGISTER + " WHERE " + DatabaseHelper.COL_ID + " = 1", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int userNameIndex = cursor.getColumnIndex(DatabaseHelper.COL_USERNAME);
                int emailIndex = cursor.getColumnIndex(DatabaseHelper.COL_EMAIL);
                int ageIndex = cursor.getColumnIndex(DatabaseHelper.COL_AGE);

                if (userNameIndex != -1 && emailIndex != -1 && ageIndex != -1) {
                    String userName = cursor.getString(userNameIndex);
                    String email = cursor.getString(emailIndex);
                    int age = cursor.getInt(ageIndex);

                    // Set the user data to the views
                    etUserName.setText(userName);
                    etEmail.setText(email);
                    etAge.setText(String.valueOf(age));
                }
            }
            cursor.close();
        }
    }

}






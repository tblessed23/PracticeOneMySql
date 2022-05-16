package com.msbs.android.practiceonemysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Annotation;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    // creating variables for our edit text
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;

    // creating variable for button
    private Button submitCourseBtn;

    // creating a strings for storing our values from edittext fields.
    private String courseName, courseDuration, courseDescription;

    RegisterAPI service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = RetrofitClient.getRetrofitInstance().create(RegisterAPI.class);

        // initializing our edittext and buttons
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        submitCourseBtn = findViewById(R.id.idBtnSubmitCourse);
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to add data to Firebase Firestore.
                try{
                    insertUser(courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), courseDurationEdt.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(courseName)) {
                    courseNameEdt.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(courseDescription)) {
                    courseDescriptionEdt.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(courseDuration)) {
                    courseDurationEdt.setError("Please enter Course Duration");
                }
            }
        });
    }


    public void insertUser(String courseName, String courseDescription, String courseDuration) throws IOException {

        Call<String> call = service.getStringScalar(courseName, courseDescription, courseDuration);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void openLaurynList(View view) {

        // Start the activity connect to the
        // specified class

        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}
package com.msbs.android.practiceonemysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    // creating variables for our edit text
    private EditText courseIDEdt;

    // creating variable for button
    private Button getCourseDetailsBtn;

    // creating variable for card view and text views.
    private CardView courseCV;
    private TextView courseNameTV, courseDescTV, courseDurationTV;

    RegisterAPI service2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        service2 = RetrofitClient.getRetrofitInstance().create(RegisterAPI.class);

        // initializing all our variables.
        courseNameTV = findViewById(R.id.idTVCourseName);
        courseDescTV = findViewById(R.id.idTVCourseDescription);
        courseDurationTV = findViewById(R.id.idTVCourseDuration);
        getCourseDetailsBtn = findViewById(R.id.idBtnGetCourse);
        courseIDEdt = findViewById(R.id.idEdtCourseId);
        courseCV = findViewById(R.id.idCVCOurseItem);

        // adding click listener for our button.
        getCourseDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the id text field is empty or not.
                if (TextUtils.isEmpty(courseIDEdt.getText().toString())) {
                    Toast.makeText(MainActivity2.this, "Please enter course id", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling method to load data.
                getCourseDetails(courseIDEdt.getText().toString());
            }
        });
    }


    private void getCourseDetails(String id) {

        Call<String> call = service2.readStringScalar(id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    // on below line passing our response to json object.
                    JSONObject jsonObject = new JSONObject(response.body());
                    // on below line we are checking if the response is null or not.
                    jsonObject.getString("courseName");
                    // if we get the data then we are setting it in our text views in below line.
                    courseNameTV.setText(jsonObject.getString("courseName"));
                    courseDescTV.setText(jsonObject.getString("courseDescription"));
                    courseDurationTV.setText(jsonObject.getString("courseDuration"));
                    courseCV.setVisibility(View.VISIBLE);
                    // on below line we are displaying
                    // a success toast message.
                } catch (JSONException e) {
                    e.printStackTrace();
                }


               courseCV.setVisibility(View.VISIBLE);

                Toast.makeText(MainActivity2.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(MainActivity2.this, "Error" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }}

//        // on below line we are calling a string
//        // request method to post the data to our API
//        // in this we are calling a post method.
//        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    // on below line passing our response to json object.
//                    JSONObject jsonObject = new JSONObject(response);
//                    // on below line we are checking if the response is null or not.
//                    if (jsonObject.getString("courseName") == null) {
//                        // displaying a toast message if we get error
//                        Toast.makeText(MainActivity.this, "Please enter valid id.", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // if we get the data then we are setting it in our text views in below line.
//                        courseNameTV.setText(jsonObject.getString("courseName"));
//                        courseDescTV.setText(jsonObject.getString("courseDescription"));
//                        courseDurationTV.setText(jsonObject.getString("courseDuration"));
//                        courseCV.setVisibility(View.VISIBLE);
//                    }
//                    // on below line we are displaying
//                    // a success toast message.
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // method to handle errors.
//                Toast.makeText(MainActivity.this, "Fail to get course" + error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            public String getBodyContentType() {
//                // as we are passing data in the form of url encoded
//                // so we are passing the content type below
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//
//            @Override
//            protected Map<String, String> getParams() {
//
//                // below line we are creating a map for storing our values in key and value pair.
//                Map<String, String> params = new HashMap<String, String>();
//
//                // on below line we are passing our key and value pair to our parameters.
//                params.put("id", courseId);
//
//                // at last we are returning our params.
//                return params;
//            }
//        };
//        // below line is to make
//        // a json object request.
//        queue.add(request);
//    }


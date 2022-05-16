package com.msbs.android.practiceonemysql;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("addCourses.php")
    Call<String> getStringScalar(@Field("courseName") String courseName,
                               @Field("courseDescription") String courseDescription,
                               @Field("courseDuration") String courseDuration);

    @FormUrlEncoded
    @POST("readCourses.php")
    Call<String> readStringScalar(@Field("id") String id);
    //PATH is not the answer

}

package com.msbs.android.practiceonemysql;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.0.176:8080/courseApp/";

    //IPv4 address 192.168.0.176
    // http://192.168.0.176:8080/customerApp/addCustomer.php worka in Postman
    //Works in Emulator http://10.0.2.2:8080/customerApp/


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

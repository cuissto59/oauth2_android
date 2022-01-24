package com.example.oauth2;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.google.android.gms.common.util.CollectionUtils.listOf;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL="https://localhost:8089/api/v1/users/";
    private static final OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                     .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

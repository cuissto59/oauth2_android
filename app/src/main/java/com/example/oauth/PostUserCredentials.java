package com.example.oauth;

import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PostUserCredentials{

    @POST("signup")
    Call<String> createUser( @Body POJO user );

}

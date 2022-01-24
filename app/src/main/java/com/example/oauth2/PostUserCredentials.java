package com.example.oauth2;

import retrofit2.Call;
import retrofit2.http.*;

public interface PostUserCredentials{

    @GET("")
    Call<String> createUser( @Body POJO user );

}

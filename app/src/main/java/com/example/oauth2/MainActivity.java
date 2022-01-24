package com.example.oauth2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity  {
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.sign_in_button);
        signin.setSize(SignInButton.SIZE_STANDARD);
        signin.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.sign_in_button:
                    signIn();
                    break;
                // ...
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityIfNeeded(signInIntent,RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("result code :" + resultCode);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String authCode = account.getIdToken();
            System.out.println("msg :" + authCode );
            POJO user = new POJO(authCode);
            System.out.println("msg :" + user.toString());
            // Signed in successfully, show authenticated UI.
            // send ID token to server
            //JsonObject requestBody = new JsonObject();
            //requestBody.addProperty("idToken",authCode);
            //Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
            //PostUserCredentials postUserCredentials = retrofit.create(PostUserCredentials.class);
            //Call<String> call = postUserCredentials.createUser(user);
            //call.enqueue(new Callback<String>() {
              //  @Override
               // public void onResponse(Call<String> call, Response<String> response) {
                //    Toast.makeText(MainActivity.this,"yes it worked :"+response,Toast.LENGTH_SHORT).show();
                 //  Log.w( "Body","signInBody = " + response.body());
              //  }

                //@Override
               // public void onFailure(Call<String> call, Throwable t) {
                //    Toast.makeText(MainActivity.this,"Somthing wrong" + t.getMessage(),Toast.LENGTH_SHORT).show();
             //  }
            //});



            Intent intent =  new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w( "Error","signInResult:failed code=" + e.getStatusCode());

        }
    }


}
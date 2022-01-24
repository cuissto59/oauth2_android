package com.example.oauth2;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import static android.app.PendingIntent.getActivity;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,email, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textName);
        email= findViewById(R.id.textEmail);
        id = findViewById(R.id.textId);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SecondActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getIdToken();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            Glide.with(this).load(String.valueOf(personPhoto)).apply(new RequestOptions().override(600,200)).into(imageView);



        }
        else
        {
            System.out.println("msg : no acc");

        }

        }

}
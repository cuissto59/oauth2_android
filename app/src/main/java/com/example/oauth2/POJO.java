package com.example.oauth2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class POJO implements Serializable {
    @SerializedName("idToken")
    @Expose
    private String itToken;

    public POJO(String itToken) {
        this.itToken = itToken;
    }

    @Override
    public String toString() {
        return "POJO{" +
                "idToken='" + itToken + '\'' +
                '}';
    }

    public String getItToken() {
        return itToken;
    }

    public void setItToken(String itToken) {
        this.itToken = itToken;
    }
}

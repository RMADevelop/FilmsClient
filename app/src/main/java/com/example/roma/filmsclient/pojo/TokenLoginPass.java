package com.example.roma.filmsclient.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenLoginPass {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("request_token")
    @Expose
    private String requestToken;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

}
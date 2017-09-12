package com.example.roma.filmsclient.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Roma on 10.09.2017.
 */

public class TokenRequest {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;
    @SerializedName("request_token")
    @Expose
    private String requestToken;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}

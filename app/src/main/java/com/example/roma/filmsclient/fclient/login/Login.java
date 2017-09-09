package com.example.roma.filmsclient.fclient.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.roma.filmsclient.R;

public class Login extends AppCompatActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}

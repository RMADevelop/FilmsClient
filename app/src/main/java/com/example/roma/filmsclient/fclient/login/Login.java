package com.example.roma.filmsclient.fclient.login;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.main.MainScreen;
import com.example.roma.filmsclient.utils.Injection;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

public class Login extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;

    private EditText login;

    private EditText password;

    private ProgressBar progress;

    private Button sentAuthToken;

    private ImageView successState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        presenter = new LoginPresenter(this, Injection.provideRepository(this));

        initAuthField();
    }

    private void initAuthField() {
        password = (EditText) findViewById(R.id.password);
        login = (EditText) findViewById(R.id.login);

        progress = (ProgressBar) findViewById(R.id.progress_login);
        successState = (ImageView) findViewById(R.id.success_image);

        sentAuthToken = (Button) findViewById(R.id.send_auth_button);
        sentAuthToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendTokenAndStartSession(login.getText().toString(), password.getText().toString());
            }
        });


        presenter.obsLoginPassword(RxTextView.textChanges(login),
                RxTextView.textChanges(password));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setStateButton(boolean state) {
        sentAuthToken.setEnabled(state);
    }

    @Override
    public void setVisionButton(boolean state) {
        sentAuthToken.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setVisionProgress(boolean state) {
        progress.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setVisionSuccesState(boolean state) {
        successState.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setStateSuccesState(boolean state) {
        successState.setImageResource(state ? R.mipmap.ic_success_ok : R.mipmap.ic_success_error);
    }

    @Override
    public void startActivity() {
        final Intent intent = new Intent(this,MainScreen.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        },1500);
    }


}

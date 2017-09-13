package com.example.roma.filmsclient.fclient.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.utils.Injection;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

public class Login extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;

    private EditText login;

    private EditText password;

    private ProgressBar progress;

    private Button sentAuthToken;

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
        sentAuthToken = (Button) findViewById(R.id.send_auth_button);
        sentAuthToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.v("asdasd","login pass " + login.getText().toString()+"   " + password.getText().toString());
            }
        });

        presenter.obsSendButton(RxView.clicks(sentAuthToken));

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
    public void setStateProgress(boolean state) {
        progress.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public String getPass() {
        return password.getText().toString();
    }

    @Override
    public String getLogin() {
        return login.getText().toString();
    }
}

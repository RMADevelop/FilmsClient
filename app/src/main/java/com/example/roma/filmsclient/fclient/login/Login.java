package com.example.roma.filmsclient.fclient.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.utils.Injection;
import com.jakewharton.rxbinding2.widget.RxTextView;

public class Login extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;

    private EditText login;

    private EditText password;

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
        sentAuthToken = (Button) findViewById(R.id.send_auth_button);

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
}

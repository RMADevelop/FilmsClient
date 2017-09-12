package com.example.roma.filmsclient.fclient.login;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.jakewharton.rxbinding2.InitialValueObservable;

import io.reactivex.Observable;


public class LoginContract {
    interface View extends BaseView<Presenter> {

        void setStateButton(boolean state);

        void setStateProgress(boolean state);
    }

    interface Presenter extends BasePresenter {

        void obsLoginPassword(InitialValueObservable<CharSequence> login,
                              InitialValueObservable<CharSequence> pass);

        void obsSendButton(Observable<Object> btn, String login, String pass);

        void sendToken(String login, String pass);
    }
}

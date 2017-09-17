package com.example.roma.filmsclient.fclient.login;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.jakewharton.rxbinding2.InitialValueObservable;

import io.reactivex.Observable;


public class LoginContract {
    interface View extends BaseView<Presenter> {

        void setStateButton(boolean state);

        void setVisionButton(boolean state);

        void setVisionProgress(boolean state);

        void setVisionSuccesState(boolean state);

        void setStateSuccesState(boolean state);

        void startActivity();

    }

    interface Presenter extends BasePresenter {


        void obsLoginPassword(InitialValueObservable<CharSequence> login,
                              InitialValueObservable<CharSequence> pass);


        void sendTokenAndStartSession(String login, String pass);

    }
}

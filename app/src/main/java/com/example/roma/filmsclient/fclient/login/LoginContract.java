package com.example.roma.filmsclient.fclient.login;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import io.reactivex.Observable;


public class LoginContract {
    interface View extends BaseView<Presenter> {
        void setStateButton(boolean state);
    }

    interface Presenter extends BasePresenter {

        void obsLoginPassword(InitialValueObservable<CharSequence> login,
                              InitialValueObservable<CharSequence> pass);

//        void obsLogin(InitialValueObservable<TextViewTextChangeEvent> obs);
//
//        void obsPass(InitialValueObservable<TextViewTextChangeEvent> obs);
    }
}

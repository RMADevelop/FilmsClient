package com.example.roma.filmsclient.fclient.login;


import com.example.roma.filmsclient.data.source.Repository;
import com.jakewharton.rxbinding2.InitialValueObservable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;

    Repository repository;

    public LoginPresenter(LoginContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


//    Observable<CharSequence> loginObservable = RxTextView.textChanges(mLogin);
//    Observable<CharSequence> passwordObservable = RxTextView.textChanges(mPassword);
//    Observable<Boolean> combinedObservables = Observable.combineLatest(loginObservable, passwordObservable, (o1, o2) -> isValidLogin(o1) && isValidPassword(o2));

//
//    Observable<CharSequence> loginObservable = RxTextView.textChanges(mLogin);
//
//    Observable<CharSequence> passwordObservable = RxTextView.textChanges(mPassword);
//
//    Observable<Boolean> combinedObservables = Observable.combineLatest(loginObservable, passwordObservable, (o1, o2) -> isValidLogin(o1) && isValidPassword(o2));
//    The first and the

    @Override
    public void obsLoginPassword(InitialValueObservable<CharSequence> login, InitialValueObservable<CharSequence> pass) {
        Observable<Boolean> combine = Observable.combineLatest(login,pass,(ob1,ob2) -> ob1.length()>3 && ob2.length()>3);
        combine.subscribe(aBoolean -> {
            view.setStateButton(aBoolean);
        });
    }
}

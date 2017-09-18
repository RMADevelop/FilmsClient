package com.example.roma.filmsclient.fclient.login;


import android.util.Log;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.SessionId;
import com.example.roma.filmsclient.pojo.TokenLoginPass;
import com.example.roma.filmsclient.pojo.TokenRequest;
import com.example.roma.filmsclient.retrofit.Request;
import com.jakewharton.rxbinding2.InitialValueObservable;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.roma.filmsclient.utils.Const.API_v3;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;

    Repository repository;

    private CompositeDisposable disposables = new CompositeDisposable();

    String loginS;

    String passS;

    public LoginPresenter(LoginContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }


    @Override
    public void obsLoginPassword(final InitialValueObservable<CharSequence> login, InitialValueObservable<CharSequence> pass) {
        Observable<Boolean> combine = Observable.combineLatest(login,
                pass, new BiFunction<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2) throws Exception {
                        Log.v("sadfdgsdfg", "login pass " + login.toString());
                        loginS = charSequence.toString();
                        passS = charSequence2.toString();
                        return charSequence.length() > 3 && charSequence2.length() > 3;
                    }
                });
        combine.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                view.setStateButton(aBoolean);
            }
        });
    }

    @Override
    public void sendTokenAndStartSession(final String login, final String pass) {

        view.setVisionProgress(true);
        view.setVisionButton(false);

        disposables.add(Request.getRequestToken(API_v3)
                .map(new Function<TokenRequest, String>() {
                    @Override
                    public String apply(@NonNull TokenRequest tokenRequest) throws Exception {
                        return tokenRequest.getRequestToken();
                    }
                })
                .flatMap(new Function<String, SingleSource<TokenLoginPass>>() {
                    @Override
                    public SingleSource<TokenLoginPass> apply(@NonNull String s) throws Exception {
                        return Request.getTokenLoginPass(API_v3, login, pass, s);
                    }
                })
                .map(new Function<TokenLoginPass, String>() {
                    @Override
                    public String apply(@NonNull TokenLoginPass tokenLoginPass) throws Exception {
                        return tokenLoginPass.getRequestToken();
                    }
                })
                .flatMap(new Function<String, SingleSource<SessionId>>() {
                    @Override
                    public SingleSource<SessionId> apply(@NonNull String s) throws Exception {
                        return Request.createSessionId(API_v3, s);
                    }
                })

                .flatMapCompletable(new Function<SessionId, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull final SessionId s) throws Exception {
                        Log.v("sdasda", "session " + s.getSessionId());

                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                repository.saveSessionId(s);
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.setVisionProgress(false);
                        view.setVisionSuccesState(true);
                    }
                })
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.setStateSuccesState(true);
                        view.startActivity();
                        Log.v("sdasda", "onComplete");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.setStateSuccesState(false);

                    }
                }));
    }
}

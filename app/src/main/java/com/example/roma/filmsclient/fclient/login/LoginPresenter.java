package com.example.roma.filmsclient.fclient.login;


import android.util.Log;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.pojo.TokenLoginPass;
import com.example.roma.filmsclient.pojo.TokenRequest;
import com.example.roma.filmsclient.retrofit.Request;
import com.jakewharton.rxbinding2.InitialValueObservable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.roma.filmsclient.utils.Const.API_v3;

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


    @Override
    public void obsLoginPassword(InitialValueObservable<CharSequence> login, InitialValueObservable<CharSequence> pass) {
        Observable<Boolean> combine = Observable.combineLatest(login,
                pass, new BiFunction<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2) throws Exception {
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
    public void obsSendButton(Observable<Object> btn, final String login, final String pass) {
//        btn.map(new Function<Object, Object>() {
//            @Override
//            public Object apply(@NonNull Object o) throws Exception {
//                Log.v("fssdsdfsd", "sfsdfsdfsdfs");
//                return o.hashCode();
//            }
//        }).subscribe();
        btn
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(@NonNull Object o) throws Exception {
                        Log.v("fssdsdfsd", "map");

                        return o;
                    }
                })
                .flatMapSingle(new Function<Object, Single<TokenRequest>>() {
                    @Override
                    public Single<TokenRequest> apply(@NonNull Object o) throws Exception {
                        Log.v("fssdsdfsd", "requestToken");

                        return Request.getRequestToken(API_v3);
                    }
                })
                .map(new Function<TokenRequest, String>() {
                    @Override
                    public String apply(@NonNull TokenRequest tokenRequest) throws Exception {
                        Log.v("fssdsdfsd", "request token " + tokenRequest.getRequestToken());

                        return tokenRequest.getRequestToken();
                    }
                })
                .flatMapSingle(new Function<String, Single<TokenLoginPass>>() {
                    @Override
                    public Single<TokenLoginPass> apply(@NonNull String s) throws Exception {
                        Log.v("fssdsdfsd", "request token " + s + " login " + login + " pass " + pass);

                        return Request.getTokenLoginPass(API_v3, login, pass, s);
                    }
                })

                .subscribe(new Consumer<TokenLoginPass>() {
                    @Override
                    public void accept(@NonNull TokenLoginPass tokenLoginPass) throws Exception {
                        Log.v("fssdsdfsd", "token loginPass " + tokenLoginPass.getRequestToken());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void sendToken(String login, String pass) {

    }
}

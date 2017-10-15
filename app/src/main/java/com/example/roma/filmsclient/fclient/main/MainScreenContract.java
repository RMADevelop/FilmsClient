package com.example.roma.filmsclient.fclient.main;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

/**
 * Created by Roma on 17.09.2017.
 */

public interface MainScreenContract {
    interface View extends BaseView<Presenter> {

        void showPremiers();

        void showMain();

        void showFilmsList(String type);

    }

    interface Presenter extends BasePresenter {

        void setFilmsList();

        void setFilmsList(String type);

        void setMain();


    }
}

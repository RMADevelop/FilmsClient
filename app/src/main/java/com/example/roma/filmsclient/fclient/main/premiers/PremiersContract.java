package com.example.roma.filmsclient.fclient.main.premiers;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;


public interface PremiersContract {

    interface View extends BaseView<Presenter> {

        void showMovies(List<Result> list);
    }

    interface Presenter extends BasePresenter {

        void loadMovies();

        void showFilmActivity(int id);
    }

    interface AdapterListener {

        void filmClick(int id);
    }

    interface FragmentListener {

        void startActivity(int id);
    }
}

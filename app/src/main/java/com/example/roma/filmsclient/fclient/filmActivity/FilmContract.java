package com.example.roma.filmsclient.fclient.filmActivity;

import com.bumptech.glide.ListPreloader;
import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

import java.util.List;

/**
 * Created by Roma on 26.09.2017.
 */

public interface FilmContract {
    interface View extends BaseView<Presenter> {

        void setVisibleView(boolean state);

        void showMovie(FilmDetail film);

        void startActivity(int id);
    }

    interface Presenter extends BasePresenter {
        void loadFilm(int id);


    }


}

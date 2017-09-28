package com.example.roma.filmsclient.fclient.filmActivity;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

/**
 * Created by Roma on 26.09.2017.
 */

public class FilmContract {
    interface View extends BaseView<Presenter> {

        void showMovie(FilmDetail film);

        void setVisibleView(boolean state);
    }

    interface Presenter extends BasePresenter {

        void loadFilm(int id);
    }
}

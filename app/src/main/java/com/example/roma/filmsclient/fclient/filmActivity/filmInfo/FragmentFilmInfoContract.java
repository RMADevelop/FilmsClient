package com.example.roma.filmsclient.fclient.filmActivity.filmInfo;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;

import java.util.List;

/**
 * Created by RomanM on 14.10.2017.
 */

public interface FragmentFilmInfoContract {

    interface View extends BaseView<Presenter> {
        void showMovie(FilmDetail film);

        void showRecommended(List<Result> films);

        void setVisibleView(boolean state);
    }

    interface Presenter extends BasePresenter {
        void loadFilm(int id);

        void loadRecommended(int id);

        void filmClick(int id);
    }

    interface RecommendedRecycler {
        void filmClick(int id);
    }
}

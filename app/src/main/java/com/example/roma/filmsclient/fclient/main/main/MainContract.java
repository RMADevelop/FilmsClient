package com.example.roma.filmsclient.fclient.main.main;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;


public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showFilms(List<Result> list);

        void startActivity(int id);

        void showPopular(List<Result> films);

        void showTopRated(List<Result> films);

        void showUpcoming(List<Result> films);

        void showFilmList(String type);
    }

    interface Presenter extends BasePresenter {

        void setFilmForActivity(int position);

        void getFilmList(String type);

        void getPopular();

        void getTopRated();

        void getUpcoming();


    }

    interface MainListener {
        void setFragmentList(String type);
    }

    interface ViewPagerListener {
        void setPosition(int id);
    }

    interface RecyclerViewListener {
        void itemClick(int id);
    }
}

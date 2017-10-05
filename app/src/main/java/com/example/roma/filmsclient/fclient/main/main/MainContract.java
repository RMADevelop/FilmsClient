package com.example.roma.filmsclient.fclient.main.main;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

/**
 * Created by Roma on 28.09.2017.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showFilms(List<Result> list);

        void startActivity(int id);

        void showPopular(List<Result> films);
    }

    interface Presenter extends BasePresenter {

        void setFilmForActivity(int position);

        void getPopular();
    }

    interface MainListener {

    }

    interface ViewPagerListener {
        void setPosition(int id);
    }

    interface RecyclerViewListener{
        void itemClick(int id);
    }
}

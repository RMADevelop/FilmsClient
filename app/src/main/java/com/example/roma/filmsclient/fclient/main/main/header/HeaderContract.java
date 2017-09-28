package com.example.roma.filmsclient.fclient.main.main.header;

import com.example.roma.filmsclient.fclient.BasePresenter;
import com.example.roma.filmsclient.fclient.BaseView;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;

/**
 * Created by Roma on 28.09.2017.
 */

public class HeaderContract {

    interface View extends BaseView<Presenter> {

        void setCardView(Result film);

        void startActivity(int id);

    }

    interface Presenter extends BasePresenter {
        void setFilms(Result film);

        void startActivity();
    }


}

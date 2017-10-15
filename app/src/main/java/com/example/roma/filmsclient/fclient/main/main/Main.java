package com.example.roma.filmsclient.fclient.main.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.FilmActivity;
import com.example.roma.filmsclient.fclient.main.main.adapters.AdapterPopularityRecyclerView;
import com.example.roma.filmsclient.fclient.main.main.adapters.ViewPagerAdapter;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.utils.Injection;

import java.util.Collections;
import java.util.List;

import static com.example.roma.filmsclient.fclient.main.premiers.PremiersFragment.ARG_POPULAR;
import static com.example.roma.filmsclient.fclient.main.premiers.PremiersFragment.ARG_TOP_RATED;
import static com.example.roma.filmsclient.fclient.main.premiers.PremiersFragment.ARG_UPCOMMING;


public class Main extends Fragment implements MainContract.View {

    private MainContract.MainListener listener;

    private MainContract.Presenter presenter;

    private MainContract.ViewPagerListener listenerVP = new MainContract.ViewPagerListener() {
        @Override
        public void setPosition(int id) {
            presenter.setFilmForActivity(id);
        }
    };

    private MainContract.RecyclerViewListener listenerPopular = new MainContract.RecyclerViewListener() {
        @Override
        public void itemClick(int id) {
            presenter.setFilmForActivity(id);
        }
    };
    private MainContract.RecyclerViewListener listenerTopRated = new MainContract.RecyclerViewListener() {
        @Override
        public void itemClick(int id) {
            presenter.setFilmForActivity(id);
        }
    };
    private MainContract.RecyclerViewListener listenerUpcoming = new MainContract.RecyclerViewListener() {
        @Override
        public void itemClick(int id) {
            presenter.setFilmForActivity(id);
        }
    };

    private ViewPager vp;

    private ViewPagerAdapter adapter;

    private Button btnMorePopular;

    private Button btnMoreTopRated;

    private Button btnMoreUpcomming;

    private RecyclerView popularityRecycler;
    private RecyclerView topRatedRecycler;
    private RecyclerView upcommingRecycler;


    private AdapterPopularityRecyclerView adapterPopularity;
    private AdapterPopularityRecyclerView adapterTopRated;
    private AdapterPopularityRecyclerView adapterUpcoming;

    public Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        presenter = new MainPresenter(Injection.provideRepository(getActivity()), this);
        initViewPager(view);
        initPopularity(view);
        initTopRated(view);
        initUpcomming(view);
        presenter.subscribe();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getPopular();
        presenter.getTopRated();
        presenter.getUpcoming();
    }

    private void initTopRated(View view) {
        topRatedRecycler = (RecyclerView) view.findViewById(R.id.top_rated_recycler);
        topRatedRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterTopRated = new AdapterPopularityRecyclerView(getContext(), Collections.<Result>emptyList(), listenerTopRated);
        topRatedRecycler.setAdapter(adapterTopRated);

        btnMoreTopRated = (Button) view.findViewById(R.id.button_more_top_rated);
        btnMoreTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getFilmList(ARG_TOP_RATED);
            }
        });
    }

    private void initUpcomming(View view) {
        upcommingRecycler = (RecyclerView) view.findViewById(R.id.upcoming_recycler);
        upcommingRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterUpcoming = new AdapterPopularityRecyclerView(getContext(), Collections.<Result>emptyList(), listenerUpcoming);
        upcommingRecycler.setAdapter(adapterUpcoming);

        btnMoreUpcomming = (Button) view.findViewById(R.id.button_more_upcoming);
        btnMoreUpcomming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getFilmList(ARG_UPCOMMING);

            }
        });
    }


    private void initPopularity(View view) {
        popularityRecycler = (RecyclerView) view.findViewById(R.id.popularity_recycler);
        popularityRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterPopularity = new AdapterPopularityRecyclerView(getContext(), Collections.<Result>emptyList(), listenerPopular);
        popularityRecycler.setAdapter(adapterPopularity);

        btnMorePopular = (Button) view.findViewById(R.id.button_more_popularity);
        btnMorePopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getFilmList(ARG_POPULAR);
            }
        });
    }

    private void initViewPager(View view) {
        vp = (ViewPager) view.findViewById(R.id.view_pager_main);
        adapter = new ViewPagerAdapter(getChildFragmentManager(), Collections.<Result>emptyList(), listenerVP);
        vp.setAdapter(adapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainContract.MainListener) {
            listener = (MainContract.MainListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFilms(List<Result> list) {
        adapter.setList(list);
    }

    @Override
    public void startActivity(int id) {
        Intent intent = new Intent(getActivity(), FilmActivity.class);
        intent.putExtra("filmId", id);
        startActivity(intent);
    }

    @Override
    public void showPopular(List<Result> films) {
        adapterPopularity.setList(films);
    }

    @Override
    public void showTopRated(List<Result> films) {
        adapterTopRated.setList(films);
    }

    @Override
    public void showUpcoming(List<Result> films) {
        adapterUpcoming.setList(films);
    }

    @Override
    public void showFilmList(String type) {
        listener.setFragmentList(type);
    }
}

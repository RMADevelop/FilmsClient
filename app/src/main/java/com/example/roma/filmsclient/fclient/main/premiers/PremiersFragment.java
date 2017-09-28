package com.example.roma.filmsclient.fclient.main.premiers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.FilmActivity;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.utils.Injection;

import java.util.Collections;
import java.util.List;

public class PremiersFragment extends Fragment implements PremiersContract.View {

    PremiersContract.Presenter presenter;

    PremiersAdapterRV adapter;


    PremiersContract.AdapterListener listener = new PremiersContract.AdapterListener() {
        @Override
        public void filmClick(int id) {
            mListener.startActivity(id);
        }
    };

    PremiersContract.FragmentListener mListener;


    public PremiersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premiers, container, false);

        presenter = new PremiersPresenter(
                Injection.provideRepository(view.getContext()),
                this);

        initRV(view);
        return view;
    }

    private void initRV(View view) {
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_premiers);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(adapter = new PremiersAdapterRV(Collections.<Result>emptyList(), view.getContext(), listener));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadMovies();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PremiersContract.FragmentListener) {
            mListener = (PremiersContract.FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void startFilmActivity(int id) {
        Intent intent = new Intent(getActivity(), FilmActivity.class);
        intent.putExtra("filmId", id);
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    @Override
    public void setPresenter(PremiersContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovies(List<Result> list) {
        adapter.setMovies(list);
    }

}

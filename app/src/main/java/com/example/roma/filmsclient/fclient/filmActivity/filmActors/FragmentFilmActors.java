package com.example.roma.filmsclient.fclient.filmActivity.filmActors;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.FilmContract;


public class FragmentFilmActors extends Fragment implements FragmentFilmActorsContract.View{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentFilmActorsContract.Presenter presenter;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentFilmActors() {
        // Required empty public constructor
    }

    public static FragmentFilmActors newInstance(String param1, String param2) {
        FragmentFilmActors fragment = new FragmentFilmActors();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_film_actors, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(FragmentFilmActorsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public interface OnFragmentInteractionListener {

    }
}

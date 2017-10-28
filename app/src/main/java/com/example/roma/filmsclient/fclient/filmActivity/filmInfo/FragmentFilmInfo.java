package com.example.roma.filmsclient.fclient.filmActivity.filmInfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;
import com.example.roma.filmsclient.utils.Api;
import com.example.roma.filmsclient.utils.Injection;

import java.util.List;


public class FragmentFilmInfo extends Fragment implements FragmentFilmInfoContract.View {

    FragmentFilmInfoContract.Presenter presenter;

    public static final String ARG_ID_FILM = "arg_id_film";

    private int idFilm;


    private ImageView poster;

    private TextView title;

    private TextView description;

    private TextView date;


    private OnFragmentInteractionListener mListener;

    public FragmentFilmInfo() {
        // Required empty public constructor
    }


    public static FragmentFilmInfo newInstance(int idFilm) {
        FragmentFilmInfo fragment = new FragmentFilmInfo();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_FILM, idFilm);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


        presenter = new FragmentFilmInfoPresenter(this, Injection.provideRepository(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_info_content, container, false);

        initPoster(view);
        initFields(view);

        return view;
    }

    private void initFields(View view) {
        title = (TextView) view.findViewById(R.id.text_title_base_info);
        date = (TextView) view.findViewById(R.id.text_date_base_info);
        description = (TextView) view.findViewById(R.id.text_description_base_info);


    }

    private void initPoster(View view) {
        poster = (ImageView) view.findViewById(R.id.image_poster_base_info);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadFilm(getArguments().getInt(ARG_ID_FILM));
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
    public void setPresenter(FragmentFilmInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovie(FilmDetail film) {
        title.setText(film.getTitle());
        description.setText(film.getOverview());
        Glide.with(getContext())
                .load(Api.getPathPoster(film.getPosterPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(poster);
    }

    @Override
    public void showRecommended(List<Result> films) {

    }

    @Override
    public void setVisibleView(boolean state) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

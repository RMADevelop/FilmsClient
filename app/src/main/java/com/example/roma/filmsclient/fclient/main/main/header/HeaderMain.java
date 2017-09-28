package com.example.roma.filmsclient.fclient.main.main.header;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.FilmActivity;
import com.example.roma.filmsclient.pojo.Result;
import com.example.roma.filmsclient.utils.Api;
import com.example.roma.filmsclient.utils.Injection;

public class HeaderMain extends Fragment implements HeaderContract.View {

    private static final String ARG_PARAM2 = "param2";

    private HeaderContract.Presenter presenter;

    private OnFragmentInteractionListener mListener;

    private Result film;


    private ImageView headerImage;

    private TextView title;

    public HeaderMain() {
        // Required empty public constructor
    }

    public static HeaderMain newInstance(Result param2) {
        HeaderMain fragment = new HeaderMain();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HeaderPresenter(Injection.provideRepository(getActivity()), this);

        if (getArguments() != null) {
            presenter.setFilms((Result) getArguments().getParcelable(ARG_PARAM2));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header_main, container, false);


        initViews(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.subscribe();
    }

    private void initViews(View view) {
        headerImage = (ImageView) view.findViewById(R.id.header_image);
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startActivity();
            }
        });
        title = (TextView) view.findViewById(R.id.header_title);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(HeaderContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setCardView(Result film) {
        title.setText(film.getTitle());

        Glide.with(this)
                .load(Api.getBackPoster(film.getBackdropPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(headerImage);
    }

    @Override
    public void startActivity(int id) {
        Intent intent = new Intent(getActivity(), FilmActivity.class);
        intent.putExtra("filmId", id);
        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

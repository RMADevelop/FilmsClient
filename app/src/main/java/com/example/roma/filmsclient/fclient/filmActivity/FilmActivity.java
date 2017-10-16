package com.example.roma.filmsclient.fclient.filmActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.adapters.ViewPagerFilmInfo;
import com.example.roma.filmsclient.fclient.filmActivity.filmActors.FragmentFilmActors;
import com.example.roma.filmsclient.fclient.filmActivity.filmInfo.FragmentFilmInfo;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;
import com.example.roma.filmsclient.utils.Api;
import com.example.roma.filmsclient.utils.Injection;

public class FilmActivity extends AppCompatActivity implements FilmContract.View,
        FragmentFilmInfo.OnFragmentInteractionListener,FragmentFilmActors.OnFragmentInteractionListener {

    FilmContract.Presenter presenter;

    private View view;

    private ImageView backPoster;


    private ViewPager viewPager;

    private TabLayout tabLayout;

    private ViewPagerFilmInfo adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        view = findViewById(R.id.film_info_view);

        presenter = new FilmPresenter(this, Injection.provideRepository(this));

        initFields();


    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.loadFilm(getIntent().getExtras().getInt("filmId"));
    }


    private void initFields() {
        backPoster = (ImageView) findViewById(R.id.film_info_backdrop_poster);
        viewPager = (ViewPager) findViewById(R.id.film_info_view_pager);
        adapter = new ViewPagerFilmInfo(getSupportFragmentManager(),getIntent().getExtras().getInt("filmId"));
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_film_info);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setPresenter(FilmContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovie(FilmDetail film) {
        setInfo(film);
    }


    private void setInfo(FilmDetail film) {

        Glide.with(this)
                .load(Api.getBackPoster(film.getBackdropPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(backPoster);

    }

    @Override
    public void setVisibleView(boolean state) {
        view.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void startActivity(int id) {
        Intent intent = new Intent(this, FilmActivity.class);
        intent.putExtra("filmId", id);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

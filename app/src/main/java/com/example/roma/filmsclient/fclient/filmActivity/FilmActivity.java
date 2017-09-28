package com.example.roma.filmsclient.fclient.filmActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.pojo.filmDetail.FilmDetail;
import com.example.roma.filmsclient.utils.Api;
import com.example.roma.filmsclient.utils.Injection;

public class FilmActivity extends AppCompatActivity implements FilmContract.View {

    FilmContract.Presenter presenter;

    private View view;

    private ImageView backPoster;

    private ImageView poster;

    private TextView title;

    private TextView description;

    private TextView date;

    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        view = findViewById(R.id.film_info_view);
        presenter = new FilmPresenter(this, Injection.provideRepository(this));

        initFields();
        presenter.loadFilm(getIntent().getExtras().getInt("filmId"));

    }

    private void initFields() {
        title = (TextView) findViewById(R.id.title_film_info);
        description = (TextView) findViewById(R.id.description_film_info);
        date = (TextView) findViewById(R.id.date_film_info);
        backPoster = (ImageView) findViewById(R.id.film_info_backdrop_poster);
        poster = (ImageView) findViewById(R.id.posterr_film_info);
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
        title.setText(film.getTitle());
        date.setText(film.getReleaseDate());
        description.setText(film.getOverview());

        Glide.with(this)
                .load(Api.getPathPoster(film.getPosterPath()))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(poster);

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
}

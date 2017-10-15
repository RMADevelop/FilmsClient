package com.example.roma.filmsclient.fclient.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.roma.filmsclient.R;
import com.example.roma.filmsclient.fclient.filmActivity.FilmActivity;
import com.example.roma.filmsclient.fclient.main.main.Main;
import com.example.roma.filmsclient.fclient.main.main.MainContract;
import com.example.roma.filmsclient.fclient.main.premiers.PremiersContract;
import com.example.roma.filmsclient.fclient.main.premiers.PremiersFragment;
import com.example.roma.filmsclient.utils.ActivityUtils;
import com.example.roma.filmsclient.utils.Injection;

public class MainScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainScreenContract.View, PremiersContract.FragmentListener, MainContract.MainListener {

    private MainScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        presenter = new MainScreenPresenter(this, Injection.provideRepository(this));

        initContainer();

    }

    private void initContainer() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_main);
        if (fragment == null) {
            ActivityUtils.setFragment(getSupportFragmentManager(), new PremiersFragment(), R.id.container_main);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.profile_menu:
                break;
            case R.id.main_menu:
                presenter.setMain();
                break;
            case R.id.premiers_menu:
                presenter.setFilmsList();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showPremiers() {
        ActivityUtils.setFragment(getSupportFragmentManager(), new PremiersFragment(), R.id.container_main);
    }

    @Override
    public void showMain() {
        ActivityUtils.setFragment(getSupportFragmentManager(), new Main(), R.id.container_main);
    }

    @Override
    public void showFilmsList(String type) {
        ActivityUtils.setFragment(getSupportFragmentManager(), PremiersFragment.getInstance(type),R.id.container_main);
    }

    @Override
    public void startActivity(int id) {
        Intent intent = new Intent(this, FilmActivity.class);
        intent.putExtra("filmId", id);
        startActivity(intent);
    }

    @Override
    public void setFragmentList(String type) {
        presenter.setFilmsList(type);
    }
}

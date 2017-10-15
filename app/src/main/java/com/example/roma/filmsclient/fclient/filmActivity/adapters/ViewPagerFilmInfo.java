package com.example.roma.filmsclient.fclient.filmActivity.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.roma.filmsclient.fclient.filmActivity.filmInfo.FragmentFilmInfo;


public class ViewPagerFilmInfo extends FragmentPagerAdapter {

    int idFilm;

    public ViewPagerFilmInfo(FragmentManager fm, int idFilm) {
        super(fm);
        this.idFilm = idFilm;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFilmInfo.newInstance(idFilm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SWCTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}

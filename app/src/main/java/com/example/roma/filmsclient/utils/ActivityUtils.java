package com.example.roma.filmsclient.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Roma on 26.09.2017.
 */

public class ActivityUtils {
    public static void setFragment(FragmentManager fragmentManager, Fragment fragment, int idContainer) {
        fragmentManager
                .beginTransaction()
                .replace(idContainer,fragment)
                .commit();
    }
}

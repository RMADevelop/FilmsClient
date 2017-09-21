package com.example.roma.filmsclient.utils;


public class Api {

    public final static String URL_IMAGE = "http://image.tmdb.org/t/p/w185"; //342

    public static String getPathPoster(String posterPath) {
        return URL_IMAGE+posterPath;
    }
}

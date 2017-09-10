package com.example.roma.filmsclient.data.source.remote;



public class RemoteSource  {

    private static  RemoteSource INSTANCE;

    private RemoteSource(){

    }

    public static RemoteSource getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new RemoteSource();
        }
        return INSTANCE;
    }

}

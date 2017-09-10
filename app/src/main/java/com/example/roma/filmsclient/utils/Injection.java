package com.example.roma.filmsclient.utils;

import android.content.Context;

import com.example.roma.filmsclient.data.source.Repository;
import com.example.roma.filmsclient.data.source.local.LocalDataRoom;
import com.example.roma.filmsclient.data.source.local.LocalRoomDAO;
import com.example.roma.filmsclient.data.source.local.LocalSource;
import com.example.roma.filmsclient.data.source.remote.RemoteSource;

/**
 * Created by Roma on 09.09.2017.
 */

public class Injection {
 public static Repository provideRepository(Context context){
     LocalDataRoom db = LocalDataRoom.getInstance(context.getApplicationContext());
     return Repository.getInstance(LocalSource.getInstance(db.getDAO()), RemoteSource.getInstance());
 }
}

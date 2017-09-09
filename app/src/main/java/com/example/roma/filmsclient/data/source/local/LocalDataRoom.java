package com.example.roma.filmsclient.data.source.local;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;



public abstract class LocalDataRoom  extends RoomDatabase{
    public static LocalDataRoom INSTANCE;

    public abstract LocalRoomDAO getDAO();

    public static LocalDataRoom getInstance(Context context){
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context,LocalDataRoom.class,"database_translate").build();
        return INSTANCE;
    }
}

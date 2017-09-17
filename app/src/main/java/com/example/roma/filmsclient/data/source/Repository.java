package com.example.roma.filmsclient.data.source;

import com.example.roma.filmsclient.data.source.local.LocalSource;
import com.example.roma.filmsclient.data.source.preference.SharedPref;
import com.example.roma.filmsclient.data.source.remote.RemoteSource;
import com.example.roma.filmsclient.pojo.SessionId;

/**
 * Created by Roma on 09.09.2017.
 */

public class Repository implements DataSource {

    LocalSource local;

    RemoteSource remote;

    SharedPref sp;

    private static Repository INSTANCE;

    private Repository(LocalSource local, RemoteSource remote,SharedPref sp) {
        this.local = local;
        this.remote = remote;
        this.sp = sp;
    }

    public static Repository getInstance(LocalSource local, RemoteSource remote, SharedPref sp) {
        if (INSTANCE == null)
            INSTANCE = new Repository(local, remote,sp);
        return INSTANCE;
    }


    @Override
    public void saveSessionId(SessionId sessionId) {
        sp.saveSessionId(sessionId);
    }
}

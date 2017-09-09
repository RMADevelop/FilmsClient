package com.example.roma.filmsclient.data.source;

import com.example.roma.filmsclient.data.source.local.LocalSource;
import com.example.roma.filmsclient.data.source.remote.RemoteSource;

/**
 * Created by Roma on 09.09.2017.
 */

public class Repository implements DataSource {

    LocalSource local;

    RemoteSource remote;

    private static Repository INSTANCE;

    private Repository(LocalSource local, RemoteSource remote) {
        this.local = local;
        this.remote = remote;
    }

    public static Repository getInstance(LocalSource local, RemoteSource remote) {
        if (INSTANCE == null)
            INSTANCE = new Repository(local, remote);
        return INSTANCE;
    }


    @Override
    public void save() {

    }
}

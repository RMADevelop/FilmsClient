package com.example.roma.filmsclient.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Roma on 09.09.2017.
 */
@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    int id;
}

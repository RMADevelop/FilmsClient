package com.example.roma.filmsclient.data.source.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.roma.filmsclient.pojo.SessionId;


public class SharedPref {

    private static SharedPref INSTANCE;
    SharedPreferences sp;

    private SharedPref(Context context) {
        sp = context.getSharedPreferences("PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
    }

    public static SharedPref getInstanse(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPref(context);
        }
        return INSTANCE;
    }

    public void saveSessionId(SessionId session) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("sessionId", session.getSessionId());
        editor.apply();
    }

    public void getSessionId() {
        sp.getString("sessionId", null);
    }
}

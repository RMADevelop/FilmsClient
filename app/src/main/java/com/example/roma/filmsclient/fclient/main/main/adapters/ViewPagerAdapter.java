package com.example.roma.filmsclient.fclient.main.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roma.filmsclient.fclient.main.main.MainContract;
import com.example.roma.filmsclient.fclient.main.main.header.HeaderContract;
import com.example.roma.filmsclient.fclient.main.main.header.HeaderMain;
import com.example.roma.filmsclient.pojo.Result;

import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    MainContract.ViewPagerListener listener;
    List<Result> list;

    public ViewPagerAdapter(FragmentManager fm, List<Result> list, MainContract.ViewPagerListener listener) {
        super(fm);
        this.list = list;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        Log.v("headerVP", "getItem " + list.get(position).getTitle());
        return HeaderMain.newInstance(list.get(position));
    }


    public void setList(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setPosition(position);
            }
        });
        return super.instantiateItem(container, position);
    }
}

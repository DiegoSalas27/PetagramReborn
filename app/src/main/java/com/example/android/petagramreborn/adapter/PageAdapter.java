package com.example.android.petagramreborn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by DOMINIC on 8/20/2017.
 */

//permite manejar toda la parte de incrustar un fragment en cada tab
public class PageAdapter extends FragmentPagerAdapter{

    //cada fragment incrustrado en cada tab sera un arreglo de fragments
    private ArrayList<Fragment> fragments;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

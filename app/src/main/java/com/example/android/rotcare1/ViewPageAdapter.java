package com.example.android.rotcare1;

import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmentListTitles = new ArrayList<>();

    public ViewPageAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);

    }
    public void AddFragment(Fragment fragment,String Title){
        fragmentList.add(fragment);
        FragmentListTitles.add(Title);
    }
}

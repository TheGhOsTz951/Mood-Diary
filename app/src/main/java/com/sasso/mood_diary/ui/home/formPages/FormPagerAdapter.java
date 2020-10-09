package com.sasso.mood_diary.ui.home.formPages;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FormPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public FormPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }
}

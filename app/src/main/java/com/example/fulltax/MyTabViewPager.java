package com.example.fulltax;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyTabViewPager extends FragmentPagerAdapter {
    int tabCount;
    public MyTabViewPager(@NonNull FragmentManager fm, int tabCount) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new UniformTaxFragment();
            case 1: return new LegalEntityTaxFragment();
            default : return new LegalEntityTaxFragment();
        }
    }

    public void setTabCount(int tabCount){
        this.tabCount=tabCount;
    }

    @Override
    public int getCount() {
        return this.tabCount;
    }
}
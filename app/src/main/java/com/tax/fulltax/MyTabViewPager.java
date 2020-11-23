package com.tax.fulltax;

import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyTabViewPager extends FragmentStatePagerAdapter {
    int tabCount;
    YearsFragment yearsFragment;
    String[] tabHeaders;
    public MyTabViewPager(@NonNull FragmentManager fm,  YearsFragment yearsFragment,String[] tabHeaders) {
        super(fm);
        this.tabCount = yearsFragment.fragments.size();
        this.yearsFragment=yearsFragment;
        this.tabHeaders=tabHeaders;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = this.yearsFragment.fragments.get(position);
        return fragment;
    }

    public void setTabCount(int tabCount){
        this.tabCount=tabCount;
    }

    @Override
    public int getCount() {
        return this.tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        try {
//            byte[] utf8Bytes = this.tabHeaders[position].getBytes("windows-1252");
            String roundTrip = this.tabHeaders[position]; //new String(utf8Bytes, "UTF8");
            return roundTrip;
        } catch (Exception e) {

            return "";
        }

      /* if(position==0) return "Hello-There  " + this.yearsFragment.fragments.get(position).getClass().toString();
       else return "التاب الأوولى   " + this.yearsFragment.fragments.get(position).getClass().toString(); */
    }

  @Override
  public Parcelable saveState()
  {
      return null;
  }

}
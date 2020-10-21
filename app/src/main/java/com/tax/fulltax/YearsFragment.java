package com.tax.fulltax;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class YearsFragment{
    public int fromYear, toYear;
    public List<Fragment> fragments=new ArrayList<Fragment>();
    YearsFragment(int fromY,int toY,Fragment frag1,Fragment frag2){
        this.fromYear=fromY;
        this.toYear=toY;
        this.fragments.add(frag1);
        if(frag2!=null) this.fragments.add(frag2);
    }
}
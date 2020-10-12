package com.example.fulltax;

import androidx.fragment.app.Fragment;

import com.example.completefactory.Constants;

import java.util.ArrayList;
import java.util.List;

 public  class FragmentIndex {

      FragmentIndex(){
        fragmentIndex.add(new YearsFragment(1981,1994,new UniformTaxFragment(), new UniformTaxFragment() ) );
        fragmentIndex.add(new YearsFragment(1995,2004,new UniformTaxFragment(), null ) );
        fragmentIndex.add(new YearsFragment(2005,2016,new SegmentSimpleTaxFragment(), new LegalEntityTaxFragment() ) );
        fragmentIndex.add(new YearsFragment(2017,2019,new SegmentsWithDiscountsTaxFragment(), new LegalEntityTaxFragment() ) );
        fragmentIndex.add(new YearsFragment(2020, Constants.MAX_YEAR,new SegmentSimpleTaxFragment(), new LegalEntityTaxFragment() ) );
    }
     public   List<YearsFragment>  fragmentIndex=new ArrayList<YearsFragment>();
}
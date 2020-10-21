package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_1981_1993_Inclusive{

    List<TaxStructure_1981_2004_Inclusive> taxSegmentsCommercialsAndIndustrial=new ArrayList<TaxStructure_1981_2004_Inclusive>();
    List<TaxStructure_1981_2004_Inclusive> taxSegmentsProfession=new ArrayList<TaxStructure_1981_2004_Inclusive>();


    TaxRule_1981_1993_Inclusive(){

        //Fill The Commercial Taxes PlayList
        //From 1983 to 1993
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(1,1000,20,0));
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(1001,2500,23,30));
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(2501,4500,27,130));
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(4501,7000,32,355));
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(7001,10000,35,565));
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(10001,13500,38,865));
        this.taxSegmentsCommercialsAndIndustrial.add(new TaxStructure_1981_2004_Inclusive(135001,Constants.MAX_VALUE_INT,40,1135));


        //Fill The Tax Profession ArrayList
        //From 1981 to 1993
        this.taxSegmentsProfession.add(new TaxStructure_1981_2004_Inclusive(1,1000,18,0));
        this.taxSegmentsProfession.add(new TaxStructure_1981_2004_Inclusive(1001,2500,20,20));
        this.taxSegmentsProfession.add(new TaxStructure_1981_2004_Inclusive(2501,4500,25,145));
        this.taxSegmentsProfession.add(new TaxStructure_1981_2004_Inclusive(4501,Constants.MAX_VALUE_INT,30,370));

    }
}
//////////////////////////////////////////////////////////////////////////////////////////
///// TaxRule for 1994 To 1997  Unified Tax
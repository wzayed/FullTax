package com.example.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2005_2012_Inclusive{
    final String lawInfo="القانون رقم 91 لسنة 2005";

    final double TAXForLegalPerson=20.0;

    List<TaxStructure_2005_2016_Inclusive> taxSegments=new ArrayList<TaxStructure_2005_2016_Inclusive>();
    TaxRule_2005_2012_Inclusive(){
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(1,5000,0));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(5001,20000,10));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(20001,40000,15));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(40001,Constants.MAX_VALUE_INT,20));
    }
}
package com.example.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2015_2016_Inclusive{
    final String lawInfo="القانون رقم 96 لسنة 2015 صدر فى 20-08-2015";

    final double TAXForLegalPerson=22.5;

    List<TaxStructure_2005_2016_Inclusive> taxSegments=new ArrayList<TaxStructure_2005_2016_Inclusive>();
    TaxRule_2015_2016_Inclusive(){
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(1,6500,0));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(6501,30000,10));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(30001,45000,15));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(45001,200000,20));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(200001,Constants.MAX_VALUE_INT,22.5));
    }
}
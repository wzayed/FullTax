package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2013_2014_Inclusive{
    final String lawInfo="القانون رقم 11 لسنة 2013 صدر فى 18-05-2013";

    final double TAXForLegalPerson=25.0;

    ArrayList<com.tax.completefactory.TaxStructure_2005_2016_Inclusive> taxSegments=new ArrayList<com.tax.completefactory.TaxStructure_2005_2016_Inclusive>();
    TaxRule_2013_2014_Inclusive(){
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(1,5000,0));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(5001,30000,10));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(30001,45000,15));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(45001,250000,20));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(250001,Constants.MAX_VALUE_INT,25));
    }
    public ArrayList<TaxStructure_2005_2016_Inclusive> getTaxSegments(){
        return taxSegments;
    }
}
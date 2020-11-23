package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2015_2016_Inclusive{
    final String lawInfo="القانون رقم 96 لسنة 2015 صدر فى 20-08-2015";

    final double TAXForLegalPerson=22.5;

    ArrayList<com.tax.completefactory.TaxStructure_2005_2016_Inclusive> taxSegments=new ArrayList<com.tax.completefactory.TaxStructure_2005_2016_Inclusive>();
    TaxRule_2015_2016_Inclusive(){
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(1,6500,0));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(6501,30000,10));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(30001,45000,15));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(45001,200000,20));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(200001, Constants.MAX_VALUE_INT,22.5));
    }
    public ArrayList<TaxStructure_2005_2016_Inclusive> getTaxSegments(){
        return taxSegments;
    }
    public void clearTaxValuesInTheArray(){
        for(int i=0 ; i< taxSegments.size()  ;i++){
            taxSegments.get(i).taxValueInThisSegment=0;
        }
    }
}
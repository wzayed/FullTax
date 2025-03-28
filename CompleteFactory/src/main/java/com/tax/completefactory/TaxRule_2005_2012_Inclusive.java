package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2005_2012_Inclusive{
    final String lawInfo="القانون رقم 91 لسنة 2005";

    final double TAXForLegalPerson=20.0;

    ArrayList<com.tax.completefactory.TaxStructure_2005_2016_Inclusive> taxSegments=new ArrayList<com.tax.completefactory.TaxStructure_2005_2016_Inclusive>();
    TaxRule_2005_2012_Inclusive(){
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(1,5000,0));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(5001,20000,10));
        this.taxSegments.add(new com.tax.completefactory.TaxStructure_2005_2016_Inclusive(20001,40000,15));
        this.taxSegments.add(new TaxStructure_2005_2016_Inclusive(40001,Constants.MAX_VALUE_INT,20));
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
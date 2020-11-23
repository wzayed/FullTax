package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2017{
    final String lawInfo="القانون رقم 82 لسنة 2017 صدر فى 01-07-2017";

    final double TAXForLegalPerson=22.5;
    ArrayList<TaxStructure_2017_2019_Inclusive> taxSegments=new ArrayList<TaxStructure_2017_2019_Inclusive>();
    TaxRule_2017(){
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(1,7200,0,0));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(7201,30000,10,80));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(30001,45000,15,40));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(45001,200000,20,5));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(200001,Constants.MAX_VALUE_INT,22.5,0));
    }
    public ArrayList<TaxStructure_2017_2019_Inclusive> getTaxSegments(){
        return taxSegments;
    }
    public void clearTaxValuesInTheArray(){
        for(int i=0 ; i< taxSegments.size()  ;i++){
            taxSegments.get(i).taxValueWithDiscountInThisSegment=taxSegments.get(i).taxValueWithoutDiscountInThisSegment=0;
        }
    }
}
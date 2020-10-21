package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2018_To_2019{
    final String lawInfo="القانون رقم 97 لسنة 2018 صدر فى 22-06-2018";

    final double TAXForLegalPerson=22.5;

    List<TaxStructure_2017_2019_Inclusive> taxSegments=new ArrayList<TaxStructure_2017_2019_Inclusive>();
    TaxRule_2018_To_2019(){
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(1,8000,0,0));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(8001,30000,10,85));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(30001,45000,15,45));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(45001,200000,20,7.5));
        this.taxSegments.add(new TaxStructure_2017_2019_Inclusive(200001, Constants.MAX_VALUE_INT,22.5,0));
    }
}
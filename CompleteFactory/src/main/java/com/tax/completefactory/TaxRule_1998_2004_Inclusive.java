package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_1998_2004_Inclusive{
    List<TaxStructure_1981_2004_Inclusive> taxSegments=new ArrayList<TaxStructure_1981_2004_Inclusive>();

    TaxRule_1998_2004_Inclusive(){

        this.taxSegments.add(new TaxStructure_1981_2004_Inclusive(1,2500,20,0));
        this.taxSegments.add(new TaxStructure_1981_2004_Inclusive(2501,7000,27,175));
        this.taxSegments.add(new TaxStructure_1981_2004_Inclusive(7001,16000,35,735));
        this.taxSegments.add(new TaxStructure_1981_2004_Inclusive(16001,Constants.MAX_VALUE_INT,40,1535));

    }
}
package com.tax.completefactory;

import java.util.ArrayList;
import java.util.List;

public class  TaxRule_2020_And_Above{
    final String lawInfo="القانون رقم 26 لسنة 2020 صدر فى 07-مايو-2020";

    final double TAXForLegalPerson=22.5;

    ArrayList<TaxStructure_2020_Inclusive> taxSegments=new ArrayList<TaxStructure_2020_Inclusive>();
    ArrayList<TaxLayersStructure2020> taxLayers= new ArrayList<TaxLayersStructure2020>();
    TaxRule_2020_And_Above(){
        this.taxSegments.add(new TaxStructure_2020_Inclusive(1,15000,0));
        this.taxSegments.add(new TaxStructure_2020_Inclusive(15001,30000,2.5));
        this.taxSegments.add(new TaxStructure_2020_Inclusive(30001,45000,10));
        this.taxSegments.add(new TaxStructure_2020_Inclusive(45001,60000,15));
        this.taxSegments.add(new TaxStructure_2020_Inclusive(60001,200000,20));
        this.taxSegments.add(new TaxStructure_2020_Inclusive(200001,400000,22.5));
        this.taxSegments.add(new TaxStructure_2020_Inclusive(400001,Constants.MAX_VALUE_INT,25));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Fill The TaxLayers
        this.taxLayers.add(new TaxLayersStructure2020(1,600000,0));
        this.taxLayers.add(new TaxLayersStructure2020(600001,700000,1));
        this.taxLayers.add(new TaxLayersStructure2020(700001,800000,2));
        this.taxLayers.add(new TaxLayersStructure2020(800001,900000,3));
        this.taxLayers.add(new TaxLayersStructure2020(900001,1000000,4));
        this.taxLayers.add(new TaxLayersStructure2020(1000001,Constants.MAX_VALUE_INT,5));
    }
    public ArrayList<TaxStructure_2020_Inclusive> getTaxSegments(){
        return taxSegments;
    }
    //tax_layers start_at_segment
}
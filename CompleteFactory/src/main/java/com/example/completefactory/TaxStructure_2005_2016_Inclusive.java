package com.example.completefactory;
public class  TaxStructure_2005_2016_Inclusive{
    int fromAmount, toAmount;
    double taxPercentageInThisSegment, taxValueInThisSegment;
    TaxStructure_2005_2016_Inclusive(int fromAmount,int toAmount,double taxPercentageInThisSegment){
        this.fromAmount=fromAmount;
        this.toAmount=toAmount;
        this.taxPercentageInThisSegment=taxPercentageInThisSegment;
    }
}
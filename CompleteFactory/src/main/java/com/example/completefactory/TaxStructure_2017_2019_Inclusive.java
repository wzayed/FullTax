package com.example.completefactory;
public class  TaxStructure_2017_2019_Inclusive{
    int fromAmount, toAmount;
    double taxPercentageInThisSegment, taxValueWithoutDiscountInThisSegment,
            taxValueWithDiscountInThisSegment,taxDiscountPercentageInThisSegment;
    TaxStructure_2017_2019_Inclusive(int fromAmount,int toAmount,double taxPercentageInThisSegment,double taxDiscountPercentageInThisSegment){
        this.fromAmount=fromAmount;
        this.toAmount=toAmount;
        this.taxPercentageInThisSegment=taxPercentageInThisSegment;
        this.taxDiscountPercentageInThisSegment=taxDiscountPercentageInThisSegment;
    }
}
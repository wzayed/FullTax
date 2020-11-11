package com.tax.completefactory;
public class  TaxStructure_2020_Inclusive {
    int fromAmount, toAmount;
    double taxPercentageInThisSegment, taxValueInThisSegment;
    TaxStructure_2020_Inclusive(int fromAmount,int toAmount,double taxPercentageInThisSegment){
        this.fromAmount=fromAmount;
        this.toAmount=toAmount;
        this.taxPercentageInThisSegment=taxPercentageInThisSegment;
    }
    public int getFromAmount(){
        return this.fromAmount;
    }
    public int getToAmount(){
        return this.toAmount;
    }
    public double getTaxPercentageInThisSegment(){
        return  this.taxPercentageInThisSegment;
    }
    public double getTaxValueInThisSegment(){
        return this.taxValueInThisSegment;
    }
}
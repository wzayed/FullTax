package com.tax.completefactory;
public class  TaxStructure_1981_2004_Inclusive{
    int fromAmount, toAmount;
    double taxPercentage, taxDiscountAmount,taxValueBeforeDiscount,taxValue;
    TaxStructure_1981_2004_Inclusive(int fromAmount,int toAmount,double taxPercentage,double taxDiscountAmount){
        this.fromAmount=fromAmount;
        this.toAmount=toAmount;
        this.taxPercentage=taxPercentage;
        this.taxDiscountAmount=taxDiscountAmount;
    }
    public int getFromAmount(){
        return this.fromAmount;
    }
    public int getToAmount(){
        return this.toAmount;
    }
    public double getTaxPercentageInThisSegment(){
        return  this.taxPercentage;
    }
    public double gettaxDiscountAmount(){
        return this.taxDiscountAmount;
    }
    public double getTaxValueInThisSegment(){ return this.taxValueBeforeDiscount; }
}
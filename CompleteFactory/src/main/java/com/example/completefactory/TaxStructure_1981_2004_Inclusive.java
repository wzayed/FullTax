package com.example.completefactory;
public class  TaxStructure_1981_2004_Inclusive{
    int fromAmount, toAmount;
    double taxPercentage, taxDiscountAmount,taxValueBeforeDiscount,taxValue;
    TaxStructure_1981_2004_Inclusive(int fromAmount,int toAmount,double taxPercentage,double taxDiscountAmount){
        this.fromAmount=fromAmount;
        this.toAmount=toAmount;
        this.taxPercentage=taxPercentage;
        this.taxDiscountAmount=taxDiscountAmount;
    }
}
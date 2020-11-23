package com.tax.completefactory;
public class  TaxLayersStructure2020{ // Used when Tax layers is used along with tax segments as in year 2020
    int fromAmount,toAmount,start_At_Segment;
    TaxLayersStructure2020(int fromAmount,int toAmount,int start_At_Segment){
        this.fromAmount=fromAmount;
        this.toAmount=toAmount;
        this.start_At_Segment=start_At_Segment;
    }
    public int[] getLayerInfo(){
        int[] a= new int[] {fromAmount,toAmount,start_At_Segment};
        return a;
    }
}
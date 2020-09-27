package com.example.completefactory;

public class  Tax_2020_Above_Inclusive implements ITax{
    TaxRule_2020_And_Above theTaxRule=new TaxRule_2020_And_Above();
    private double taxValue=0.00;
    double taxBase=0;
    public final String[] tabs={"شخص إعتبارى","شخص طبيعى"};
    TaxPersonType taxPersonType;
    Tax_2020_Above_Inclusive(double taxBase,TaxPersonType taxPersonType){
        this.taxBase=taxBase;
        this.taxPersonType=taxPersonType;
    }

    Tax_2020_Above_Inclusive(){

    }

    double getTaxValue(){
        return this.taxValue;
    }
    void setTaxBase(double taxBase){
        this.taxBase=taxBase;
    }
    void setTaxPersonType(TaxPersonType tpt)
    {
        this.taxPersonType=tpt;
    }

    public double getTax_LegalPerson()                    //On and After 2005
    {

        taxValue=taxBase*theTaxRule.TAXForLegalPerson/100; //100for Percentage;
        return this.taxValue;
    }

    public double getTax_NormalPerson_WithExemption(double noOfMonthes,SocialStatus sc)      //This and Next functions are For Individuals
    {


        return this.taxValue;
    }
    public double getTax_NormalPerson_WithExemption(double noOfMonthes)                     //On and After 2005
    {
        return this.taxValue;
    }
    public double getTax_NormalPersonWithout_Exemption(double noOfMonthes)                  //////
    {

        this.taxValue=taxBase*noOfMonthes*theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment/1200; //1200for Percentage
        return this.taxValue;
    }
    public double getTax_NormalPerson_WithDiscount(double noOfMonthes)                      /////
    {


        return this.taxValue;
    }
    public double getTax_NormalPerson_WithoutDiscount(double noOfMonthes)                   //End For Individuals
    {


        return this.taxValue;
    }
    public String get_WhichRuleAmI(){
        return "Tax_2020_Above_Inclusive" + "\n" + theTaxRule.lawInfo;
    }
} ///End Of class
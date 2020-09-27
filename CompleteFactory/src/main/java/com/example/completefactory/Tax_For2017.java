package com.example.completefactory;
public class  Tax_For2017 implements ITax{
    TaxRule_2017 theTaxRule=new TaxRule_2017();
    private double taxValue=0.00;
    double taxBase=0;
    public final String[] tabs={"شخص إعتبارى","شخص طبيعى"};
    TaxPersonType taxPersonType;
    Tax_For2017(double taxBase,TaxPersonType taxPersonType){
        this.taxBase=taxBase;
        this.taxPersonType=taxPersonType;
    }

    Tax_For2017(){

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

        taxValue=taxBase*theTaxRule.TAXForLegalPerson/100; //100for Percentage
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

        taxValue=taxBase*noOfMonthes*theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment/1200; //1200for Percentage
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
        return "Tax_For2017" + "\n" + theTaxRule.lawInfo;
    }

} ///End Of class
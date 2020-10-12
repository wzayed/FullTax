package com.example.completefactory;
public class  Tax_2015_2016_Inclusive implements ITax{

    double taxBase=0;
    TaxPersonType taxPersonType;
    private double taxValue=0.00;
    public final String[] tabs={"شخص طبيعى","شخص إعتبارى"};
    Tax_2015_2016_Inclusive(double taxBase,TaxPersonType taxPersonType){
        this.taxBase=taxBase;
        this.taxPersonType=taxPersonType;
    }

    Tax_2015_2016_Inclusive(){

    }

    double getTaxValue(){
        return this.taxValue;

    }
    public void setTaxBase(double taxBase){
        this.taxBase=taxBase;
    }
    public void setTaxPersonType(TaxPersonType tpt)
    {
        this.taxPersonType=tpt;
    }
    TaxRule_2015_2016_Inclusive theTaxRule=new TaxRule_2015_2016_Inclusive();

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

    public int getTabCount(){
        return  tabs.length;
    }

    public String[] getTabHeaders(){
        return tabs;
    }

    public String get_WhichRuleAmI(){
        return "Tax_From_To_2015_2016_Inclusive" + "\n" + theTaxRule.lawInfo;
    }

    public double getTaxRatioLegalEntity(){
        return theTaxRule.TAXForLegalPerson;
    }
    public double getTaxRatioNormalPerson_Unexempted()
    {
        return theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment;
    }
} ///End Of class
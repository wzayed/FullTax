package com.tax.completefactory;

public class  Tax_2018_2019_Inclusive implements ITax {
    com.tax.completefactory.TaxRule_2018_To_2019 theTaxRule=new TaxRule_2018_To_2019();
    private double taxValue=0.00;
    private double taxDiscount=0;
    double taxBase=0;
    public final String[] tabs={"شخص طبيعى","شخص إعتبارى"};
    com.tax.completefactory.TaxPersonType taxPersonType;
    Tax_2018_2019_Inclusive(double taxBase, com.tax.completefactory.TaxPersonType taxPersonType){
        this.taxBase=taxBase;
        this.taxPersonType=taxPersonType;
    }

    Tax_2018_2019_Inclusive(){

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

    public double getTax_LegalPerson()                    //On and After 2005
    {

        taxValue=taxBase*theTaxRule.TAXForLegalPerson/100; //100for Percentage
        return this.taxValue;
    }

    public double getTax_NormalPerson_WithExemption(double noOfMonthes, com.tax.completefactory.SocialStatus sc)      //This and Next functions are For Individuals
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
        int i=0;
        double accumulateTaxValue=0;
        while(taxBase > theTaxRule.taxSegments.get(i).toAmount){
            accumulateTaxValue += theTaxRule.taxSegments.get(i).taxValueWithoutDiscountInThisSegment=
                    theTaxRule.taxSegments.get(i).taxPercentageInThisSegment*
                            (theTaxRule.taxSegments.get(i).toAmount - theTaxRule.taxSegments.get(i).fromAmount +1)*noOfMonthes/1200;

            i++;
        }
        if(taxBase >=  theTaxRule.taxSegments.get(i).fromAmount){
            accumulateTaxValue += theTaxRule.taxSegments.get(i).taxValueWithoutDiscountInThisSegment=
                    (taxBase - theTaxRule.taxSegments.get(i).fromAmount +1)
                            * theTaxRule.taxSegments.get(i).taxPercentageInThisSegment* noOfMonthes/1200;
            taxDiscount= theTaxRule.taxSegments.get(i).taxDiscountPercentageInThisSegment;
            accumulateTaxValue *= (1-taxDiscount/100);
            // theTaxRule.taxSegments.get(i).taxValueWithDiscountInThisSegment=(1-theTaxRule.taxSegments.get(i).taxDiscountPercentageInThisSegment/100);
        }
        taxValue=accumulateTaxValue;

        return this.taxValue;
    }
    public double getTax_NormalPerson_WithoutDiscount(double noOfMonthes)                   //End For Individuals
    {


        return this.taxValue;
    }
    public double getDiscount_NormalPerson_WithDiscount(){
        return taxDiscount;
    }
    public int getTabCount(){
        return  tabs.length;
    }

    public String[] getTabHeaders(){
        return tabs;
    }

    public String get_WhichRuleAmI(){
        return "Tax_From_To_2018_2019_Inclusive" + "\n" + theTaxRule.lawInfo;
    }
    public double getTaxRatioLegalEntity(){
        return theTaxRule.TAXForLegalPerson;
    }
    public double getTaxRatioNormalPerson_Without_exemption()
    {
        return theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment;
    }
    public void setSocialStatus(SocialStatus sc){

    }
} ///End Of class
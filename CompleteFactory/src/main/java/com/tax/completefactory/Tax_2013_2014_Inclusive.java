package com.tax.completefactory;
public class  Tax_2013_2014_Inclusive implements ITax{
    TaxRule_2013_2014_Inclusive theTaxRule=new TaxRule_2013_2014_Inclusive();
    private double taxValue=0.00;
    double taxBase=0;
    public final String[] tabs={"شخص طبيعى","شخص إعتبارى"};
    TaxPersonType taxPersonType;
    Tax_2013_2014_Inclusive(double taxBase,TaxPersonType taxPersonType){
        this.taxBase=taxBase;
        this.taxPersonType=taxPersonType;
    }

    Tax_2013_2014_Inclusive(){

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


    public double getTax_NormalPerson_WithExemption(double noOfMonthes)                     //On and After 2005
    {

        double taxableAmountInTheSegment=0;
        if(taxBase<=0) return 0 ;
        taxValue=0;
        int i=0;
        while(taxBase > theTaxRule.taxSegments.get(i).toAmount){
            taxableAmountInTheSegment=theTaxRule.taxSegments.get(i).toAmount - theTaxRule.taxSegments.get(i).fromAmount + 1;
            taxValue +=theTaxRule.taxSegments.get(i).taxValueInThisSegment=
                    taxableAmountInTheSegment*(noOfMonthes/12)
                            * (theTaxRule.taxSegments.get(i).taxPercentageInThisSegment/100);
            i++;
        }
        taxableAmountInTheSegment=taxBase - theTaxRule.taxSegments.get(i).fromAmount + 1 ;
        taxValue +=theTaxRule.taxSegments.get(i).taxValueInThisSegment=
                taxableAmountInTheSegment*(noOfMonthes/12)
                        * (theTaxRule.taxSegments.get(i).taxPercentageInThisSegment/100);

        return this.taxValue;
    }
    public double getTax_NormalPersonWithout_Exemption(double noOfMonthes)                  //////
    {

        taxValue=taxBase*noOfMonthes*theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment/1200; //1200for Percentage
        return this.taxValue;
    }

    public int getTabCount(){
        return  tabs.length;
    }

    public String[] getTabHeaders(){
        return tabs;
    }

    public String get_WhichRuleAmI(){
        return "Tax_From_To_2013_2014_Inclusive" + "\n" + theTaxRule.lawInfo;
    }

    public double getTaxRatioLegalEntity(){
        return theTaxRule.TAXForLegalPerson;
    }
    public double getTaxRatioNormalPerson_Without_exemption()
    {
        return theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////// Doesn't apply to ths year range
    public double getTax_NormalPerson_WithDiscount(double noOfMonthes)                      /////
    {
        return this.taxValue;
    }
    public double getTax_NormalPerson_WithoutDiscount(double noOfMonthes)                   //End For Individuals
    {
        return this.taxValue;
    }
    public double getTax_NormalPerson_WithExemption(double noOfMonthes,SocialStatus sc)      //This and Next functions are For Individuals
    {

        return this.taxValue;
    }
    public void setSocialStatus(SocialStatus sc){

    }
    public double getDiscount_NormalPerson_WithDiscount(){
        return 0;
    }
} ///End Of class
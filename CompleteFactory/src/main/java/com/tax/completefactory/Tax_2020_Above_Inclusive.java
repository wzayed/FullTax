package com.tax.completefactory;

public class  Tax_2020_Above_Inclusive implements ITax{
    public com.tax.completefactory.TaxRule_2020_And_Above theTaxRule=new TaxRule_2020_And_Above();
    private double taxValue=0.00;
    double taxBase=0;
    public final String[] tabs={"شخص طبيعى","شخص إعتبارى"};
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
    public void setTaxBase(double taxBase){
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


    public double getTax_NormalPerson_WithExemption(double noOfMonthes)                     //On and After 2005
    {
        //From Taxbase get the layer
        int i=0;
        while(taxBase > theTaxRule.taxLayers.get(i).toAmount ){
            i++;
        }
        int j=  theTaxRule.taxLayers.get(i).start_At_Segment;
        double accumulativeTaxValue=0;
        while(taxBase > theTaxRule.taxSegments.get(j).toAmount){
            if( j ==  theTaxRule.taxLayers.get(i).start_At_Segment)
                accumulativeTaxValue+=  theTaxRule.taxSegments.get(j).taxValueInThisSegment=
                        noOfMonthes*(theTaxRule.taxSegments.get(j).toAmount ) *
                                theTaxRule.taxSegments.get(j).taxPercentageInThisSegment/1200;
                else
            accumulativeTaxValue+=  theTaxRule.taxSegments.get(j).taxValueInThisSegment=
                    noOfMonthes*(theTaxRule.taxSegments.get(j).toAmount - theTaxRule.taxSegments.get(j).fromAmount +1) *
                    theTaxRule.taxSegments.get(j).taxPercentageInThisSegment/1200;
            j++;
        }
        if(taxBase >= theTaxRule.taxSegments.get(j).fromAmount ) {
            accumulativeTaxValue += theTaxRule.taxSegments.get(j).taxValueInThisSegment =
                    (taxBase - theTaxRule.taxSegments.get(j).fromAmount + 1) *
                            noOfMonthes * theTaxRule.taxSegments.get(j).taxPercentageInThisSegment / 1200;
        }
        taxValue=accumulativeTaxValue;
        return this.taxValue;
    }
    public double getTax_NormalPersonWithout_Exemption(double noOfMonthes)                  //////
    {
        this.taxValue=taxBase*noOfMonthes*theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentageInThisSegment/1200; //1200for Percentage
        return this.taxValue;
    }

    public int getTabCount(){
        return  tabs.length;
    }

    public String[] getTabHeaders(){
        return tabs;
    }

    public String get_WhichRuleAmI(){
        return "Tax_2020_Above_Inclusive" + "\n" + theTaxRule.lawInfo;
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
    public double getTax_NormalPerson_WithExemption(double noOfMonthes, com.tax.completefactory.SocialStatus sc)      //This and Next functions are For Individuals
    {
        taxValue=0;
        return this.taxValue;
    }
    public void setSocialStatus(SocialStatus sc){

    }
    public double getDiscount_NormalPerson_WithDiscount(){
        return 0;
    }
} ///End Of class
package com.tax.completefactory;
public class  Tax_For2017 implements ITax{
    TaxRule_2017 theTaxRule=new TaxRule_2017();
    private double taxValue=0.00;
    double taxBase=0;
    private double taxDiscount=0;
    public final String[] tabs={"شخص طبيعى","شخص إعتبارى"};
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
        //Here set the taxDiscount so that you can bring a real value when calling getDiscount
        clear_tax_values();
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
    public <Any> Any getTaxStructure(){

        return (Any)(theTaxRule.getTaxSegments()) ;
    }
    public String[] getTabHeaders(){
        return tabs;
    }
    public String get_WhichRuleAmI(){
        return  theTaxRule.lawInfo;//return "Tax_For2017" + "\n" + theTaxRule.lawInfo;
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
    void clear_tax_values(){
        theTaxRule.clearTaxValuesInTheArray();
    }
} ///End Of class
package com.tax.completefactory;

class  Tax_1981_1993_Inclusive implements ITax {
    final String lawInfo="القانون رقم 157 لسنة 1981";
    private com.tax.completefactory.SocialStatus socialStatus;
    com.tax.completefactory.TaxRule_1981_1993_Inclusive theTaxRule=new TaxRule_1981_1993_Inclusive();
    Exemption_1981_1993 taxExemption=new Exemption_1981_1993();

    double taxBase=0;
    TaxPersonType taxPersonType;

    private double taxValue=0.00;
    public final String[] tabs={"أرباح مهن حرة","أرباح تجارية"};

    Tax_1981_1993_Inclusive(double taxBase,TaxPersonType taxPersonType){
        this.taxBase=taxBase;
        this.taxPersonType=taxPersonType;
    }

    Tax_1981_1993_Inclusive(){
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

    public void setSocialStatus(com.tax.completefactory.SocialStatus sc){
        this.socialStatus=sc;
    }
    public double getTax_LegalPerson()                    //On and After 2005
    {
        clear_tax_values();
        if(taxBase==0) return 0 ;
        int i=0;
        while(taxBase > theTaxRule.taxSegmentsCommercialsAndIndustrial.get(i).toAmount){
            i++;
        }
        this.taxValue=theTaxRule.taxSegmentsCommercialsAndIndustrial.get(i).taxValueBeforeDiscount=taxBase*theTaxRule.taxSegmentsCommercialsAndIndustrial.get(i).taxPercentage/100
                - theTaxRule.taxSegmentsCommercialsAndIndustrial.get(i).taxDiscountAmount;

        return this.taxValue;
    }

    public double getTax_NormalPerson_WithExemption(double noOfMonthes, SocialStatus sc)      //This and Next functions are For Individuals
    {
        clear_tax_values();
        double taxbaseAfterExemption=this.taxBase - taxExemption.exemptions.get(sc);

        if(taxbaseAfterExemption<=0) return 0 ;
        int i=0;
        while(taxbaseAfterExemption > theTaxRule.taxSegmentsProfession.get(i).toAmount){
            i++;
        }
        this.taxValue=taxbaseAfterExemption*(noOfMonthes/12)
                * (theTaxRule.taxSegmentsProfession.get(i).taxPercentage/100)
                - theTaxRule.taxSegmentsProfession.get(i).taxDiscountAmount;
        theTaxRule.taxSegmentsProfession.get(i).taxValueBeforeDiscount=taxbaseAfterExemption*(noOfMonthes/12)
                * (theTaxRule.taxSegmentsProfession.get(i).taxPercentage/100);
        return this.taxValue;
    }
    public double getTax_NormalPerson_WithExemption(double noOfMonthes)                     //On and After 2005
    {
        clear_tax_values();
        double taxbaseAfterExemption=this.taxBase - taxExemption.exemptions.get(this.socialStatus);

        if(taxbaseAfterExemption<=0) return 0 ;
        int i=0;
        while(taxbaseAfterExemption > theTaxRule.taxSegmentsProfession.get(i).toAmount){
            i++;
        }
        this.taxValue=taxbaseAfterExemption*(noOfMonthes/12)
                * (theTaxRule.taxSegmentsProfession.get(i).taxPercentage/100)
                - theTaxRule.taxSegmentsProfession.get(i).taxDiscountAmount;
        theTaxRule.taxSegmentsProfession.get(i).taxValueBeforeDiscount=taxbaseAfterExemption*(noOfMonthes/12)
                * (theTaxRule.taxSegmentsProfession.get(i).taxPercentage/100);

        return this.taxValue;
    }
    public double getTax_NormalPersonWithout_Exemption(double noOfMonthes)                  //////
    {
        clear_tax_values();
        this.taxValue=taxBase*noOfMonthes*theTaxRule.taxSegmentsProfession.get(theTaxRule.taxSegmentsProfession.size() - 1).taxPercentage/1200; //1200for Percentage
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
    public <Any> Any getTaxStructure(){
        return (Any)(theTaxRule.getTaxSegments()) ;
    }
    public int getTabCount(){
        return  tabs.length;
    }

    public String[] getTabHeaders(){
        return tabs;
    }
    public String get_WhichRuleAmI(){
        return  lawInfo;//return "Tax_From_To_1981_1993_Inclusive" + "\n" + lawInfo;
    }

    public double getTaxRatioLegalEntity(){
        return theTaxRule.taxSegmentsCommercialsAndIndustrial.get(theTaxRule.taxSegmentsCommercialsAndIndustrial.size() - 1).taxPercentage;
    }
    public double getTaxRatioNormalPerson_Without_exemption()
    {
        return theTaxRule.taxSegmentsProfession.get(theTaxRule.taxSegmentsProfession.size() - 1).taxPercentage;
    }
    public double getDiscount_NormalPerson_WithDiscount(){
        return 0;
    }
    void clear_tax_values(){
        theTaxRule.clearTaxValuesInTheArray();
    }

} ///End Of class
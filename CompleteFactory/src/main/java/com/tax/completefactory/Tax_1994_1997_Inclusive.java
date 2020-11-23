package com.tax.completefactory;
public class  Tax_1994_1997_Inclusive implements ITax {
    TaxRule_1994_1997_Inclusive theTaxRule = new TaxRule_1994_1997_Inclusive();
    final String lawInfo = "القانون رقم 187 لسنة 1993 و تعديلاته";
    private SocialStatus socialStatus;
    Exemption_1994_1997 taxExemption = new Exemption_1994_1997();
    double taxBase = 0;
    TaxPersonType taxPersonType;
    private double taxValue = 0.00;
    public final String[] tabs = {"الضريبة الموحدة"};

    Tax_1994_1997_Inclusive(double taxBase, TaxPersonType taxPersonType) {
        this.taxBase = taxBase;
        this.taxPersonType = taxPersonType;
    }

    double getTaxValue() {
        return this.taxValue;

    }

    Tax_1994_1997_Inclusive() {

    }

    public void setTaxBase(double taxBase) {
        this.taxBase = taxBase;
    }

    public void setTaxPersonType(TaxPersonType tpt) {
        this.taxPersonType = tpt;
    }

    public void setSocialStatus(SocialStatus sc) {
        this.socialStatus = sc;
    }

    public double getTax_LegalPerson()                    //On and After 2005
    {
        clear_tax_values();
        if (taxBase == 0) return 0;
        int i = 0;
        while (taxBase > theTaxRule.taxSegments.get(i).toAmount) {
            i++;
        }
        this.taxValue = taxBase * theTaxRule.taxSegments.get(i).taxPercentage / 100
                - theTaxRule.taxSegments.get(i).taxDiscountAmount;
        theTaxRule.taxSegments.get(i).taxValueBeforeDiscount=taxBase * theTaxRule.taxSegments.get(i).taxPercentage / 100;
        return this.taxValue;
    }

    public double getTax_NormalPerson_WithExemption(double noOfMonthes, SocialStatus sc)      //This and Next functions are For Individuals
    {
        clear_tax_values();
        double taxbaseAfterExemption = this.taxBase - taxExemption.exemptions.get(sc);

        if (taxbaseAfterExemption <= 0) return 0;
        int i = 0;
        while (taxbaseAfterExemption > theTaxRule.taxSegments.get(i).toAmount) {
            i++;
        }
        this.taxValue = taxbaseAfterExemption * (noOfMonthes / 12)
                * (theTaxRule.taxSegments.get(i).taxPercentage / 100)
                - theTaxRule.taxSegments.get(i).taxDiscountAmount;
        theTaxRule.taxSegments.get(i).taxValueBeforeDiscount=taxbaseAfterExemption * (noOfMonthes / 12)
                * (theTaxRule.taxSegments.get(i).taxPercentage / 100);

        return this.taxValue;
    }

    public double getTax_NormalPerson_WithExemption(double noOfMonthes)                     //On and After 2005
    {
        clear_tax_values();
        double taxbaseAfterExemption = this.taxBase - taxExemption.exemptions.get(this.socialStatus);

        if (taxbaseAfterExemption <= 0) return 0;
        int i = 0;
        while (taxbaseAfterExemption > theTaxRule.taxSegments.get(i).toAmount) {
            i++;
        }
        this.taxValue = taxbaseAfterExemption * (noOfMonthes / 12)
                * (theTaxRule.taxSegments.get(i).taxPercentage / 100)
                - theTaxRule.taxSegments.get(i).taxDiscountAmount;
        theTaxRule.taxSegments.get(i).taxValueBeforeDiscount=taxbaseAfterExemption * (noOfMonthes / 12)
                * (theTaxRule.taxSegments.get(i).taxPercentage / 100);

        return this.taxValue;
    }

    public double getTax_NormalPersonWithout_Exemption(double noOfMonthes)                  //////
    {
        clear_tax_values();
        this.taxValue = taxBase * noOfMonthes * theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentage / 1200; //1200for Percentage
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
    public int getTabCount() {
        return tabs.length;
    }

    public String[] getTabHeaders() {
        return tabs;
    }

    public String get_WhichRuleAmI() {
        return  lawInfo; //return "Tax_From_To_1994_1997_Inclusive" + "\n" + lawInfo;
    }

    public double getTaxRatioLegalEntity() {
        return theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentage;
    }

    public double getTaxRatioNormalPerson_Without_exemption() {
        return theTaxRule.taxSegments.get(theTaxRule.taxSegments.size() - 1).taxPercentage;
    }
    public double getDiscount_NormalPerson_WithDiscount(){
        return 0;
    }
    void clear_tax_values(){
        theTaxRule.clearTaxValuesInTheArray();
    }
}///End Of class
package com.tax.completefactory;

public interface ITax{


    double getTax_LegalPerson();                    //On and After 2005

    double getTax_NormalPerson_WithExemption(double noOfMonthes, com.tax.completefactory.SocialStatus sc);      //This and Next functions are For Individuals
    double getTax_NormalPerson_WithExemption(double noOfMonthes);                     //On and After 2005
    double getTax_NormalPersonWithout_Exemption(double noOfMonthes);                  //////
    double getTax_NormalPerson_WithDiscount(double noOfMonthes);                      /////
    double getTax_NormalPerson_WithoutDiscount(double noOfMonthes);                   //End For Individuals
    double getDiscount_NormalPerson_WithDiscount();
    void setTaxBase(double taxBase);
    void setSocialStatus(SocialStatus sc);

    int getTabCount();
    String[] getTabHeaders();

    String get_WhichRuleAmI();                                   //For Debug Info

     double getTaxRatioLegalEntity();

     double getTaxRatioNormalPerson_Without_exemption();

}
package com.example.completefactory;
public interface ITax{


    double getTax_LegalPerson();                    //On and After 2005

    double getTax_NormalPerson_WithExemption(double noOfMonthes,SocialStatus sc);      //This and Next functions are For Individuals
    double getTax_NormalPerson_WithExemption(double noOfMonthes);                     //On and After 2005
    double getTax_NormalPersonWithout_Exemption(double noOfMonthes);                  //////
    double getTax_NormalPerson_WithDiscount(double noOfMonthes);                      /////
    double getTax_NormalPerson_WithoutDiscount(double noOfMonthes);                   //End For Individuals

    String get_WhichRuleAmI();                                   //For Debug Info

}
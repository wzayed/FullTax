package com.example.completefactory;
public class  ConcreteTaxYearEntityFactory extends ITaxYearEntity{
    @Override
    public ITax createTaxYearEntity(int taxYear){
        ITax taxYearEntityForTheYear=null;
        //Return The right Tax Year Entity
        if(taxYear>=1981 && taxYear<=1993){
            taxYearEntityForTheYear= new Tax_1981_1993_Inclusive();
        } else if (taxYear>=1994 && taxYear<=1997){
            taxYearEntityForTheYear= new Tax_1994_1997_Inclusive();
        } else if (taxYear>=1998 && taxYear<=2004){
            taxYearEntityForTheYear= new Tax_1998_2004_Inclusive();
        } else if (taxYear>=2005 && taxYear<=2012){
            taxYearEntityForTheYear= new Tax_2005_2012_Inclusive();
        } else if (taxYear>=2013 && taxYear<=2014){
            taxYearEntityForTheYear= new Tax_2013_2014_Inclusive();
        } else if (taxYear>=2015 && taxYear<=2016){
            taxYearEntityForTheYear= new Tax_2015_2016_Inclusive();
        } else if (taxYear==2017) {
            taxYearEntityForTheYear= new Tax_For2017();
        } else if (taxYear>=2018 && taxYear<=2019){
            taxYearEntityForTheYear= new Tax_2018_2019_Inclusive();
        } else if (taxYear>=2020) {
            taxYearEntityForTheYear= new Tax_2020_Above_Inclusive();
        }
        return taxYearEntityForTheYear;
    } //End of getTaxRule function()

}
package com.tax.completefactory;

import java.util.HashMap;

public class  Exemption_1981_1993{

    HashMap<com.tax.completefactory.SocialStatus,Integer> exemptions =new HashMap<com.tax.completefactory.SocialStatus,Integer>();
    Exemption_1981_1993(){
        exemptions.put(com.tax.completefactory.SocialStatus.Single, 720);
        exemptions.put(com.tax.completefactory.SocialStatus.MarriedNotSponsoring, 840);
        exemptions.put(com.tax.completefactory.SocialStatus.NotMarriedSponsor, 840);
        exemptions.put(SocialStatus.MarriedSponsor, 960);
    }
}
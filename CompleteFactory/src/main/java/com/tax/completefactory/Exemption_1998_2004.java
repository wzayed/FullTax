package com.tax.completefactory;

import java.util.HashMap;

public  class  Exemption_1998_2004{

    HashMap<SocialStatus,Integer> exemptions =new HashMap<SocialStatus,Integer>();
    Exemption_1998_2004(){
        exemptions.put(SocialStatus.Single, 2000);
        exemptions.put(SocialStatus.MarriedNotSponsoring, 2500);
        exemptions.put(SocialStatus.NotMarriedSponsor, 2500);
        exemptions.put(SocialStatus.MarriedSponsor, 3000);
    }
}
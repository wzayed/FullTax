package com.example.completefactory;

import java.util.HashMap;

public class  Exemption_1994_1997{

    HashMap<SocialStatus,Integer> exemptions =new HashMap<SocialStatus,Integer>();
    Exemption_1994_1997(){
        exemptions.put(SocialStatus.Single, 1440);
        exemptions.put(SocialStatus.MarriedNotSponsoring, 1680);
        exemptions.put(SocialStatus.NotMarriedSponsor, 1680);
        exemptions.put(SocialStatus.MarriedSponsor, 1920);

    }
}
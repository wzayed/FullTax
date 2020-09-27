package com.example.completefactory;

import java.util.HashMap;

public class  Exemption_1981_1993{

    HashMap<SocialStatus,Integer> exemptions =new HashMap<SocialStatus,Integer>();
    Exemption_1981_1993(){
        exemptions.put(SocialStatus.Single, 720);
        exemptions.put(SocialStatus.MarriedNotSponsoring, 840);
        exemptions.put(SocialStatus.NotMarriedSponsor, 840);
        exemptions.put(SocialStatus.MarriedSponsor, 960);
    }
}
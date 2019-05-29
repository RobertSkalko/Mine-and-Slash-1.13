package com.robertx22.database.affixes.requirements;

import java.util.ArrayList;
import java.util.List;

public class AffixRequirements {

    List<BaseAffixRequirement> requirements = new ArrayList<>();

    public AffixRequirements(BaseAffixRequirement req) {
        this.requirements.add(req);
    }

    public AffixRequirements(BaseAffixRequirement req1, BaseAffixRequirement req2) {
        this.requirements.add(req1);
        this.requirements.add(req2);
    }

    public AffixRequirements(BaseAffixRequirement req1, BaseAffixRequirement req2,
                             BaseAffixRequirement req3) {
        this.requirements.add(req1);
        this.requirements.add(req2);
        this.requirements.add(req3);
    }

    public AffixRequirements(List<BaseAffixRequirement> reqs) {
        this.requirements.addAll(reqs);
    }

    public boolean satisfiesAllRequirements(AffixRequested requested) {

        for (BaseAffixRequirement req : requirements) {
            if (req.meetsRequierment(requested) == false) {
                return false;
            }
        }

        return true;
    }

}

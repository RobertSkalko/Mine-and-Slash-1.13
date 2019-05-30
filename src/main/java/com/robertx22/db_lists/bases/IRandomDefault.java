package com.robertx22.db_lists.bases;

import com.robertx22.database.requirements.GearRequestedFor;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public interface IRandomDefault<T extends IhasRequirements & IWeighted> extends IRandom<T, GearRequestedFor> {

    HashMap<String, T> All();

    public default T random(GearRequestedFor gearRequestedFor) {

        List<T> allThatMeetReq = allThatMeetRequirement(gearRequestedFor);

        if (allThatMeetReq.size() == 0) {
            return null;
        }

        return RandomUtils.weightedRandom(allThatMeetReq);
    }

    public default T random() {
        return RandomUtils.weightedRandom(All().values());
    }

    public default List<T> allThatMeetRequirement(GearRequestedFor gearRequestedFor) {
        return All().values()
                .stream()
                .filter(x -> x.requirements().satisfiesAllRequirements(gearRequestedFor))
                .collect(Collectors.toList());
    }

}

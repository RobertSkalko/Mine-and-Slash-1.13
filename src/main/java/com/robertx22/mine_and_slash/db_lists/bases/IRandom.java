package com.robertx22.mine_and_slash.db_lists.bases;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IWeighted;

import java.util.HashMap;
import java.util.List;

public interface IRandom<T extends IhasRequirements & IWeighted, Config> {

    HashMap<String, T> All();

    public T random(Config gearRequestedFor);

    public T random();

    public List<T> allThatMeetRequirement(Config gearRequestedFor);

}

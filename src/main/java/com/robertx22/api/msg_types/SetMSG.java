package com.robertx22.api.msg_types;

import com.robertx22.database.sets.Set;
import com.robertx22.db_lists.Sets;

public class SetMSG implements MineAndSlashMSG {

    public Set set;

    public SetMSG(Set set) {
        this.set = set;
    }

    @Override
    public final void register() {
        Sets.All.put(set.GUID(), set);
    }

}

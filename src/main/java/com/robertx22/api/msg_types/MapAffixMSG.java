package com.robertx22.api.msg_types;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.db_lists.MapAffixes;

public class MapAffixMSG implements MineAndSlashMSG {

    public BaseMapAffix affix;

    public MapAffixMSG(BaseMapAffix affix) {
        this.affix = affix;
    }

    @Override
    public final void register() {
        MapAffixes.All.put(affix.GUID(), affix);
    }

}

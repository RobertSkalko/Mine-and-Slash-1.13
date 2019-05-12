package com.robertx22.api.msg_types;

import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.StatusEffects;

public class MobEffectsMSG implements MineAndSlashMSG {

    public BaseStatusEffect affix;

    public MobEffectsMSG(BaseStatusEffect affix) {
        this.affix = affix;
    }

    @Override
    public final void register() {
        StatusEffects.All.put(affix.GUID(), affix);
    }

}


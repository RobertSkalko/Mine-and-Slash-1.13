package com.robertx22.database.stats.stat_types.elementals.transfers.thunder_to;

import com.robertx22.database.stats.TransferMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.transfers.BaseTransferMod;

import java.util.Arrays;
import java.util.List;

public class ThunderToWaterTransfer extends BaseTransferMod {

    @Override
    public String GUID() {
        return "Thunder To Water Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(new SpellThunderDamage(), new SpellWaterDamage()), new TransferMethod(new AttackThunderDamage(), new AttackWaterDamage()));

    }

}

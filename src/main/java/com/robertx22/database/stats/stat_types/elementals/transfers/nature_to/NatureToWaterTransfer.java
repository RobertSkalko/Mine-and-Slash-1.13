package com.robertx22.database.stats.stat_types.elementals.transfers.nature_to;

import com.robertx22.database.stats.TransferMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.transfers.BaseTransferMod;

import java.util.Arrays;
import java.util.List;

public class NatureToWaterTransfer extends BaseTransferMod {

    @Override
    public String GUID() {
        return "Nature To Water Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(new SpellNatureDamage(), new SpellWaterDamage()), new TransferMethod(new AttackNatureDamage(), new AttackWaterDamage()));

    }

}

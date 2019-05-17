package com.robertx22.database.stats.stat_types.elementals.transfers.nature_to;

import com.robertx22.database.stats.TransferMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.transfers.BaseTransferMod;

import java.util.Arrays;
import java.util.List;

public class NatureToFireTransfer extends BaseTransferMod {

    @Override
    public String GUID() {
        return "Nature To Fire Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(new SpellNatureDamage(), new SpellFireDamage()), new TransferMethod(new AttackNatureDamage(), new AttackFireDamage()));

    }

}

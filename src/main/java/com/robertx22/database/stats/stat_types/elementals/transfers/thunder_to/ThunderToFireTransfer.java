package com.robertx22.database.stats.stat_types.elementals.transfers.thunder_to;

import com.robertx22.database.stats.TransferMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.transfers.BaseTransferMod;

import java.util.Arrays;
import java.util.List;

public class ThunderToFireTransfer extends BaseTransferMod {

    @Override
    public String GUID() {
        return "Thunder To Fire Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(new SpellThunderDamage(), new SpellFireDamage()), new TransferMethod(new AttackThunderDamage(), new AttackFireDamage()));

    }

}

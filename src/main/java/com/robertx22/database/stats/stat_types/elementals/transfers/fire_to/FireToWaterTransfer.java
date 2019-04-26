package com.robertx22.database.stats.stat_types.elementals.transfers.fire_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.TransferMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.transfers.BaseTransferMod;

public class FireToWaterTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Fire To Water Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellFireDamage(), new SpellWaterDamage()),
		new TransferMethod(new AttackFireDamage(), new AttackWaterDamage()));

    }

    @Override
    public String unlocString() {
	return "fire_water_transfer";
    }

}

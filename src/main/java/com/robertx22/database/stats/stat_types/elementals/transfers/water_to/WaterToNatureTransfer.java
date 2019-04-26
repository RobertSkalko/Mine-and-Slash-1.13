package com.robertx22.database.stats.stat_types.elementals.transfers.water_to;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.TransferMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.transfers.BaseTransferMod;

public class WaterToNatureTransfer extends BaseTransferMod {

    @Override
    public String Guid() {
	return "Water To Nature Transfer";
    }

    @Override
    public List<TransferMethod> Transfer() {
	return Arrays.asList(new TransferMethod(new SpellWaterDamage(), new SpellNatureDamage()),
		new TransferMethod(new AttackWaterDamage(), new AttackNatureDamage()));

    }

    @Override
    public String unlocString() {
	return "water_nature_transfer";
    }
}

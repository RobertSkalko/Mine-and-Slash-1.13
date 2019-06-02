package com.robertx22.items.infusions;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

public class AttackInfusionItem extends BaseInfusionItem {

    public AttackInfusionItem() {
        super(name);

    }

    private static final String name = "attack_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public List<StatMod> weaponInfusions() {
        return ListUtils.newList(new ElementalSpellToAttackDMGFlat().allSingleElements(), new CriticalHitFlat(), new CriticalDamageFlat(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorInfusions() {
        return ListUtils.newList(new ElementalSpellDamagePercent().allSingleElements(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
        return new ElementalResistFlat().allSingleElements();
    }

    @Override
    public String GUID() {
        return name;
    }

}

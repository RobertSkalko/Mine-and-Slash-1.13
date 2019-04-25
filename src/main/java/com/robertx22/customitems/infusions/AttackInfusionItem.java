package com.robertx22.customitems.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.mmorpg.Ref;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class AttackInfusionItem extends BaseInfusionItem {

    public AttackInfusionItem() {
	super(name);

    }

    private static final String name = "attack_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new AttackInfusionItem());
    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new CriticalHitFlat(), new CriticalDamageFlat(), new PhysicalDamagePercent(),
		new NatureSpellToAttackFlat(), new WaterSpellToAttackFlat(), new ThunderSpellToAttackFlat(),
		new FireSpellToAttackFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new SpellNatureDamagePercent(), new SpellFireDamagePercent(),
		new SpellThunderDamagePercent(), new SpellWaterDamagePercent(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return Arrays.asList(new FirePeneFlat(), new WaterPeneFlat(), new ThunderPeneFlat(), new NaturePeneFlat());
    }

    @Override
    public String GUID() {
	return name;
    }

}

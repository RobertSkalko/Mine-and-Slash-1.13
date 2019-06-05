package com.robertx22.db_lists;

import com.robertx22.database.map_mods.bases.LessWeaponDamageFlat;
import com.robertx22.database.map_mods.bonus.BonusHealthMap;
import com.robertx22.database.map_mods.bonus.BonusLifestealMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusFireDamageMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusNatureDamageMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusThunderDamageMap;
import com.robertx22.database.map_mods.bonus.ele_dmg.BonusWaterDamageMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusFireResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusNatureResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusThunderResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusWaterResistMap;
import com.robertx22.database.map_mods.minus.*;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllFireDamageMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllNatureDamageMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllThunderDamageMap;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllWaterDamageMap;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.AllTraitMods;
import com.robertx22.database.stats.stat_mods.flat.*;
import com.robertx22.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleSpellDmgFlat;
import com.robertx22.database.stats.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.misc.BonusExpFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.*;
import com.robertx22.database.stats.stat_mods.flat.resources.conversions.EnergyToManaConvFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.database.stats.stat_mods.generated.*;
import com.robertx22.database.stats.stat_mods.multi.defense.*;
import com.robertx22.database.stats.stat_mods.multi.ele_minus.MajorMinusFireResistMulti;
import com.robertx22.database.stats.stat_mods.multi.ele_minus.MajorMinusNatureResistMulti;
import com.robertx22.database.stats.stat_mods.multi.ele_minus.MajorMinusThunderResistMulti;
import com.robertx22.database.stats.stat_mods.multi.ele_minus.MajorMinusWaterResistMulti;
import com.robertx22.database.stats.stat_mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessManaMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.ManaMulti;
import com.robertx22.database.stats.stat_mods.percent.*;
import com.robertx22.database.stats.stat_mods.percent.less.*;
import com.robertx22.database.stats.stat_mods.percent.much_less.*;
import com.robertx22.database.stats.stat_mods.percent.offense.*;
import com.robertx22.database.stats.stat_types.BaseTrait;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatMods {

    public static HashMap<String, StatMod> All = new HashMap<String, StatMod>() {
        {
            {

                put(new AllEleDmgFlat().GUID(), new AllEleDmgFlat());
                put(new AllEleSpellDmgFlat().GUID(), new AllEleSpellDmgFlat());

                put(new BlockStrengthPercent().GUID(), new BlockStrengthPercent());
                put(new SpellDamageFlat().GUID(), new SpellDamageFlat());
                put(new SpellDamagePercent().GUID(), new SpellDamagePercent());

                put(new BonusExpFlat().GUID(), new BonusExpFlat());

                // spell buffs

                put(new ManaMulti().GUID(), new ManaMulti());
                put(new LessManaMulti().GUID(), new LessManaMulti());
                put(new LessHealthRegenMulti().GUID(), new LessHealthRegenMulti());

                put(new PhysicalDamageMulti().GUID(), new PhysicalDamageMulti());
                put(new LessPhysicalDamageMulti().GUID(), new LessPhysicalDamageMulti());

                put(new LessArmorMulti().GUID(), new LessArmorMulti());
                put(new HealthMulti().GUID(), new HealthMulti());
                put(new DodgeMulti().GUID(), new DodgeMulti());
                put(new CriticalHitMulti().GUID(), new CriticalHitMulti());
                put(new ArmorMulti().GUID(), new ArmorMulti());

                //

                put(new EnergyToManaConvFlat().GUID(), new EnergyToManaConvFlat());
                put(new ManaToEnergyConvFlat().GUID(), new ManaToEnergyConvFlat());

                // weapon damages

                // less stats

                put(new LessHealthRegenFlat().GUID(), new LessHealthRegenFlat());
                put(new LessCriticalDamagePercent().GUID(), new LessCriticalDamagePercent());
                put(new LessCriticalHitPercent().GUID(), new LessCriticalHitPercent());
                put(new LessDodgePercent().GUID(), new LessDodgePercent());
                put(new LessHealthRegenPercent().GUID(), new LessHealthRegenPercent());
                put(new LessManaRegenPercent().GUID(), new LessManaRegenPercent());
                put(new LessManaOnHitPercent().GUID(), new LessManaOnHitPercent());
                put(new LessLifestealPercent().GUID(), new LessLifestealPercent());
                put(new LessLifeOnHitPercent().GUID(), new LessLifeOnHitPercent());
                // less stats

                // cripple stats (much less)
                put(new CrippleCriticalDamagePercent().GUID(), new CrippleCriticalDamagePercent());
                put(new CrippleCriticalHitPercent().GUID(), new CrippleCriticalHitPercent());
                put(new CrippleDodgePercent().GUID(), new CrippleDodgePercent());
                put(new CrippleHealthRegenPercent().GUID(), new CrippleHealthRegenPercent());
                put(new CrippleManaRegenPercent().GUID(), new CrippleManaRegenPercent());
                put(new CrippleManaOnHitPercent().GUID(), new CrippleManaOnHitPercent());
                put(new CrippleLifestealPercent().GUID(), new CrippleLifestealPercent());
                put(new CrippleLifeOnHitPercent().GUID(), new CrippleLifeOnHitPercent());
                // cripple

                put(new MajorCriticalHitPercent().GUID(), new MajorCriticalHitPercent());
                put(new MajorCriticalDamagePercent().GUID(), new MajorCriticalDamagePercent());

                put(new MajorDodgeFlat().GUID(), new MajorDodgeFlat());
                put(new MajorArmorFlat().GUID(), new MajorArmorFlat());
                put(new ArmorFlat().GUID(), new ArmorFlat());
                put(new ArmorPeneFlat().GUID(), new ArmorPeneFlat());
                put(new CriticalHitFlat().GUID(), new CriticalHitFlat());
                put(new CriticalDamageFlat().GUID(), new CriticalDamageFlat());
                put(new PhysicalDamageFlat().GUID(), new PhysicalDamageFlat());
                put(new CriticalHitPercent().GUID(), new CriticalHitPercent());
                put(new PhysicalDamagePercent().GUID(), new PhysicalDamagePercent());
                put(new CriticalDamagePercent().GUID(), new CriticalDamagePercent());
                put(new ArmorPenePercent().GUID(), new ArmorPenePercent());
                put(new DodgePercent().GUID(), new DodgePercent());
                put(new BlockStrengthFlat().GUID(), new BlockStrengthFlat());

                // Resources
                put(new MajorManaRegenFlat().GUID(), new MajorManaRegenFlat());
                put(new HealthFlat().GUID(), new HealthFlat());
                put(new HealthPercent().GUID(), new HealthPercent());
                put(new HealthRegenPercent().GUID(), new HealthRegenPercent());
                put(new HealthRegenFlat().GUID(), new HealthRegenFlat());
                put(new ManaRegenFlat().GUID(), new ManaRegenFlat());
                put(new EnergyRegenFlat().GUID(), new EnergyRegenFlat());
                put(new EnergyRegenPercent().GUID(), new EnergyRegenPercent());
                put(new ManaRegenPercent().GUID(), new ManaRegenPercent());
                put(new EnergyFlat().GUID(), new EnergyFlat());

                put(new LifestealFlat().GUID(), new LifestealFlat());
                put(new LifestealPercent().GUID(), new LifestealPercent());
                put(new LifeOnHitFlat().GUID(), new LifeOnHitFlat());
                put(new LifeOnHitPercent().GUID(), new LifeOnHitPercent());
                put(new ManaFlat().GUID(), new ManaFlat());
                put(new ManaOnHitFlat().GUID(), new ManaOnHitFlat());
                // Resources

                put(new ArmorPercent().GUID(), new ArmorPercent());
                put(new DodgeFlat().GUID(), new DodgeFlat());

                // bonus dmg

                // Map mods

                put(new BonusHealthMap().GUID(), new BonusHealthMap());
                put(new BonusLifestealMap().GUID(), new BonusLifestealMap());

                put(new LessCriticalHitMap().GUID(), new LessCriticalHitMap());
                put(new LessDodgeMap().GUID(), new LessDodgeMap());

                put(new BonusFireDamageMap().GUID(), new BonusFireDamageMap());
                put(new BonusNatureDamageMap().GUID(), new BonusNatureDamageMap());
                put(new BonusThunderDamageMap().GUID(), new BonusThunderDamageMap());
                put(new BonusWaterDamageMap().GUID(), new BonusWaterDamageMap());

                put(new BonusFireResistMap().GUID(), new BonusFireResistMap());
                put(new BonusNatureResistMap().GUID(), new BonusNatureResistMap());
                put(new BonusThunderResistMap().GUID(), new BonusThunderResistMap());
                put(new BonusWaterResistMap().GUID(), new BonusWaterResistMap());

                put(new LessAllFireDamageMap().GUID(), new LessAllFireDamageMap());
                put(new LessAllNatureDamageMap().GUID(), new LessAllNatureDamageMap());
                put(new LessAllThunderDamageMap().GUID(), new LessAllThunderDamageMap());
                put(new LessAllWaterDamageMap().GUID(), new LessAllWaterDamageMap());

                put(new LessEnergyRegenMap().GUID(), new LessEnergyRegenMap());
                put(new LessManaRegenMap().GUID(), new LessManaRegenMap());
                put(new LessHealthRegenMap().GUID(), new LessHealthRegenMap());
                put(new LessLifeOnHitMap().GUID(), new LessLifeOnHitMap());
                put(new LessLifestealMap().GUID(), new LessLifestealMap());
                put(new LessHealthMap().GUID(), new LessHealthMap());
                put(new LessManaOnHitMap().GUID(), new LessManaOnHitMap());
                // Map mods
                put(new MajorMinusFireResistMulti().GUID(), new MajorMinusFireResistMulti());
                put(new MajorMinusWaterResistMulti().GUID(), new MajorMinusWaterResistMulti());
                put(new MajorMinusThunderResistMulti().GUID(), new MajorMinusThunderResistMulti());
                put(new MajorMinusNatureResistMulti().GUID(), new MajorMinusNatureResistMulti());

            }
        }
    };

    private static List<StatMod> nonGenerated = new ArrayList<StatMod>() {
        {
            add(new AllAttributesFlat());
            add(new StrengthFlat());
            add(new DexterityFlat());
            add(new WisdomFlat());
            add(new IntelligenceFlat());
            add(new StaminaFlat());
            add(new VitalityFlat());

        }

    };

    private static List<IGenerated<StatMod>> generated = new ArrayList<IGenerated<StatMod>>() {
        {
            {
                add(new ElementalConversionFlat(Elements.None, Elements.None));
                add(new ElementalTransferFlat(Elements.None, Elements.None));
                add(new ElementalAffinityFlat(Elements.None));
                add(new LootTypeBonusFlat(LootType.NormalItem));
                add(new WeaponDamageFlat(WeaponTypes.None));
                add(new LessWeaponDamageFlat(WeaponTypes.None));
                add(new ElementalAttackDamageFlat(Elements.None));
                add(new AllElementalDamageMulti(Elements.None));
                add(new ElementalSpellToAttackDMGFlat(Elements.None));
                add(new ElementalSpellDamagePercent(Elements.None));
                add(new ElementalSpellDamageFlat(Elements.None));
                add(new ElementalResistFlat(Elements.None));
                add(new ElementalSpellDamageMulti(Elements.None));
                add(new ElementalPeneFlat(Elements.None));
                add(new ElementalPenePercent(Elements.None));
                add(new ElementalFocusFlat(Elements.None));

            }
        }
    };

    public static void init() {

        for (StatMod mod : nonGenerated) {
            All.put(mod.GUID(), mod);
        }

        for (IGenerated<StatMod> gen : generated) {
            for (StatMod statmod : gen.generateAllPossibleStatVariations()) {
                All.put(statmod.GUID(), statmod);
            }
        }

        // this makes all StatMod classes for traits cus they are all the same!
        for (Stat stat : Stats.All.values()) {
            if (stat instanceof BaseTrait) {
                BaseTrait trait = (BaseTrait) stat;
                AllTraitMods traitMod = new AllTraitMods(trait);
                StatMods.All.put(traitMod.GUID(), traitMod);
            }
        }
        // how didnt i think of this before.

    }

}

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
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.*;
import com.robertx22.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleResistFlat;
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
import com.robertx22.database.stats.stat_mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessManaMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.ManaMulti;
import com.robertx22.database.stats.stat_mods.percent.*;
import com.robertx22.database.stats.stat_mods.percent.less.*;
import com.robertx22.database.stats.stat_mods.percent.much_less.*;
import com.robertx22.database.stats.stat_mods.percent.offense.*;
import com.robertx22.database.stats.stat_mods.spell_buffs.*;
import com.robertx22.database.stats.stat_mods.traits.*;
import com.robertx22.database.stats.stat_mods.traits.atronachs.EarthAtronachFlat;
import com.robertx22.database.stats.stat_mods.traits.atronachs.FireAtronachFlat;
import com.robertx22.database.stats.stat_mods.traits.atronachs.FrostAtronachFlat;
import com.robertx22.database.stats.stat_mods.traits.atronachs.ThunderAtronachFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.high_crit.HighCritAddArmorFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.high_crit.HighCritAddLifestealFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.high_dodge.HighDodgeAddCritDamageFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.high_dodge.HighDodgeAddPhysDamageFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_crit_hit.LowCritAddDodgeFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_crit_hit.LowCritAddHealthFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddCritHitFlat;
import com.robertx22.database.stats.stat_mods.traits.ele_lords.LordOfBlizzardsFlat;
import com.robertx22.database.stats.stat_mods.traits.ele_lords.LordOfEarthquakesFlat;
import com.robertx22.database.stats.stat_mods.traits.ele_lords.LordOfThunderstormsFlat;
import com.robertx22.database.stats.stat_mods.traits.ele_lords.LordOfVolcanoesFlat;
import com.robertx22.database.stats.stat_mods.traits.major_arcana.*;
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
                put(new AllAttributesFlat().GUID(), new AllAttributesFlat());
                put(new AllEleResistFlat().GUID(), new AllEleResistFlat());

                put(new AllEleDmgFlat().GUID(), new AllEleDmgFlat());
                put(new AllEleSpellDmgFlat().GUID(), new AllEleSpellDmgFlat());

                put(new BlockStrengthPercent().GUID(), new BlockStrengthPercent());
                put(new SpellDamageFlat().GUID(), new SpellDamageFlat());
                put(new SpellDamagePercent().GUID(), new SpellDamagePercent());

                // major arcana tarot
                put(new JudgementFlat().GUID(), new JudgementFlat());
                put(new StrengthArcanaFlat().GUID(), new StrengthArcanaFlat());
                put(new HermitFlat().GUID(), new HermitFlat());
                put(new ChariotFlat().GUID(), new ChariotFlat());
                put(new DeathFlat().GUID(), new DeathFlat());
                put(new HangedManFlat().GUID(), new HangedManFlat());
                put(new JusticeFlat().GUID(), new JusticeFlat());
                put(new HighPriestessFlat().GUID(), new HighPriestessFlat());
                put(new TemperanceFlat().GUID(), new TemperanceFlat());
                put(new TheEmperorFlat().GUID(), new TheEmperorFlat());
                put(new TheDevilFlat().GUID(), new TheDevilFlat());
                put(new TheEmpressFlat().GUID(), new TheEmpressFlat());
                put(new TheFoolFlat().GUID(), new TheFoolFlat());
                put(new TheHierophantFlat().GUID(), new TheHierophantFlat());
                put(new TheMagicianFlat().GUID(), new TheMagicianFlat());
                put(new TheMoonFlat().GUID(), new TheMoonFlat());
                put(new TheSunFlat().GUID(), new TheSunFlat());
                put(new TheWorldFlat().GUID(), new TheWorldFlat());
                put(new TowerFlat().GUID(), new TowerFlat());
                put(new WheelOfFortuneFlat().GUID(), new WheelOfFortuneFlat());
                put(new TheStarFlat().GUID(), new TheStarFlat());
                // major arcana tarot

                put(new StrengthFlat().GUID(), new StrengthFlat());
                put(new DexterityFlat().GUID(), new DexterityFlat());
                put(new WisdomFlat().GUID(), new WisdomFlat());
                put(new IntelligenceFlat().GUID(), new IntelligenceFlat());
                put(new StaminaFlat().GUID(), new StaminaFlat());
                put(new VitalityFlat().GUID(), new VitalityFlat());

                put(new BonusExpFlat().GUID(), new BonusExpFlat());

                // spell buffs

                put(new HomingFlat().GUID(), new HomingFlat());
                put(new GhostProjectileFlat().GUID(), new GhostProjectileFlat());
                put(new ZephyrFlat().GUID(), new ZephyrFlat());
                put(new LightFlat().GUID(), new LightFlat());
                put(new PurityFlat().GUID(), new PurityFlat());
                put(new EnergyRegenBuffFlat().GUID(), new EnergyRegenBuffFlat());
                put(new ManaRegenBuffFlat().GUID(), new ManaRegenBuffFlat());

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

                // lord Flats
                put(new LordOfVolcanoesFlat().GUID(), new LordOfVolcanoesFlat());
                put(new LordOfBlizzardsFlat().GUID(), new LordOfBlizzardsFlat());
                put(new LordOfThunderstormsFlat().GUID(), new LordOfThunderstormsFlat());
                put(new LordOfEarthquakesFlat().GUID(), new LordOfEarthquakesFlat());

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

                // Traits
                put(new GolemFlat().GUID(), new GolemFlat());
                put(new ElementalFlat().GUID(), new ElementalFlat());
                put(new ClumsyScholarFlat().GUID(), new ClumsyScholarFlat());
                put(new DiseasedFlat().GUID(), new DiseasedFlat());
                put(new CrippledFlat().GUID(), new CrippledFlat());
                put(new BarbarianFlat().GUID(), new BarbarianFlat());
                put(new EarthAtronachFlat().GUID(), new EarthAtronachFlat());
                put(new FireAtronachFlat().GUID(), new FireAtronachFlat());
                put(new FrostAtronachFlat().GUID(), new FrostAtronachFlat());
                put(new ThunderAtronachFlat().GUID(), new ThunderAtronachFlat());
                put(new LuckyFlat().GUID(), new LuckyFlat());
                put(new ArmoredFlat().GUID(), new ArmoredFlat());

                // conditional traits

                put(new LowDodgeAddArmorFlat().GUID(), new LowDodgeAddArmorFlat());
                put(new LowDodgeAddCritHitFlat().GUID(), new LowDodgeAddCritHitFlat());
                put(new LowCritAddDodgeFlat().GUID(), new LowCritAddDodgeFlat());
                put(new LowCritAddHealthFlat().GUID(), new LowCritAddHealthFlat());

                put(new HighCritAddArmorFlat().GUID(), new HighCritAddArmorFlat());
                put(new HighCritAddLifestealFlat().GUID(), new HighCritAddLifestealFlat());
                put(new HighDodgeAddCritDamageFlat().GUID(), new HighDodgeAddCritDamageFlat());
                put(new HighDodgeAddPhysDamageFlat().GUID(), new HighDodgeAddPhysDamageFlat());

                // Traits

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

            }
        }
    };

    private static List<IGenerated<StatMod>> generated = new ArrayList<IGenerated<StatMod>>() {
        {
            {
                add(new ElementalConversionFlat(Elements.None, Elements.None));
                add(new ElementalTransferFlat(Elements.None, Elements.None));
                add(new ElementalAffinityFlat(Elements.None));
                add(new XBonusLootDropFlat(LootType.NormalItem));
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

            }
        }
    };

    static {
        for (IGenerated<StatMod> gen : generated) {
            for (StatMod statmod : gen.generateAllPossibleStatVariations()) {
                All.put(statmod.GUID(), statmod);
            }

        }
    }

}

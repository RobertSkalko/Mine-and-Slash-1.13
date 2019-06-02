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
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
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
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllFireDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllNatureDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllThunderDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllWaterDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellFireDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellNatureDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellThunderDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellWaterDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.offence.LessPhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessHealthRegenMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessManaMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.ManaMulti;
import com.robertx22.database.stats.stat_mods.percent.*;
import com.robertx22.database.stats.stat_mods.percent.less.*;
import com.robertx22.database.stats.stat_mods.percent.much_less.*;
import com.robertx22.database.stats.stat_mods.percent.offense.*;
import com.robertx22.database.stats.stat_mods.percent.pene.FirePenePercent;
import com.robertx22.database.stats.stat_mods.percent.pene.NaturePenePercent;
import com.robertx22.database.stats.stat_mods.percent.pene.ThunderPenePercent;
import com.robertx22.database.stats.stat_mods.percent.pene.WaterPenePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
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

                put(new SpellWaterDamageMulti().GUID(), new SpellWaterDamageMulti());
                put(new SpellFireDamageMulti().GUID(), new SpellFireDamageMulti());
                put(new SpellThunderDamageMulti().GUID(), new SpellThunderDamageMulti());
                put(new SpellNatureDamageMulti().GUID(), new SpellNatureDamageMulti());

                put(new AllWaterDamageMulti().GUID(), new AllWaterDamageMulti());
                put(new AllFireDamageMulti().GUID(), new AllFireDamageMulti());
                put(new AllThunderDamageMulti().GUID(), new AllThunderDamageMulti());
                put(new AllNatureDamageMulti().GUID(), new AllNatureDamageMulti());

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

                // Elements
                put(new SpellFireDamageFlat().GUID(), new SpellFireDamageFlat());
                put(new SpellWaterDamageFlat().GUID(), new SpellWaterDamageFlat());
                put(new SpellThunderDamageFlat().GUID(), new SpellThunderDamageFlat());
                put(new SpellNatureDamageFlat().GUID(), new SpellNatureDamageFlat());

                put(new FireResistFlat().GUID(), new FireResistFlat());
                put(new WaterResistFlat().GUID(), new WaterResistFlat());
                put(new ThunderResistFlat().GUID(), new ThunderResistFlat());
                put(new NatureResistFlat().GUID(), new NatureResistFlat());

                put(new FirePeneFlat().GUID(), new FirePeneFlat());
                put(new WaterPeneFlat().GUID(), new WaterPeneFlat());
                put(new ThunderPeneFlat().GUID(), new ThunderPeneFlat());
                put(new NaturePeneFlat().GUID(), new NaturePeneFlat());

                put(new FirePenePercent().GUID(), new FirePenePercent());
                put(new WaterPenePercent().GUID(), new WaterPenePercent());
                put(new ThunderPenePercent().GUID(), new ThunderPenePercent());
                put(new NaturePenePercent().GUID(), new NaturePenePercent());

                put(new SpellFireDamagePercent().GUID(), new SpellFireDamagePercent());
                put(new SpellWaterDamagePercent().GUID(), new SpellWaterDamagePercent());
                put(new SpellThunderDamagePercent().GUID(), new SpellThunderDamagePercent());
                put(new SpellNatureDamagePercent().GUID(), new SpellNatureDamagePercent()); // Elements

                put(new ArmorPercent().GUID(), new ArmorPercent());
                put(new DodgeFlat().GUID(), new DodgeFlat());

                // bonus dmg
                put(new WaterSpellToAttackFlat().GUID(), new WaterSpellToAttackFlat());
                put(new FireSpellToAttackFlat().GUID(), new FireSpellToAttackFlat());
                put(new ThunderSpellToAttackFlat().GUID(), new ThunderSpellToAttackFlat());
                put(new NatureSpellToAttackFlat().GUID(), new NatureSpellToAttackFlat());
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
                add(new BaseConversionFlat());
                add(new BaseTransferFlat());
                add(new XElementAffinityFlat());
                add(new XBonusLootDropFlat());
                add(new WeaponDamageFlat());
                add(new LessWeaponDamageFlat());
                add(new ElementalAttackDamageFlat());

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

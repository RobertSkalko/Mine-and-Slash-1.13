package com.robertx22.db_lists;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.UnknownStat;
import com.robertx22.database.stats.stat_types.core_stats.*;
import com.robertx22.database.stats.stat_types.defense.Armor;
import com.robertx22.database.stats.stat_types.defense.BlockStrength;
import com.robertx22.database.stats.stat_types.defense.Dodge;
import com.robertx22.database.stats.stat_types.defense.SpellDodge;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllEleDmg;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllEleSpellDmg;
import com.robertx22.database.stats.stat_types.generated.*;
import com.robertx22.database.stats.stat_types.misc.BonusExp;
import com.robertx22.database.stats.stat_types.offense.*;
import com.robertx22.database.stats.stat_types.resources.*;
import com.robertx22.database.stats.stat_types.resources.conversions.EnergyToManaConversion;
import com.robertx22.database.stats.stat_types.resources.conversions.ManaToEnergyConversion;
import com.robertx22.database.stats.stat_types.spell_buff_traits.*;
import com.robertx22.database.stats.stat_types.traits.*;
import com.robertx22.database.stats.stat_types.traits.atronachs.EarthAtronach;
import com.robertx22.database.stats.stat_types.traits.atronachs.FireAtronach;
import com.robertx22.database.stats.stat_types.traits.atronachs.FrostAtronach;
import com.robertx22.database.stats.stat_types.traits.atronachs.ThunderAtronach;
import com.robertx22.database.stats.stat_types.traits.bad_and_good.Barbarian;
import com.robertx22.database.stats.stat_types.traits.bad_and_good.ClumsyScholar;
import com.robertx22.database.stats.stat_types.traits.bad_ones.Crippled;
import com.robertx22.database.stats.stat_types.traits.bad_ones.Diseased;
import com.robertx22.database.stats.stat_types.traits.cause_stats.OnDodgeBuffSpeed;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfBlizzardsTrait;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfEarthquakesTrait;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfThunderstormsTrait;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfVolcanoesTrait;
import com.robertx22.database.stats.stat_types.traits.high_crit.HighCritAddArmor;
import com.robertx22.database.stats.stat_types.traits.high_crit.HighCritAddLifesteal;
import com.robertx22.database.stats.stat_types.traits.high_dodge.HighDodgeAddCritDamage;
import com.robertx22.database.stats.stat_types.traits.high_dodge.HighDodgeAddPhysDamage;
import com.robertx22.database.stats.stat_types.traits.low_crit_hit.LowCritHitAddDodge;
import com.robertx22.database.stats.stat_types.traits.low_crit_hit.LowCritHitAddHealth;
import com.robertx22.database.stats.stat_types.traits.low_dodge.LowDodgeAddArmor;
import com.robertx22.database.stats.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.database.stats.stat_types.traits.major_arcana.*;
import com.robertx22.db_lists.bases.AllPreGenMapStats;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stats {

    public static HashMap<String, Stat> All = new HashMap<String, Stat>() {
        {
            {
                put(new AllAttributes().GUID(), new AllAttributes());
                put(new AllEleDmg().GUID(), new AllEleDmg());
                put(new AllEleSpellDmg().GUID(), new AllEleSpellDmg());
                put(new SpellDamage().GUID(), new SpellDamage());
                // major arcana tarot
                put(new Judgement().GUID(), new Judgement());
                put(new StrengthArcana().GUID(), new StrengthArcana());
                put(new Hermit().GUID(), new Hermit());
                put(new Chariot().GUID(), new Chariot());
                put(new Death().GUID(), new Death());
                put(new HangedMan().GUID(), new HangedMan());
                put(new Justice().GUID(), new Justice());
                put(new HighPriestess().GUID(), new HighPriestess());
                put(new Temperance().GUID(), new Temperance());
                put(new TheEmperor().GUID(), new TheEmperor());
                put(new TheDevil().GUID(), new TheDevil());
                put(new TheEmpress().GUID(), new TheEmpress());
                put(new TheFool().GUID(), new TheFool());
                put(new TheHierophant().GUID(), new TheHierophant());
                put(new TheMagician().GUID(), new TheMagician());
                put(new TheMoon().GUID(), new TheMoon());
                put(new TheSun().GUID(), new TheSun());
                put(new TheWorld().GUID(), new TheWorld());
                put(new Tower().GUID(), new Tower());
                put(new WheelOfFortune().GUID(), new WheelOfFortune());
                put(new TheStar().GUID(), new TheStar());
                // major arcana tarot

                put(new Strength().GUID(), new Strength());
                put(new Dexterity().GUID(), new Dexterity());
                put(new Wisdom().GUID(), new Wisdom());
                put(new Intelligence().GUID(), new Intelligence());
                put(new Stamina().GUID(), new Stamina());
                put(new Vitality().GUID(), new Vitality());

                put(new BonusExp().GUID(), new BonusExp());

                // spell buffs
                put(new HomingTrait().GUID(), new HomingTrait());
                put(new GhostProjectileTrait().GUID(), new GhostProjectileTrait());
                put(new ZephyrTrait().GUID(), new ZephyrTrait());
                put(new LightTrait().GUID(), new LightTrait());
                put(new PurityTrait().GUID(), new PurityTrait());
                put(new BuffEnergyRegenTrait().GUID(), new BuffEnergyRegenTrait());
                put(new BuffManaRegenTrait().GUID(), new BuffManaRegenTrait());

                // spell buffs

                put(new EnergyToManaConversion().GUID(), new EnergyToManaConversion());
                put(new ManaToEnergyConversion().GUID(), new ManaToEnergyConversion());

                // conditional traits
                put(new HighCritAddArmor().GUID(), new HighCritAddArmor());
                put(new HighCritAddLifesteal().GUID(), new HighCritAddLifesteal());
                put(new HighDodgeAddCritDamage().GUID(), new HighDodgeAddCritDamage());
                put(new HighDodgeAddPhysDamage().GUID(), new HighDodgeAddPhysDamage());

                put(new LowDodgeAddArmor().GUID(), new LowDodgeAddArmor());
                put(new LowDodgeAddCritHit().GUID(), new LowDodgeAddCritHit());
                put(new LowCritHitAddDodge().GUID(), new LowCritHitAddDodge());
                put(new LowCritHitAddHealth().GUID(), new LowCritHitAddHealth());

                // lord traits
                put(new LordOfVolcanoesTrait().GUID(), new LordOfVolcanoesTrait());
                put(new LordOfBlizzardsTrait().GUID(), new LordOfBlizzardsTrait());
                put(new LordOfThunderstormsTrait().GUID(), new LordOfThunderstormsTrait());
                put(new LordOfEarthquakesTrait().GUID(), new LordOfEarthquakesTrait());

                // weapon damages

                put(UnknownStat.GUID, new UnknownStat());
                // Resources
                put(Health.GUID, new Health());
                put(HealthRegen.GUID, new HealthRegen());
                put(Lifesteal.GUID, new Lifesteal());
                put(LifeOnHit.GUID, new LifeOnHit());

                put(Mana.GUID, new Mana());
                put(ManaRegen.GUID, new ManaRegen());
                put(ManaOnHit.GUID, new ManaOnHit());

                put(Energy.GUID, new Energy());
                put(EnergyRegen.GUID, new EnergyRegen());
                // Resources

                put(BlockStrength.GUID, new BlockStrength());

                put(Armor.GUID, new Armor());
                put(ArmorPenetration.GUID, new ArmorPenetration());
                put(CriticalDamage.GUID, new CriticalDamage());
                put(CriticalHit.GUID, new CriticalHit());
                put(PhysicalDamage.GUID, new PhysicalDamage());

                put(Dodge.GUID, new Dodge());
                put(SpellDodge.GUID, new SpellDodge());

                // traits
                put(Golem.GUID, new Golem());
                put(Elemental.GUID, new Elemental());
                put(Lucky.GUID, new Lucky());
                put(Barbarian.GUID, new Barbarian());
                put(Stealthy.GUID, new Stealthy());
                put(ClumsyScholar.GUID, new ClumsyScholar());
                put(Crippled.GUID, new Crippled());
                put(Diseased.GUID, new Diseased());
                put(Armored.GUID, new Armored());

                put(EarthAtronach.GUID, new EarthAtronach());
                put(FrostAtronach.GUID, new FrostAtronach());
                put(FireAtronach.GUID, new FireAtronach());
                put(ThunderAtronach.GUID, new ThunderAtronach());

                put(new OnDodgeBuffSpeed().GUID(), new OnDodgeBuffSpeed());
                // traits

            }
        }
    };

    private static List<IGenerated<Stat>> generated = new ArrayList<IGenerated<Stat>>() {
        {
            {
                add(new ElementalConversion(Elements.None, Elements.None));
                add(new ElementalTransfer(Elements.None, Elements.None));
                add(new ElementalAffinity(Elements.None));
                add(new LootTypeBonusFlat(LootType.NormalItem));
                add(new WeaponDamage(WeaponTypes.None));
                add(new ElementalAttackDamage(Elements.None));
                add(new ElementalSpellToAttackDMG(Elements.None));
                add(new AllElementalDamage(Elements.None));
                add(new ElementalSpellDamage(Elements.None));
                add(new ElementalResist(Elements.None));
                add(new ElementalPene(Elements.None));
                add(new ElementalFocus(Elements.None));

            }
        }
    };

    public static AllPreGenMapStats allPreGenMapStatLists = new AllPreGenMapStats();

    public static void init() {
        for (IGenerated<Stat> gen : generated) {
            for (Stat stat : gen.generateAllPossibleStatVariations()) {
                All.put(stat.GUID(), stat);
            }
        }

    }

}

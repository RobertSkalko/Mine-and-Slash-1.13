package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.interfaces.IAutoLocName;

public enum Words implements IAutoLocName {

    ActivationTime(),
    AffixesAffectingAll(),
    Animal(),
    Attack(),
    BaseValue(),
    Works_when_equipped,
    Blocks(),
    Automatically_salvages_items,
    BonusLootAmount(),
    BonusSalvageChance(),
    BonusSuccessRate(),
    By(),
    ChaosStats(),
    Cooldown(),
    Currency(),
    Damage(),
    Dealt(),
    Defenses(),
    Distance(),
    Empty(),
    From(),
    Fuel(),
    Gears(),
    Infusion(),
    Item(),
    Left(),
    Level(),
    MajorArcana(),
    MajorFailureChance(),
    MajorSuccessBonus(),
    MajorSuccessChance(),
    ManaCost(),
    Map(),
    Maps(),
    Minutes(),
    Misc(),
    Mob(),
    MobAffixes(),
    Multi(),
    None(),
    NotAMapWorld(),
    Npc(),
    Percent(),
    Permadeath(),
    PlayerAffixes(),
    Position(),
    Prefix(),
    PrimaryStats(),
    Progress(),
    PutMap(),
    Rarity(),
    Regeneration(),
    Resources(),
    Runed(),
    Runes(),
    Runeword(),
    SacrificeMapForLevel(),
    SacrificeMapForTier(),
    ScalingValue(),
    SecondaryStats(),
    Seconds(),
    Set(),
    Socket(),
    Sockets(),
    Spell(),
    SpellDamage(),
    Spells(),
    Start(),
    Stats(),
    Suffix(),
    Tier(),
    To(),
    Took(),
    Type(),
    UniqueStats(),
    Unsalvagable(),
    UseTime(),
    Uses(),
    WorldType();

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Words;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".word." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return this.name().replaceAll("(.)([A-Z])", "$1 $2");
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}

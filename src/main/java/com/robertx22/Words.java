package com.robertx22;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import net.minecraft.util.text.ITextComponent;

public enum Words implements IAutoLocName {
    Percent(),
    Runeword(),
    Set(),
    UseTime(),
    Permadeath(),
    Type(),
    Distance(),
    Position(),
    Blocks(),
    Prefix(),
    Suffix(),
    Stats(),
    PrimaryStats(),
    SecondaryStats(),
    ChaosStats(),
    UniqueStats(),
    Level(),
    Seconds(),
    None(),
    Rarity(),
    Unsalvagable(),
    Multi(),
    Tier(),
    BonusLootAmount(),
    Minutes(),
    PlayerAffixes(),
    MobAffixes(),
    AffixesAffectingAll(),
    WorldType(),
    ManaCost(),
    BaseValue(),
    ScalingValue(),
    By(),
    Resources(),
    Regeneration(),
    Attack(),
    SpellDamage(),
    Defenses(),
    Misc(),
    PutMap(),
    NotAMapWorld(),
    SacrificeMapForTier(),
    SacrificeMapForLevel(),
    Start(),
    Dealt(),
    Took(),
    From(),
    To(),
    Damage(),
    Progress(),
    Fuel(),
    Npc(),
    Mob(),
    Animal(),
    Item(),
    Currency(),
    Map(),
    Spell(),
    Socket(),
    Sockets(),
    Empty(),
    Infusion(),
    BonusSuccessRate(),
    MajorSuccessChance(),
    MajorFailureChance(),
    Gears(),
    Spells(),
    Runes(),
    Maps(),
    Cooldown(),
    Uses(),
    Left(),
    ActivationTime(),
    BonusSalvageChance(),
    Runed(),
    MajorSuccessBonus(),
    MajorArcana();

    public String translate() {
        return CLOC.translate(this.locName());
    }

    @Override
    public String locNameLangFileGUID(String guid) {
        return Ref.MODID + ".word." + guid;
    }

    @Override
    public String locNameForLangFile() {
        return this.name().replaceAll("(.)([A-Z])", "$1 $2");
    }

    @Override
    public ITextComponent locName() {
        return CLOC.blank(locNameLangFileGUID(formattedGUID()));
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}

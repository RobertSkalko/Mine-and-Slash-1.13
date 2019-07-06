package com.robertx22.uncommon.localization;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.interfaces.IAutoLocName;

public enum AdvDescs implements IAutoLocName {

    WelcomeMineandslash("This mod is all about killing mobs, looting epic gear to kill more mobs!"),
    RepairStation("Repair Station is used to repair your gear. Put ores (common, uncommon etc) into the fuel slot and then put items to repair left. They can also have a capacitor that decreases the repair cost"),
    ModifyStation("Modify Station is used to modify gears with currencies. Currencies have special effects like adding a prefix, rerolling a suffix etc. This station can also insert runes, awaken runewords etc."),
    SalvageStation("Turns items into ores and sometimes currencies."),
    FactoryStation("Turns bad gears into items of higher rarity."),
    CurrencyBag("Currency Bag automatically picks up Mine and Slash ores, currencies etc"),
    MapBag("Map Bag automatically picks up adventure maps"),
    LootBag("Loot Bag automatically picks up gears, spells, runes etc"),
    LevelUp10("Starting at lvl 10, mobs will start dropping currencies and adventure maps (per default config)."),
    LevelUp("Every level milestone allows items to spawn with new and better affixes, sets and so on. Loot new gear to find out and power up!"),

    ChaosOrb("Gives Chaos Stats. These Stats can be good or bad. They either can't be removed or the removal is extremely rare and expensive."),
    Stoneofhope("Recreates the item completely, giving you a higher rarity. Very useful if you have a legendary item with bad/unwanted stats."),
    AddAffix("Unique items have lower chance to gain affixes, that's why these currencies are so important"),

    MapDevice("This device allows you to sacrifice an adventure map and a seed, to gain time that allows you to enter adventure maps, gain special rewards like unique items and currencies, progress through tiers for better rewards!"),

    MasterBag("Master Bag automatically picks up almost all Mine and Slash loot drops and each is separated into a different container for easy sorting.");

    private String localization = "";

    AdvDescs(String str) {
        this.localization = str;

    }

    @Override
    public IAutoLocName.AutoLocGroup locNameGroup() {
        return AutoLocGroup.Advancement_descriptions;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".advancement.desc." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}

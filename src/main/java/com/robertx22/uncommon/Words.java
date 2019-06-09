package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.interfaces.IAutoLocName;

public enum Words implements IAutoLocName {
    Penetration("Penetration"),
    Map_Device("Map Device"),
    Core_Stat("Core Stat"),
    Elemental_Attack_Damage("Elemental Attack Damage"),
    Main("Main"),
    Activation_Time("Activation Time"),
    Affixes_Affecting_All("Affixes Affecting All"),
    Animal("Animal"),
    Attack("Attack"),
    BaseValue("BaseValue"),
    Works_when_equipped("Works when equipped"),
    Blocks("Blocks"),
    Automatically_salvages_items("Automatically salvages items"),
    Bonus_Loot_Amount("Bonus Loot Amount"),
    Bonus_Salvage_Chance("Bonus Salvage Chance"),
    Bonus_Success_Rate("Bonus Success Rate"),
    By("By"),
    Chaos_Stats("Chaos Stats"),
    Cooldown("Cooldown"),
    Currency("Currency"),
    Damage("Damage"),
    Dealt("Dealt"),
    Defenses("Defenses"),
    Distance("Distance"),
    Empty("Empty"),
    From("From"),
    Fuel("Fuel"),
    Gears("Gears"),
    Infusion("Infusion"),
    Item("Item"),
    Left("Left"),
    Level("Level"),
    Major_Arcana("Major Arcana"),
    Major_Failure_Chance("Major Failure Chance"),
    Major_Success_Bonus("Major Success Bonus"),
    Major_Success_Chance("Major Success Chance"),
    Mana_Cost("Mana Cost"),
    Map("Map"),
    Maps("Maps"),
    Minutes("Minutes"),
    Misc("Misc"),
    Mob("Mob"),
    Mob_Affixes("Mob Affixes"),
    Multi("Multi"),
    None("None"),
    Not_a_Map_World("Not a Map World"),
    Npc("Npc"),
    Percent("Percent"),
    Permadeath("Permadeath"),
    Player_Affixes("Player Affixes"),
    Position("Position"),
    Prefix("Prefix"),
    Primary_Stats("Primary Stats"),
    Progress("Progress"),
    Put_Map("Put Map"),
    Rarity("Rarity"),
    Regeneration("Regeneration"),
    Resources("Resources"),
    Runed("Runed"),
    Runes("Runes"),
    Runeword("Runeword"),
    Sacrifice_Map_For_Level("Sacrifice Map For Level"),
    Sacrifice_Map_For_Tier("Sacrifice Map For Tier"),
    Scaling_Value("Scaling Value"),
    Secondary_Stats("Secondary Stats"),
    Seconds("Seconds"),
    Set("Set"),
    Socket("Socket"),
    Sockets("Sockets"),
    Spell("Spell"),
    Spell_Damage("Spell Damage"),
    Spells("Spells"),
    Start("Start"),
    Stats("Stats"),
    Suffix("Suffix"),
    Tier("Tier"),
    To("To"),
    Took("Took"),
    Type("Type"),
    Unique_Stats("Unique Stats"),
    Unsalvagable("Unsalvagable"),
    Use_Time("Use Time"),
    Uses("Uses"),
    Beware("Beware"),
    MapWorldsAreResetOnGameReload("Map Worlds Are Reset On Game Reload"),
    DoNotBuildInMaps("Do not build in maps!"),
    World_Type("World Type"),
    Gear_Modify("Gear Modify");

    private String localization = "";

    Words(String str) {
        this.localization = str;

    }

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
        return localization;
    }

    @Override
    public String GUID() {
        return this.name().toLowerCase();
    }
}

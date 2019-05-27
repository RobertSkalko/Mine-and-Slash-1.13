package com.robertx22.api;

import com.robertx22.config.compatible_items.ConfigItem;
import com.robertx22.config.compatible_items.ConfigItems;
import com.robertx22.database.affixes.BaseAffix;
import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.sets.Set;
import com.robertx22.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.*;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.spells.bases.BaseSpell;
import net.minecraft.item.Item;

public class MineAndSlashAPI {

    public enum GearFamilly {
        Armor, Weapon, Jewerly, All
    }

    public static void addCompatibleItem(String itemID, ConfigItem item) {
        ConfigItems.INSTANCE.map.put(itemID, item);
        ConfigItems.INSTANCE.refreshList();
    }

    public static void addAffix(BaseAffix affix, GearFamilly family) {
        if (affix instanceof Prefix) {

            if (family == GearFamilly.All) {
                Prefixes.Armor.add((Prefix) affix);
                Prefixes.Jewerly.add((Prefix) affix);
                Prefixes.Weapon.add((Prefix) affix);

            } else if (family == GearFamilly.Armor) {
                Prefixes.Armor.add((Prefix) affix);
            } else if (family == GearFamilly.Jewerly) {
                Prefixes.Jewerly.add((Prefix) affix);
            } else {
                Prefixes.Weapon.add((Prefix) affix);
            }
            Prefixes.all.put(affix.GUID(), (Prefix) affix);

        } else {

            if (family == GearFamilly.All) {
                Suffixes.Armor.add((Suffix) affix);
                Suffixes.Jewerly.add((Suffix) affix);
                Suffixes.Weapon.add((Suffix) affix);

            } else if (family == GearFamilly.Armor) {
                Suffixes.Armor.add((Suffix) affix);
            } else if (family == GearFamilly.Jewerly) {
                Suffixes.Jewerly.add((Suffix) affix);
            } else {
                Suffixes.Weapon.add((Suffix) affix);
            }
            Suffixes.all.put(affix.GUID(), (Suffix) affix);
        }

    }

    public static void addMapAffix(BaseMapAffix affix) {
        MapAffixes.All.put(affix.GUID(), affix);
    }

    public static void addRuneWord(RuneWord word) {

        RuneWords.All.put(word.GUID(), word);
    }

    public static void addSet(Set set) {
        Sets.All.put(set.GUID(), set);
    }

    public static void addSpell(BaseSpell spell) {
        Spells.All.put(spell.GUID(), spell);
    }

    public static void addMobEffect(BaseStatusEffect effect) {
        StatusEffects.All.put(effect.GUID(), effect);
    }

    public static void addGearItemType(GearItemSlot type) {
        GearTypes.All.put(type.GUID(), type);
    }

    public static void addRune(BaseRuneItem rune) {
        Runes.All.put(rune.name(), rune);
    }

    public static <T extends Item & IUnique> void addUnique(T unique) {
        UniqueItems.ITEMS.put(unique.GUID(), unique);
    }

}

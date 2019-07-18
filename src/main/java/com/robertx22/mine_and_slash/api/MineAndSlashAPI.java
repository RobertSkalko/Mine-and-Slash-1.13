package com.robertx22.mine_and_slash.api;

import com.robertx22.mine_and_slash.config.compatible_items.ConfigItem;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItems;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.initializers.*;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import net.minecraft.item.Item;

public class MineAndSlashAPI {

    public static void addCompatibleItem(String itemID, ConfigItem item) {
        ConfigItems.INSTANCE.map.put(itemID, item);
        ConfigItems.INSTANCE.refreshList();
    }

    public static void addAffix(BaseAffix affix) {
        if (affix instanceof Prefix) {
            Prefixes.all.put(affix.GUID(), (Prefix) affix);
        } else {
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

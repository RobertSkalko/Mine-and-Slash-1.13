package com.robertx22.mine_and_slash.db_lists.newregistry;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.particle_gens.ParticleGen;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.db_lists.initializers.*;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;

import java.util.HashMap;

public class SlashRegistry {

    public static SlashRegistryContainer<GearItemSlot> GearTypes() {
        return getRegistry(SlashRegistryType.GEAR_TYPE);
    }

    public static SlashRegistryContainer<GearItemSlot> Spells() {
        return getRegistry(SlashRegistryType.SPELL);
    }

    public static SlashRegistryContainer<GearItemSlot> Runes() {
        return getRegistry(SlashRegistryType.RUNE);
    }

    public static SlashRegistryContainer<GearItemSlot> RuneWords() {
        return getRegistry(SlashRegistryType.RUNEWORD);
    }

    public static SlashRegistryContainer<GearItemSlot> WorldProviders() {
        return getRegistry(SlashRegistryType.WORLD_PROVIDER);
    }

    public static SlashRegistryContainer<GearItemSlot> Stats() {
        return getRegistry(SlashRegistryType.STAT);
    }

    public static SlashRegistryContainer<GearItemSlot> StatMods() {
        return getRegistry(SlashRegistryType.STATMOD);
    }

    private static HashMap<SlashRegistryType, SlashRegistryContainer> map = new HashMap<>();

    public static SlashRegistryContainer getRegistry(SlashRegistryType type) {
        return map.get(type);

    }

    public static ISlashRegistryEntry get(SlashRegistryType type, String guid) {
        return getRegistry(type).get(guid);

    }

    public static void init() {
        createRegistries();
        initAllDatabases();

    }

    private static void initAllDatabases() {
        Stats.init(); // STATS MUST BE INIT FIRST CUS STATMODS ARE DERIVED FROM STATS, or should be at least

        StatMods.init();
        Prefixes.init();

        Suffixes.init();
        Sets.init();
        Spells.init();

    }

    private static void createRegistries() {

        map.put(SlashRegistryType.GEAR_TYPE, new SlashRegistryContainer<GearItemSlot>());
        map.put(SlashRegistryType.STAT, new SlashRegistryContainer<Stat>());
        map.put(SlashRegistryType.STATMOD, new SlashRegistryContainer<StatMod>());
        map.put(SlashRegistryType.SET, new SlashRegistryContainer<Set>());
        map.put(SlashRegistryType.SPELL, new SlashRegistryContainer<BaseSpell>());
        map.put(SlashRegistryType.UNIQUE_ITEM, new SlashRegistryContainer<IUnique>());
        map.put(SlashRegistryType.SUFFIX, new SlashRegistryContainer<Suffix>());
        map.put(SlashRegistryType.PREFIX, new SlashRegistryContainer<Prefix>());
        map.put(SlashRegistryType.RUNE, new SlashRegistryContainer<BaseRuneItem>());
        map.put(SlashRegistryType.RUNEWORD, new SlashRegistryContainer<RuneWord>());
        map.put(SlashRegistryType.MAP_AFFIX, new SlashRegistryContainer<BaseMapAffix>());
        map.put(SlashRegistryType.STATUS_EFFECT, new SlashRegistryContainer<BaseStatusEffect>());
        map.put(SlashRegistryType.PARTICLE_GEN, new SlashRegistryContainer<ParticleGen>());
        map.put(SlashRegistryType.WORLD_PROVIDER, new SlashRegistryContainer<BaseWorldProvider>());

    }

}

package com.robertx22.db_lists;

import com.robertx22.database.rarities.*;
import com.robertx22.database.rarities.items.*;
import com.robertx22.database.rarities.maps.*;
import com.robertx22.database.rarities.mobs.*;
import com.robertx22.database.rarities.runed_items.*;
import com.robertx22.database.rarities.runes.*;
import com.robertx22.database.rarities.spells.*;

import java.util.Arrays;
import java.util.List;

public class Rarities {

    public static final int MAXIMUM_ITEM_RARITY = 5;

    public static final List<ItemRarity> RunedItems = Arrays.asList(new CommonRunedItem(), new UncommonRunedItem(), new RareRunedItem(), new EpicRunedItem(), new LegendaryRunedItem(), new MythicalRunedItem());

    public static final List<RuneRarity> Runes = Arrays.asList(new CommonRune(), new UncommonRune(), new RareRune(), new EpicRune(), new LegendaryRune(), new MythicalRune());

    public static final List<ItemRarity> Items = Arrays.asList(new CommonItem(), new UncommonItem(), new RareItem(), new EpicItem(), new LegendaryItem(), new MythicalItem());

    public static final List<MapRarity> Maps = Arrays.asList(new CommonMap(), new UncommonMap(), new RareMap(), new EpicMap(), new LegendaryMap(), new MythicalMap());

    public static final List<MobRarity> Mobs = Arrays.asList(new CommonMob(), new UncommonMob(), new RareMob(), new EpicMob(), new LegendaryMob(), new MythicalMob());

    public static final List<SpellRarity> Spells = Arrays.asList(new CommonSpell(), new UncommonSpell(), new RareSpell(), new EpicSpell(), new LegendarySpell(), new MythicalSpell());

}

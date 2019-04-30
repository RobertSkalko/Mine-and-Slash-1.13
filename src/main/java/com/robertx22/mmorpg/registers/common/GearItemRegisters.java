package com.robertx22.mmorpg.registers.common;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.armor.ItemBoots;
import com.robertx22.items.gearitems.armor.ItemChest;
import com.robertx22.items.gearitems.armor.ItemHelmet;
import com.robertx22.items.gearitems.armor.ItemPants;
import com.robertx22.items.gearitems.baubles.ItemBracelet;
import com.robertx22.items.gearitems.baubles.ItemCharm;
import com.robertx22.items.gearitems.baubles.ItemNecklace;
import com.robertx22.items.gearitems.baubles.ItemRing;
import com.robertx22.items.gearitems.offhands.NormalShield;
import com.robertx22.items.gearitems.weapons.*;
import com.robertx22.items.misc.ItemMap;
import com.robertx22.items.runes.*;
import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EventBusSubscriber
public class GearItemRegisters {

    private static List<Item> items = new ArrayList<Item>();

    private static void register() {

        for (ItemRarity rarity : Rarities.Items) {

            // 1] class 2] rarity hashmap 3] registry name 4] rarity rank

            int rank = rarity.Rank();

            // runes
            regRarities(new CenItem(rank), CenItem.Items, "runes/" + new CenItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new BerItem(rank), BerItem.Items, "runes/" + new BerItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new DosItem(rank), DosItem.Items, "runes/" + new DosItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new GohItem(rank), GohItem.Items, "runes/" + new GohItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new MosItem(rank), MosItem.Items, "runes/" + new MosItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new RahItem(rank), RahItem.Items, "runes/" + new RahItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new VohItem(rank), VohItem.Items, "runes/" + new VohItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new XahItem(rank), XahItem.Items, "runes/" + new XahItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new AnoItem(rank), AnoItem.Items, "runes/" + new AnoItem(rank).name()
                    .toLowerCase(), rank);
            regRarities(new ItaItem(rank), ItaItem.Items, "runes/" + new ItaItem(rank).name()
                    .toLowerCase(), rank);

            // offhands
            regRarities(new NormalShield("normal_shield" + rarity.Rank()), NormalShield.Items, "shields/normal_shield", rarity
                    .Rank());

            // weapons
            regRarities(new ItemSword(), ItemSword.Items, "sword/sword", rarity.Rank());
            regRarities(new ItemHammer(), ItemHammer.Items, "hammer/hammer", rarity.Rank());
            regRarities(new ItemAxe(), ItemAxe.Items, "axe/axe", rarity.Rank());
            regRarities(new ItemBow(), ItemBow.Items, "bow/bow", rarity.Rank());
            regRarities(new ItemStaff(), ItemStaff.Items, "staff/staff", rarity.Rank());

            // baubles
            regRarities(new ItemNecklace(), ItemNecklace.Items, "necklace/necklace", rarity
                    .Rank());
            regRarities(new ItemBracelet(), ItemBracelet.Items, "bracelet/bracelet", rarity
                    .Rank());
            regRarities(new ItemRing(), ItemRing.Items, "ring/ring", rarity.Rank());
            regRarities(new ItemCharm(), ItemCharm.Items, "charm/charm", rarity.Rank());

            // armors
            regRarities(new ItemBoots(rank), ItemBoots.Items, "boots/boots", rarity.Rank());
            regRarities(new ItemChest(rank), ItemChest.Items, "chest/chest", rarity.Rank());
            regRarities(new ItemHelmet(rank), ItemHelmet.Items, "helmet/helmet", rarity.Rank());
            regRarities(new ItemPants(rank), ItemPants.Items, "pants/pants", rarity.Rank());

            // misc
            regRarities(new ItemMap(), ItemMap.Items, "map/map", rarity.Rank()); // not gearitem but yeah

        }

    }

    private static void regRarities(Item item, HashMap<Integer, Item> map, String name,
                                    int rarity) {

        String reg = name + rarity;
        item.setRegistryName(reg);
        map.put(rarity, item);
        items.add(item);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        System.out.println("Registering Gear Items!");

        register();

        for (Item item : items) {
            event.getRegistry().register(item);
        }

    }

}

package com.robertx22.advacements;

import com.robertx22.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.items.bags.loot_bag.ItemLootBag;
import com.robertx22.items.bags.map_bag.ItemMapBag;
import com.robertx22.items.bags.master_bag.ItemMasterBag;
import com.robertx22.items.gearitems.weapons.ItemHammer;
import com.robertx22.items.gearitems.weapons.ItemSword;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.uncommon.localization.AdvDescs;
import com.robertx22.uncommon.localization.AdvTitles;
import com.robertx22.uncommon.localization.Words;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class MyAdvancements implements Consumer<Consumer<Advancement>> {

    public void accept(Consumer<Advancement> consu) {
        Advancement parent = Advancement.Builder.builder()
                .withDisplay(ItemSword.Items.get(5), AdvTitles.WelcomeMineandslash.locName(), AdvDescs.WelcomeMineandslash
                        .locName(), new ResourceLocation("textures/gui/advancements/backgrounds/end.png"), FrameType.TASK, false, false, false)
                .withCriterion("crafting_table", InventoryChangeTrigger.Instance.forItems(Blocks.CRAFTING_TABLE))
                .register(consu, id("root"));

        Advancement repair = itemAdv(AdvTitles.RepairStation, AdvDescs.RepairStation, "repair", parent, consu, BlockRegister.BLOCK_GEAR_REPAIR);
        Advancement modify = itemAdv(AdvTitles.ModifyStation, AdvDescs.ModifyStation, "modify", parent, consu, BlockRegister.BLOCK_GEAR_MODIFY);
        Advancement salvage = itemAdv(AdvTitles.SalvageStation, AdvDescs.SalvageStation, "salvage", parent, consu, BlockRegister.BLOCK_GEAR_SALVAGE);
        Advancement factory = itemAdv(AdvTitles.FactoryStation, AdvDescs.FactoryStation, "factory", parent, consu, BlockRegister.BLOCK_GEAR_FACTORY);

        Advancement currency_bag = itemAdv(AdvTitles.CurrencyBag, AdvDescs.CurrencyBag, "currency_bag", repair, consu, ItemCurrencyBag.ITEM);
        Advancement map_bag = itemAdv(AdvTitles.MapBag, AdvDescs.MapBag, "map_bag", repair, consu, ItemMapBag.ITEM);
        Advancement loot_bag = itemAdv(AdvTitles.LootBag, AdvDescs.LootBag, "loot_bag", repair, consu, ItemLootBag.ITEM);
        Advancement master_bag = itemAdv(AdvTitles.MasterBag, AdvDescs.MasterBag, "master_bag", repair, consu, ItemMasterBag.ITEM);

        Advancement lvl_10 = levelAdv(10, AdvDescs.LevelUp10, parent, consu, ItemHammer.Items
                .get(0));
        Advancement lvl_25 = levelAdv(20, AdvDescs.LevelUp, lvl_10, consu, ItemHammer.Items
                .get(1));
        Advancement lvl_50 = levelAdv(50, AdvDescs.LevelUp, lvl_25, consu, ItemHammer.Items
                .get(2));
        Advancement lvl_75 = levelAdv(75, AdvDescs.LevelUp, lvl_50, consu, ItemHammer.Items
                .get(3));
        Advancement lvl_90 = levelAdv(90, AdvDescs.LevelUp, lvl_75, consu, ItemHammer.Items
                .get(4));
        Advancement lvl_100 = levelAdv(100, AdvDescs.LevelUp, lvl_90, consu, ItemHammer.Items
                .get(5));

        Advancement map_device = itemAdv(AdvTitles.MapDevice, AdvDescs.MapDevice, "map_device", lvl_10, consu, BlockRegister.BLOCK_MAP_DEVICE);

    }

    private Advancement levelAdv(int lvl, AdvDescs desc, Advancement parent,
                                 Consumer<Advancement> consumerAdv, IItemProvider item) {
        String id = "player_level_" + lvl;

        Advancement adv = Advancement.Builder.builder()
                .withParent(parent)
                .withDisplay(item, Words.Level.locName()
                        .appendText(": " + lvl), desc.locName(), null, FrameType.CHALLENGE, true, true, false)
                .withCriterion(id, new PlayerLevelTrigger.Instance(lvl))
                .register(consumerAdv, id(id));

        return adv;
    }

    private Advancement itemAdv(AdvTitles title, AdvDescs desc, String id,
                                Advancement parent, Consumer<Advancement> consumerAdv,
                                IItemProvider item) {
        Advancement adv = Advancement.Builder.builder()
                .withParent(parent)
                .withDisplay(item, title.locName(), desc.locName(), null, FrameType.TASK, true, true, false)
                .withCriterion("gained_" + id, InventoryChangeTrigger.Instance.forItems(item))
                .register(consumerAdv, id(id));

        return adv;
    }

    private String id(String str) {
        return Ref.MODID + ":mine_and_slash/" + str;
    }

}


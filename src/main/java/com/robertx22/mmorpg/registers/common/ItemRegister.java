package com.robertx22.mmorpg.registers.common;

import com.robertx22.TomeItem;
import com.robertx22.TomeRenderer;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.database.unique_items.UniqueItemRegister;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.items.bags.AutoSalvageBag;
import com.robertx22.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.items.bags.loot_bag.ItemLootBag;
import com.robertx22.items.bags.map_bag.ItemMapBag;
import com.robertx22.items.currency.*;
import com.robertx22.items.infusions.AttackInfusionItem;
import com.robertx22.items.infusions.DefenseInfusionItem;
import com.robertx22.items.infusions.ResourceInfusionItem;
import com.robertx22.items.infusions.upgrade.NormalUpgradeInfusion;
import com.robertx22.items.infusions.upgrade.SuperiorUpgradeInfusion;
import com.robertx22.items.infusions.upgrade.WondrousUpgradeInfusion;
import com.robertx22.items.level_incentives.Hearthstone;
import com.robertx22.items.misc.*;
import com.robertx22.items.spells.aoe_bomb_proj.ItemAcidBomb;
import com.robertx22.items.spells.aoe_bomb_proj.ItemFireBomb;
import com.robertx22.items.spells.aoe_bomb_proj.ItemIceBomb;
import com.robertx22.items.spells.aoe_bomb_proj.ItemThunderBomb;
import com.robertx22.items.spells.aoe_projectile.ItemAcidExplosion;
import com.robertx22.items.spells.aoe_projectile.ItemFlameExplosion;
import com.robertx22.items.spells.aoe_projectile.ItemFrostExplosion;
import com.robertx22.items.spells.aoe_projectile.ItemLightningExplosion;
import com.robertx22.items.spells.nova.ItemFireNova;
import com.robertx22.items.spells.nova.ItemFrostNova;
import com.robertx22.items.spells.nova.ItemPoisonNova;
import com.robertx22.items.spells.nova.ItemThunderNova;
import com.robertx22.items.spells.projectile.ItemAcidBolt;
import com.robertx22.items.spells.projectile.ItemFireBolt;
import com.robertx22.items.spells.projectile.ItemFrostBolt;
import com.robertx22.items.spells.projectile.ItemThunderBolt;
import com.robertx22.items.spells.self.ItemInstantHeal;
import com.robertx22.items.spells.self.ItemSelfRegen;
import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        putInLists();
        registerSingles(event);
        registerLists(event);
    }

    private static void putInLists() {

        UniqueItemRegister.register();

        for (ItemRarity x : Rarities.Items.rarities()) {
            AutoSalvageBag.Items.put(x.Rank(), new AutoSalvageBag(x.Rank()));
            Hearthstone.Items.put(x.Rank(), new Hearthstone(x.Rank()));
            ItemCapacitor.Items.put(x.Rank(), new ItemCapacitor(x.Rank()));

            for (ItemRarity rarity : Rarities.Items.rarities()) {
                for (ItemLootbox.LootTypes type : ItemLootbox.LootTypes.values()) {
                    for (ItemLootbox.LootBoxSizes size : ItemLootbox.LootBoxSizes.values()) {
                        String reg = ItemLootbox.GetStringForType(rarity.Rank(), type, size);
                        ItemLootbox.Items.put(reg, (ItemLootbox) new ItemLootbox(size, type, rarity
                                .Rank()).setRegistryName(reg));
                    }
                }
            }

        }

    }

    private static void registerLists(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        AutoSalvageBag.Items.values().forEach((x) -> r.register(x));
        Hearthstone.Items.values().forEach((x) -> r.register(x));
        ItemCapacitor.Items.values().forEach((x) -> r.register(x));
        ItemLootbox.Items.values().forEach((x) -> r.register(x));

        for (Item item : UniqueItems.ITEMS.values()) {
            IUnique uniq = (IUnique) item;
            item.setRegistryName("uniques/" + uniq.slot()
                    .toLowerCase() + "/" + uniq.GUID());
            event.getRegistry().register(item);
        }

    }

    private static void registerSingles(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        r.register(new ItemCurrencyBag().setRegistryName(ItemCurrencyBag.ID));
        r.register(new ItemLootBag().setRegistryName(ItemLootBag.ID));
        r.register(new ItemMapBag().setRegistryName(ItemMapBag.ID));

        Item.Properties tomeProp = new Item.Properties().defaultMaxDamage(0);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            tomeProp.setTEISR(TomeRenderer::new);
        });
        r.register(new TomeItem(tomeProp, 0).setRegistryName("mmorpg:tome"));

        r.register(new ItemAddSet());
        r.register(new ItemRerollSet());
        r.register(new CreateNewUnique());
        r.register(new ItemAddPrefix());
        r.register(new ItemAddSecondaryStat());
        r.register(new ItemAddSuffix());
        r.register(new ItemChaosOrb());
        r.register(new ItemLevelUpGear());
        r.register(new ItemNumberReroll());
        r.register(new ItemOrbOfTransmutation());
        r.register(new ItemRandomizePrefix());
        r.register(new ItemRandomizeSuffix());
        r.register(new ItemStoneOfHope());
        r.register(new RerollPrefixNumbers());
        r.register(new RerollSuffixNumbers());
        r.register(new RerollUniqueNumbers());

        r.register(new NormalUpgradeInfusion());
        r.register(new SuperiorUpgradeInfusion());
        r.register(new WondrousUpgradeInfusion());
        r.register(new AttackInfusionItem());
        r.register(new DefenseInfusionItem());
        r.register(new ResourceInfusionItem());

        r.register(new ItemAwakenRuneWord());
        r.register(new ItemIncreaseRarityNearestEntity());
        r.register(new ItemLevelNearestEntity());
        r.register(new ItemMapBackPortal());
        r.register(new ItemNewbieGearBag());
        r.register(new ItemPlayerLevelUp());

        r.register(new ItemAcidBolt());
        r.register(new ItemFireBolt());
        r.register(new ItemFrostBolt());
        r.register(new ItemThunderBolt());

        r.register(new ItemAcidBomb());
        r.register(new ItemFireBomb());
        r.register(new ItemIceBomb());
        r.register(new ItemThunderBomb());

        r.register(new ItemFireNova());
        r.register(new ItemFrostNova());
        r.register(new ItemThunderNova());
        r.register(new ItemPoisonNova());

        r.register(new ItemAcidExplosion());
        r.register(new ItemFrostExplosion());
        r.register(new ItemLightningExplosion());
        r.register(new ItemFlameExplosion());

        r.register(new ItemInstantHeal());
        r.register(new ItemSelfRegen());

    }

}



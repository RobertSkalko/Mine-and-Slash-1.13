package com.robertx22.onevent.player;

import com.robertx22.database.gearitemslots.*;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.blueprints.MapBlueprint;
import com.robertx22.loot.blueprints.SpellBlueprint;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.loot.gens.MapLootGen;
import com.robertx22.loot.gens.SpellLootGen;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.spells.self.SpellInstantHeal;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@EventBusSubscriber
public class OnLogin {

    @SubscribeEvent
    public static void onLogin(PlayerLoggedInEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }

        try {

            PlayerEntity player = event.getPlayer();

            if (Load.hasUnit(player)) {

                UnitData data = Load.Unit(player);

                data.onLogin(player);

                data.syncToClient(player);

                Load.playerMapData(player).teleportPlayerBack(player);

            } else {
                player.sendMessage(new StringTextComponent("Error, player has no capability!" + Ref.MOD_NAME + " mod is broken!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GiveStarterItems(PlayerEntity player) {

        GearBlueprint print = new GearBlueprint(1);
        print.SetSpecificType(new Sword().GUID());
        print.LevelRange = false;
        print.SetSpecificRarity(0);

        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Boots().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Chest().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Helmet().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Pants().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));

        print.SetSpecificType(new Ring().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Ring().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Necklace().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Bracelet().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));

        SpellBlueprint spell = new SpellBlueprint(1);
        spell.SetSpecificType(new SpellInstantHeal().GUID());
        spell.LevelRange = false;
        spell.SetSpecificRarity(0);

        player.inventory.addItemStackToInventory(new ItemStack(ItemOre.ItemOres.get(0)));

        player.inventory.addItemStackToInventory(SpellLootGen.Create(spell));

        // TESTING MAPS
        MapBlueprint map = new MapBlueprint(1, 1);
        player.inventory.addItemStackToInventory(MapLootGen.Create(map));
        player.inventory.addItemStackToInventory(MapLootGen.Create(map));
        player.inventory.addItemStackToInventory(MapLootGen.Create(map));
        player.inventory.addItemStackToInventory(MapLootGen.Create(map));
        player.inventory.addItemStackToInventory(MapLootGen.Create(map));
        player.inventory.addItemStackToInventory(MapLootGen.Create(map));

        ItemStack seeds = new ItemStack(Items.WHEAT_SEEDS);
        seeds.setCount(64);
        player.inventory.addItemStackToInventory(seeds);

        ItemStack mapdevice = new ItemStack(BlockRegister.ITEMBLOCK_MAP_DEVICE);
        mapdevice.setCount(64);
        player.inventory.addItemStackToInventory(mapdevice);

    }

}

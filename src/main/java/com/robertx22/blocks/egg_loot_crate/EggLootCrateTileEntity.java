package com.robertx22.blocks.egg_loot_crate;

import com.robertx22.blocks.conditions.IConditionalLootCrate;
import com.robertx22.blocks.conditions.LootCrateCondition;
import com.robertx22.blocks.conditions.NoMobAroundCondition;
import com.robertx22.database.rarities.items.MythicalItem;
import com.robertx22.loot.MasterLootGen;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.saveclasses.PlayerOncePerMapData;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.PlayerOncePerMap;
import com.robertx22.uncommon.utilityclasses.ElementalParticleUtils;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class EggLootCrateTileEntity extends TileEntity implements ITickable, IConditionalLootCrate {

    public static final String dataLoc = "PlayerOncePerMapData";
    public static final String isdroppingloc = "isdroppingloc";
    public static final String droplootticksloc = "droplootticksloc";
    public static final String timestodroploc = "timesToDrop";

    PlayerOncePerMapData data = new PlayerOncePerMapData();
    EntityPlayer player;

    int timesToDrop = getTimesToDrop();
    public boolean isDroppingLoot = false;
    int dropLootTicks = 0;

    public EggLootCrateTileEntity() {
        super(BlockRegister.EGG_LOOT_CRATE);
    }

    public void firework() {

        // turns invisible for some reason?
        EntityFireworkRocket firework = new EntityFireworkRocket(world);
        firework.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
        firework.setInvulnerable(true);
        world.spawnEntity(firework);

    }

    public void dropLoot(EntityPlayer player) {

        timesToDrop--;

        if (player != null) {

            List<ItemStack> loot = MasterLootGen.gen(player, Load.playerMapData(player)
                    .getLevel(), 5F);

            for (ItemStack stack : loot) {
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 2, pos.getZ(), stack));
            }
            if (loot.size() > 0) {
                firework();
            }
        }

    }

    @Override
    public NBTTagCompound write(NBTTagCompound nbt) {
        super.write(nbt);

        NBTTagCompound datanbt = new NBTTagCompound();
        PlayerOncePerMap.Save(datanbt, data);
        nbt.put(dataLoc, datanbt);
        nbt.putInt(timestodroploc, this.timesToDrop);
        nbt.putBoolean(isdroppingloc, isDroppingLoot);
        nbt.putInt(droplootticksloc, dropLootTicks);

        return nbt;
    }

    @Override
    public void read(NBTTagCompound nbt) {
        super.read(nbt);

        this.data = PlayerOncePerMap.Load(nbt.getCompound(dataLoc));
        this.timesToDrop = nbt.getInt(timestodroploc);
        this.isDroppingLoot = nbt.getBoolean(isdroppingloc);
        this.dropLootTicks = nbt.getInt(droplootticksloc);

    }

    public void activateDrops(EntityPlayer player) {
        data.add(player);
        this.player = player;
        this.isDroppingLoot = true;

    }

    public int getTimesToDrop() {
        return 5;
    }

    public void reset() {

        isDroppingLoot = false;
        this.timesToDrop = getTimesToDrop();

    }

    public boolean finished() {
        return timesToDrop < 1;
    }

    private Rarity getRarity() {
        return new MythicalItem();
    }

    @Override
    public void tick() {

        if (world.isRemote) {
            //return;
        }

        if (isDroppingLoot) {

            if (finished()) {
                reset();
            }

            ElementalParticleUtils.SpawnAoeParticle(getRarity(), world, pos.getX() + 0.5, pos
                    .getY() + 0.5, pos.getZ() + 0.5, 1.3F, 10); // 0.5 is cus blockpos truncates the values so it ends at the edges instead of center

            dropLootTicks++;

            if (timesToDrop > 0) {
                if (dropLootTicks > 20) {
                    dropLootTicks = 0;
                    this.dropLoot(player);
                }
            }
        }

    }

    public void tryActivate(EntityPlayer player) {

        if (condition().canOpenCrate(player)) {

            if (data.canDo(player)) {

                if (isDroppingLoot) {
                    player.sendMessage(new TextComponentString("This crate is currently being used."));
                } else {
                    activateDrops(player);
                }

            } else {
                player.sendMessage(new TextComponentString("You have already used this block. Come again next map!"));
            }

        } else {
            player.sendMessage(condition().tellCondition());
        }

    }

    @Override
    public LootCrateCondition condition() {
        return new NoMobAroundCondition(7);
    }
}

package com.robertx22.blocks.egg_loot_crate;

import com.robertx22.loot.MasterLootGen;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.saveclasses.PlayerOncePerMapData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.PlayerOncePerMap;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.ElementalParticleUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.List;

public class EggLootCrateTileEntity extends TileEntity implements ITickable {

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

    public void dropLoot(EntityPlayer player) {

        timesToDrop--;
        if (player != null) {
            List<ItemStack> loot = MasterLootGen.gen(player, Load.playerMapData(player)
                    .getLevel(), 5F);

            for (ItemStack stack : loot) {
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 2, pos.getZ(), stack));
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

    @Override
    public void tick() {

        if (isDroppingLoot) {

            if (finished()) {
                reset();
            }

            //BlockPos center = getCenter();

            ElementalParticleUtils.SpawnAoeParticle(Elements.Thunder, world, pos.getX() + 0.5, pos
                    .getY() + 0.5, pos.getZ() + 0.5, 1.3F, 10);
            
            dropLootTicks++;

            if (timesToDrop > 0) {
                if (dropLootTicks > 20) {
                    dropLootTicks = 0;
                    this.dropLoot(player);
                }
            }
        }

    }
}

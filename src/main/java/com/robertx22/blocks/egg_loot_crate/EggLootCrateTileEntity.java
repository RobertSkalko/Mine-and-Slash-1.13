package com.robertx22.blocks.egg_loot_crate;

import com.robertx22.loot.MasterLootGen;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.saveclasses.PlayerOncePerMapData;
import com.robertx22.uncommon.datasaving.PlayerOncePerMap;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.ElementalParticleUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class EggLootCrateTileEntity extends TileEntity implements ITickable {

    public static final String dataLoc = "PlayerOncePerMapData";
    public static final String isdroppingloc = "isdroppingloc";
    public static final String droplootticksloc = "droplootticksloc";
    public static final String itemsamountloc = "itemsamountloc";

    PlayerOncePerMapData data = new PlayerOncePerMapData();

    int itemsToDrop = getItemsToDrop();
    public boolean isDroppingLoot = false;
    int dropLootTicks = 0;

    public EggLootCrateTileEntity() {
        super(BlockRegister.EGG_LOOT_CRATE);
    }

    public void dropLoot() {

        itemsToDrop--;

        List<ItemStack> loot = MasterLootGen.genAmount(pos, 1, world);

        for (ItemStack stack : loot) {
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 2, pos.getZ(), stack));
        }

    }

    @Override
    public NBTTagCompound write(NBTTagCompound nbt) {
        super.write(nbt);

        NBTTagCompound datanbt = new NBTTagCompound();
        PlayerOncePerMap.Save(datanbt, data);
        nbt.put(dataLoc, datanbt);
        nbt.putInt(itemsamountloc, this.itemsToDrop);
        nbt.putBoolean(isdroppingloc, isDroppingLoot);
        nbt.putInt(droplootticksloc, dropLootTicks);

        return nbt;
    }

    @Override
    public void read(NBTTagCompound nbt) {
        super.read(nbt);

        this.data = PlayerOncePerMap.Load(nbt.getCompound(dataLoc));
        this.itemsToDrop = nbt.getInt(itemsamountloc);
        this.isDroppingLoot = nbt.getBoolean(isdroppingloc);
        this.dropLootTicks = nbt.getInt(droplootticksloc);

    }

    public void activateDrops() {
        this.isDroppingLoot = true;
    }

    public int getItemsToDrop() {
        return 5;
    }

    public void reset() {

        isDroppingLoot = false;
        this.itemsToDrop = getItemsToDrop();

    }

    public boolean finished() {
        return itemsToDrop < 1;
    }

    public BlockPos getCenter() {

        return new BlockPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }

    @Override
    public void tick() {

        if (isDroppingLoot) {

            if (finished()) {
                reset();
            }

            BlockPos center = getCenter();

            ElementalParticleUtils.SpawnAoeParticle(Elements.Thunder, world, center, 1.3F, 10);

            dropLootTicks++;

            if (itemsToDrop > 0) {
                if (dropLootTicks > 20) {
                    dropLootTicks = 0;
                    this.dropLoot();
                }
            }
        }

    }
}

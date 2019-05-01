package com.robertx22.blocks.map_device;

import com.robertx22.blocks.bases.BaseTile;
import com.robertx22.items.misc.ItemMap;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nullable;

public class TileMap extends BaseTile {
    @Override
    public boolean isAutomatable() {
        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return false;
    }

    public ItemStack StartSlot() {
        return itemStacks[3];
    }

    public ItemStack TierSlot() {
        return itemStacks[0];
    }

    public ItemStack LevelSlot() {
        return itemStacks[1];
    }

    public ItemStack MapSlot() {
        return itemStacks[2];
    }

    public TileMap() {
        super(StartupMap.MAP_DEVICE);

        itemStacks = new ItemStack[4];
        clear();
    }

    @Override
    public void tick() {
        if (!this.world.isRemote) {

            ticks++;
            if (ticks > 20) {
                ticks = 0;

                doLogic();

            }
        }
    }

    private void doLogic() {

        ItemStack start = this.StartSlot();

        MapItemData map = Map.Load(this.MapSlot());
        MapItemData level = Map.Load(this.LevelSlot());
        MapItemData tier = Map.Load(this.TierSlot());

        if (map != null) {

            if (start != null && start.getItem().equals(Items.WHEAT_SEEDS)) {

                BlockPos p = this.pos;

                EntityPlayer player = this.getWorld()
                        .getClosestPlayer(p.getX(), p.getY(), p.getZ(), 20, false);

                if (player != null) {

                    world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.6f, 0);

                    world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.BLOCKS, 0.4f, 0);

                    DimensionType type = map.createDimension(world, p, player);

                    // start map
                    this.MapSlot().shrink(1);
                    this.StartSlot().shrink(1);

                    BlockPos pos = this.pos.north(4);
                    ItemMap.createMapPortal(type, pos, world, map);

                    BlockPos pos1 = this.pos.south(4);
                    ItemMap.createMapPortal(type, pos1, world, map);

                    BlockPos pos2 = this.pos.east(4);
                    ItemMap.createMapPortal(type, pos2, world, map);

                    BlockPos pos3 = this.pos.west(4);
                    ItemMap.createMapPortal(type, pos3, world, map);

                } else if (level != null) {

                    if (map.increaseLevel(level.rarity + 1)) {
                        this.LevelSlot().shrink(1);
                        Map.Save(this.MapSlot(), map);
                    }

                } else if (tier != null) {

                    if (map.increaseTier(tier.rarity + 1)) {
                        this.TierSlot().shrink(1);
                        Map.Save(this.MapSlot(), map);
                    }
                }
            }

            markDirty();
        }

    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Override
    public ITextComponent getName() {
        return CLOC.blank("block.mmorpg.map_device");
    }

    @Override
    public ITextComponent getCustomName() {
        return this.getName();
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory,
                                     EntityPlayer playerIn) {
        return new ContainerMap(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return StartupMap.ID;
    }

}
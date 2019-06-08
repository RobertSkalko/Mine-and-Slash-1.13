package com.robertx22.blocks.map_device;

import com.robertx22.blocks.bases.BaseTile;
import com.robertx22.items.misc.ItemMap;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Map;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
        super(BlockRegister.MAP_DEVICE);

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

                PlayerEntity player = this.getWorld()
                        .getClosestPlayer(p.getX(), p.getY(), p.getZ(), 20, EntityPredicates.IS_ALIVE);

                if (player != null) {

                    world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.6f, 0);
                    world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.BLOCKS, 0.4f, 0);

                    DimensionType type = null;
                    try {
                        type = map.initDimension(world, p, player);

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

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.map_device");

    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {
        return new ContainerMap(i, playerInventory, this);
    }
}
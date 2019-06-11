package com.robertx22.mmorpg.registers.client;

import com.robertx22.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class TestEntity extends ProjectileItemEntity implements IRendersAsItem {

    public TestEntity(EntityType<? extends ProjectileItemEntity> p_i50155_1_,
                      World p_i50155_2_) {
        super(p_i50155_1_, p_i50155_2_);
    }

    public TestEntity(FMLPlayMessages.SpawnEntity p_i50155_1_, World p_i50155_2_) {
        super(EntityRegister.TESTENTITY, p_i50155_2_);
    }

    public TestEntity(World world) {
        super(EntityRegister.TESTENTITY, world);
    }

    @Override
    protected Item func_213885_i() {
        return Items.EMERALD;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }
}

package com.robertx22.mmorpg.registers;

import java.util.function.Function;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.projectile.SpellAcidBolt;
import com.robertx22.spells.projectile.SpellFireBolt;
import com.robertx22.spells.projectile.SpellFrostBolt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegisterEntities {

    public static final EntityType<SpellFireBolt.EntityFireBolt> FIREBOLT;
    public static final EntityType<SpellFrostBolt.EntityFrostBolt> FROSTBOLT;
    public static final EntityType<SpellAcidBolt.EntityAcidBolt> ACIDBOLT;
    public static final EntityType<SpellFireBolt.EntityFireBolt> THUNDERBOLT;

    static {

	FIREBOLT = createEntityType(SpellFireBolt.EntityFireBolt.class, SpellFireBolt.EntityFireBolt::new);

    }

    @SubscribeEvent
    public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event) {

    }

    private static <T extends Entity> EntityType<T> createEntityType(Class<? extends T> entityClass,
	    Function<? super World, ? extends T> factory) {
	EntityType<T> type = EntityType.Builder.create(entityClass, factory).tracker(64, 1, true)
		.build(Ref.MODID + ":" + entityClass.getName());
	type.setRegistryName(new ResourceLocation(Ref.MODID, entityClass.getName()));

	return type;
    }

}

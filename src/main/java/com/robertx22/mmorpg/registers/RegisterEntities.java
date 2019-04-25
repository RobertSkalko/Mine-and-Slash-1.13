package com.robertx22.mmorpg.registers;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.spells.projectile.SpellAcidBolt;
import com.robertx22.spells.projectile.SpellFireBolt;
import com.robertx22.spells.projectile.SpellFrostBolt;
import com.robertx22.spells.projectile.SpellThunderBolt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegisterEntities {

    public static List<EntityType<?>> ENTITY_TYPES = new LinkedList();

    public static final EntityType<?> FIREBOLT;
    public static final EntityType<?> FROSTBOLT;
    public static final EntityType<?> ACIDBOLT;
    public static final EntityType<?> THUNDERBOLT;

    public static final EntityType<?> FIREBOMB;
    public static final EntityType<?> FROSTBOMB;
    public static final EntityType<?> ACIDBOMB;
    public static final EntityType<?> THUNDERBOMB;

    public static final EntityType<?> FIREEXPLOSION;
    public static final EntityType<?> FROSTEXPLOSION;
    public static final EntityType<?> ACIDEXPLOSION;
    public static final EntityType<?> THUNDEREXPLOSION;

    static {

	FIREBOLT = newType(SpellFireBolt.EntityFireBolt.class, SpellFireBolt.EntityFireBolt::new);
	FROSTBOLT = newType(SpellFrostBolt.EntityFrostBolt.class, SpellFrostBolt.EntityFrostBolt::new);
	ACIDBOLT = newType(SpellAcidBolt.EntityAcidBolt.class, SpellAcidBolt.EntityAcidBolt::new);
	THUNDERBOLT = newType(SpellThunderBolt.EntityThunderBolt.class, SpellThunderBolt.EntityThunderBolt::new);

	FIREEXPLOSION = newType(SpellFlameExplosion.EntityFlameExplosion.class,
		SpellFlameExplosion.EntityFlameExplosion::new);
	FROSTEXPLOSION = newType(SpellFrostExplosion.EntityFrostExplosion.class,
		SpellFrostExplosion.EntityFrostExplosion::new);
	ACIDEXPLOSION = newType(SpellAcidExplosion.EntityAcidExplosion.class,
		SpellAcidExplosion.EntityAcidExplosion::new);
	THUNDEREXPLOSION = newType(SpellLightningExplosion.EntityLightningExplosion.class,
		SpellLightningExplosion.EntityLightningExplosion::new);

	FIREBOMB = newType(SpellFireBomb.EntityFireBomb.class, SpellFireBomb.EntityFireBomb::new);
	FROSTBOMB = newType(SpellIceBomb.EntityIceBomb.class, SpellIceBomb.EntityIceBomb::new);
	ACIDBOMB = newType(SpellAcidBomb.EntityAcidBomb.class, SpellAcidBomb.EntityAcidBomb::new);
	THUNDERBOMB = newType(SpellThunderBomb.EntityThunderBomb.class, SpellThunderBomb.EntityThunderBomb::new);

    }

    @SubscribeEvent
    public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event) {

	ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));

    }

    private static <T extends Entity> EntityType<T> newType(Class<? extends T> entityClass,
	    Function<? super World, ? extends T> factory) {

	EntityType<T> type = EntityType.Builder.create(entityClass, factory).tracker(64, 1, true)
		.build(Ref.MODID + ":" + entityClass.getName());
	type.setRegistryName(new ResourceLocation(Ref.MODID, entityClass.getName()));

	ENTITY_TYPES.add(type);

	return type;
    }

}

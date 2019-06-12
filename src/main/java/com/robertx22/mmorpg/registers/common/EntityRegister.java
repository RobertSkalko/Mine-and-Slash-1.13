package com.robertx22.mmorpg.registers.common;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.spells.entities.bases.EntityStaffProjectile;
import com.robertx22.spells.projectile.SpellAcidBolt;
import com.robertx22.spells.projectile.SpellFireBolt;
import com.robertx22.spells.projectile.SpellFrostBolt;
import com.robertx22.spells.projectile.SpellThunderBolt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

    public static List<EntityType<? extends Entity>> ENTITY_TYPES = new LinkedList();

    @SubscribeEvent
    public static void registerEntityTypes(
            final RegistryEvent.Register<EntityType<?>> event) {

        ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));

    }

    public static final EntityType<? extends Entity> FIREBOLT;
    public static final EntityType<? extends Entity> FROSTBOLT;
    public static final EntityType<? extends Entity> ACIDBOLT;
    public static final EntityType<? extends Entity> THUNDERBOLT;

    public static final EntityType<? extends Entity> FIREBOMB;
    public static final EntityType<? extends Entity> FROSTBOMB;
    public static final EntityType<? extends Entity> ACIDBOMB;
    public static final EntityType<? extends Entity> THUNDERBOMB;

    public static final EntityType<? extends Entity> FIREEXPLOSION;
    public static final EntityType<? extends Entity> FROSTEXPLOSION;
    public static final EntityType<? extends Entity> ACIDEXPLOSION;
    public static final EntityType<? extends Entity> THUNDEREXPLOSION;

    public static final EntityType<? extends Entity> STAFFPROJECTILE;

    static {

        FIREBOLT = newType(SpellFireBolt.EntityFireBolt.class, SpellFireBolt.EntityFireBolt::new, SpellFireBolt.EntityFireBolt::new, "entity_fire_bolt");
        FROSTBOLT = newType(SpellFrostBolt.EntityFrostBolt.class, SpellFrostBolt.EntityFrostBolt::new, SpellFrostBolt.EntityFrostBolt::new, "entity_frost_bolt");
        ACIDBOLT = newType(SpellAcidBolt.EntityAcidBolt.class, SpellAcidBolt.EntityAcidBolt::new, SpellAcidBolt.EntityAcidBolt::new, "entity_acid_bolt");
        THUNDERBOLT = newType(SpellThunderBolt.EntityThunderBolt.class, SpellThunderBolt.EntityThunderBolt::new, SpellThunderBolt.EntityThunderBolt::new, "entity_thunder_bolt");

        FIREEXPLOSION = newType(SpellFlameExplosion.EntityFlameExplosion.class, SpellFlameExplosion.EntityFlameExplosion::new, SpellFlameExplosion.EntityFlameExplosion::new, "entity_flame_explosion");
        FROSTEXPLOSION = newType(SpellFrostExplosion.EntityFrostExplosion.class, SpellFrostExplosion.EntityFrostExplosion::new, SpellFrostExplosion.EntityFrostExplosion::new, "entity_frost_explosion");
        ACIDEXPLOSION = newType(SpellAcidExplosion.EntityAcidExplosion.class, SpellAcidExplosion.EntityAcidExplosion::new, SpellAcidExplosion.EntityAcidExplosion::new, "entity_acid_explosion");
        THUNDEREXPLOSION = newType(SpellLightningExplosion.EntityLightningExplosion.class, SpellLightningExplosion.EntityLightningExplosion::new, SpellLightningExplosion.EntityLightningExplosion::new, "entity_lightning_explosion");

        FIREBOMB = newType(SpellFireBomb.EntityFireBomb.class, SpellFireBomb.EntityFireBomb::new, SpellFireBomb.EntityFireBomb::new, "entity_fire_bomb");
        FROSTBOMB = newType(SpellIceBomb.EntityIceBomb.class, SpellIceBomb.EntityIceBomb::new, SpellIceBomb.EntityIceBomb::new, "entity_ice_bomb");
        ACIDBOMB = newType(SpellAcidBomb.EntityAcidBomb.class, SpellAcidBomb.EntityAcidBomb::new, SpellAcidBomb.EntityAcidBomb::new, "entity_acid_bomb");
        THUNDERBOMB = newType(SpellThunderBomb.EntityThunderBomb.class, SpellThunderBomb.EntityThunderBomb::new, SpellThunderBomb.EntityThunderBomb::new, "entity_thunder_bomb");

        STAFFPROJECTILE = newType(EntityStaffProjectile.class, EntityStaffProjectile::new, EntityStaffProjectile::new, "staff_projectile");

    }

    private static <T extends Entity> EntityType<T> newType(
            Class<? extends T> entityClass, EntityType.IFactory<T> factory,
            BiFunction<FMLPlayMessages.SpawnEntity, World, T> bif, String id) {

        EntityType<T> type = EntityType.Builder.<T>create(factory, EntityClassification.MISC)
                .setCustomClientFactory(bif)
                .size(0.25F, 0.25F)
                .build(Ref.MODID + ":" + id.toLowerCase());

        type.setRegistryName(new ResourceLocation(Ref.MODID, id.toLowerCase()));

        ENTITY_TYPES.add(type);

        return type;
    }

}



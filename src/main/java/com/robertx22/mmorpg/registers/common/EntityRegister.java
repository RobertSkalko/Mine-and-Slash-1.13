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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

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

    public static final EntityType<?> STAFFPROJECTILE;

    static {

        FIREBOLT = newType(SpellFireBolt.EntityFireBolt.class, SpellFireBolt.EntityFireBolt::new, "entity_fire_bolt");
        FROSTBOLT = newType(SpellFrostBolt.EntityFrostBolt.class, SpellFrostBolt.EntityFrostBolt::new, "entity_frost_bolt");
        ACIDBOLT = newType(SpellAcidBolt.EntityAcidBolt.class, SpellAcidBolt.EntityAcidBolt::new, "entity_acid_bolt");
        THUNDERBOLT = newType(SpellThunderBolt.EntityThunderBolt.class, SpellThunderBolt.EntityThunderBolt::new, "entity_thunder_bolt");

        FIREEXPLOSION = newType(SpellFlameExplosion.EntityFlameExplosion.class, SpellFlameExplosion.EntityFlameExplosion::new, "entity_flame_explosion");
        FROSTEXPLOSION = newType(SpellFrostExplosion.EntityFrostExplosion.class, SpellFrostExplosion.EntityFrostExplosion::new, "entity_frost_explosion");
        ACIDEXPLOSION = newType(SpellAcidExplosion.EntityAcidExplosion.class, SpellAcidExplosion.EntityAcidExplosion::new, "entity_acid_explosion");
        THUNDEREXPLOSION = newType(SpellLightningExplosion.EntityLightningExplosion.class, SpellLightningExplosion.EntityLightningExplosion::new, "entity_lightning_explosion");

        FIREBOMB = newType(SpellFireBomb.EntityFireBomb.class, SpellFireBomb.EntityFireBomb::new, "entity_fire_bomb");
        FROSTBOMB = newType(SpellIceBomb.EntityIceBomb.class, SpellIceBomb.EntityIceBomb::new, "entity_ice_bomd");
        ACIDBOMB = newType(SpellAcidBomb.EntityAcidBomb.class, SpellAcidBomb.EntityAcidBomb::new, "entity_acid_bomb");
        THUNDERBOMB = newType(SpellThunderBomb.EntityThunderBomb.class, SpellThunderBomb.EntityThunderBomb::new, "entity_thunder_bomb");

        STAFFPROJECTILE = newType(EntityStaffProjectile.class, EntityStaffProjectile::new, "staff_projectile");

    }

    @SubscribeEvent
    public static void registerEntityTypes(
            final RegistryEvent.Register<EntityType<?>> event) {

        ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));

    }

    private static <T extends Entity> EntityType<T> newType(
            Class<? extends T> entityClass, EntityType.IFactory<T> factory, String id) {

        EntityType<T> type = EntityType.Builder.<T>create(factory, EntityClassification.MISC)
                .setTrackingRange(64)
                .setShouldReceiveVelocityUpdates(true)
                .setUpdateInterval(1)
                .build(Ref.MODID + ":" + id.toLowerCase());

        type.setRegistryName(new ResourceLocation(Ref.MODID, id.toLowerCase()));

        ENTITY_TYPES.add(type);

        return type;
    }

}

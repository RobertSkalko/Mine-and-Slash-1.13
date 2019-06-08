package com.robertx22.spells.entities.bases;

import com.robertx22.items.gearitems.weapons.ItemStaff;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityStaffProjectile extends EntityBaseProjectile {

    ItemStack staff;

    public EntityStaffProjectile(EntityType<? extends EntityStaffProjectile> type,
                                 World world) {
        super(type, world);
    }

    public EntityStaffProjectile(World worldIn) {
        super(EntityRegister.STAFFPROJECTILE, worldIn);

    }

    public void SetReady(ItemStack staff) {
        this.staff = staff;
    }

    @Override
    public double radius() {
        return 0.5D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (result.entity != null && result.entity instanceof LivingEntity && staff != null) {

            if (!world.isRemote) {
                try {
                    LivingEntity target = (LivingEntity) result.entity;

                    ItemStaff staffclass = (ItemStaff) staff.getItem();

                    UnitData sourcedata = Load.Unit(this.thrower);
                    UnitData targetdata = Load.Unit(target);

                    staffclass.mechanic()
                            .Attack(this.getThrower(), target, sourcedata, targetdata);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }

    }

    @Override
    public void tick() {

        super.tick();

        if (world.isRemote) {
            for (int i = 0; i < 5; i++) {

                this.world.addParticle(ParticleTypes.CRIT, true, this.posX + rand.nextFloat() * 0.2 - 0.1, this.posY + this
                        .getHeight() / 2 + rand.nextFloat() * 0.2 - 0.1, this.posZ + rand.nextFloat() * 0.2 - 0.1, 0, 0, 0);

            }
        }

        if (this.ticksExisted > 20) {
            this.remove();
        }
    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              LivingEntity caster) {

        this.ignoreEntity = caster;
        Vec3d look = caster.getLookVec();
        this.thrower = caster;

        SetReady(caster.getHeldItemMainhand());
        setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
        shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

        WorldUtils.spawnEntity(world, this);
    }

}
package com.robertx22.spells.entities;

import com.mojang.datafixers.DataFixer;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.interfaces.IBuffableSpell;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Particles;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class EntityBaseProjectile extends Entity implements IProjectile, IBuffableSpell, IShootableProjectile {

    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    protected boolean inGround;
    public int throwableShake;
    /**
     * The entity that threw this throwable item.
     */
    protected EntityLivingBase thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;
    private int deathTime = 80;
    private int airProcTime;
    private boolean doGroundProc;

    public Entity ignoreEntity;
    private int ignoreTime;

    public SpellBuffType buff = SpellBuffType.None;
    public SpellType spellType = SpellType.Self_Heal;

    public abstract double radius();

    protected boolean shouldExcludeCaster() {
        return true;
    }

    @Override
    protected void registerData() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void readAdditional(NBTTagCompound compound) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void writeAdditional(NBTTagCompound compound) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBuffType(SpellType type) {
        this.spellType = type;
    }

    @Override
    public SpellType getBuffType() {
        return this.spellType;
    }

    @Override
    public void setBuff(SpellBuffType buff) {
        this.buff = buff;

    }

    protected boolean onExpireProc(EntityLivingBase caster) {

        return false;
    }

    @Override
    public SpellBuffType getBuff() {
        return buff;
    }

    public boolean getDoExpireProc() {
        return this.doGroundProc;
    }

    public int getTicksInAir() {
        return this.ticksInAir;
    }

    public int getTicksInGround() {
        return this.ticksInGround;
    }

    public void setTicksInAir(int newVal) {
        this.ticksInAir = newVal;
    }

    public void setTicksInGround(int newVal) {
        this.ticksInGround = newVal;
    }

    public int getAirProcTime() {
        return this.airProcTime;
    }

    public void setAirProcTime(int newVal) {
        this.airProcTime = newVal;
    }

    public int getDeathTime() {
        return this.deathTime;
    }

    public void setDeathTime(int newVal) {
        this.deathTime = newVal;
    }

    public void setDoExpireProc(boolean newVal) {
        this.doGroundProc = newVal;
    }

    public EntityBaseProjectile(EntityType<?> type, World worldIn) {
        super(type, worldIn);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.setSize(0.25F, 0.25F);
    }

    public EntityBaseProjectile(EntityType<?> type, World worldIn, double x, double y,
                                double z) {
        this(type, worldIn);
        this.setPosition(x, y, z);
    }

    public EntityBaseProjectile(EntityType<?> type, World worldIn,
                                EntityLivingBase throwerIn) {
        this(type, worldIn, throwerIn.posX, throwerIn.posY + (double) throwerIn.getEyeHeight() - 0.10000000149011612D, throwerIn.posZ);
        this.thrower = throwerIn;
    }

    protected void entityInit() {
    }

    /**
     * Checks if the entity is in range to render.
     */

    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    /**
     * Sets throwable heading based on an entity that's throwing it
     */
    public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn,
                      float pitchOffset, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        this.shoot((double) f, (double) f1, (double) f2, velocity, inaccuracy);
        this.motionX += entityThrower.motionX;
        this.motionZ += entityThrower.motionZ;

        if (entityThrower instanceof EntityLivingBase) {
            this.thrower = (EntityLivingBase) entityThrower;
        }
        if (!entityThrower.onGround) {
            this.motionY += entityThrower.motionY;
        }
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
     * direction.
     */
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double) f;
        y = y / (double) f;
        z = z / (double) f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        x = x * (double) velocity;
        y = y * (double) velocity;
        z = z * (double) velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.ticksInGround = 0;
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);
            this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
    }

    /**
     * Sets this projectile's velocity as a normalised vector towards the target.
     */
    public void directTowards(Entity target, float velocity) {

        double dx = target.posX - this.posX;
        double dy = target.getBoundingBox().minY + (double) (target.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
        double dz = target.posZ - this.posZ;

        this.motionX = dx / this.getDistance(target) * velocity;
        this.motionY = dy / this.getDistance(target) * velocity;
        this.motionZ = dz / this.getDistance(target) * velocity;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.tick();

        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        if (this.getDoExpireProc() && this.ticksExisted > 0 && this.ticksExisted % this.deathTime == 0) {
            if (this.onExpireProc(this.getThrower())) {
                this.remove();
            }
        }

        if (this.inGround) {
            if (this.world.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile))
                    .getBlock() == this.inTile) {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200) {
                    this.remove();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
            this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
            this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null) {
            vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox()
                .expand(this.motionX, this.motionY, this.motionZ)
                .grow(1.2D));

        boolean flag = false;

        for (int i = 0; i < list.size(); ++i) {
            Entity entity1 = list.get(i);

            if (Load.hasUnit(entity1)) {
                if (entity1 == this.ignoreEntity) {
                    flag = true;
                } else if (this.thrower != null && this.ignoreEntity == null) {
                    this.ignoreEntity = entity1;
                    flag = true;
                } else {
                    flag = false;

                    entity = entity1;

                }
            }
        }

        if (this.ignoreEntity != null) {
            if (flag) {
                this.ignoreTime = 2;
            } else if (this.ignoreTime-- <= 0) {
                this.ignoreEntity = null;
            }
        }

        if (entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null) {

            if (raytraceresult.hitInfo == RayTraceResult.Type.BLOCK) {
                Block block = this.world.getBlockState(raytraceresult.getBlockPos())
                        .getBlock();

                if (block.equals(Blocks.GRASS) || block.equals(Blocks.TALL_GRASS) || block
                        .equals(Blocks.TALL_SEAGRASS)) {
                    // ignore grass
                } else {
                    this.onImpact(raytraceresult);
                }
            } else {
                this.onImpact(raytraceresult);
            }

        }

        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

        for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f1 = 0.99F;
        float f2 = this.getGravityVelocity();

        if (this.isInWater()) {
            for (int j = 0; j < 4; ++j) {
                float f3 = 0.25F;
                this.world.addParticle(Particles.BUBBLE, flag, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
            }

            f1 = 0.8F;
        }

        this.motionX *= (double) f1;
        this.motionY *= (double) f1;
        this.motionZ *= (double) f1;

        if (!this.hasNoGravity()) {
            this.motionY -= (double) f2;
        }

        this.setPosition(this.posX, this.posY, this.posZ);

        checkHoming();

        if (this.ticksExisted > this.getDeathTime()) {
            this.remove();
        }

    }

    public void checkHoming() {

        if (this.getBuff().equals(SpellBuffType.Homing_Projectile)) {
            // homing

            if (!this.collided && !this.world.isRemote) {

                double seekingRange = 3.0d;

                if (setHomingTarget == false) {
                    List<EntityLivingBase> entities = WizardryUtilities.getEntitiesWithinRadius(seekingRange, this.posX, this.posY, this.posZ, this.world);

                    for (Entity possibleTarget : entities) {
                        // Decides if current entity should be replaced.
                        if (homindTarget == null || this.getDistance(homindTarget) > this.getDistance(possibleTarget)) {
                            // Decides if new entity is a valid target.
                            if (Load.hasUnit(possibleTarget) && !possibleTarget.equals(this
                                    .getThrower())) {
                                homindTarget = possibleTarget;
                                setHomingTarget = true;
                                this.setNoGravity(true);
                                this.motionX /= (double) 5;
                                this.motionY /= (double) 5;
                                this.motionZ /= (double) 5;

                            }
                        }
                    }
                }

                if (homindTarget != null && Math.abs(this.motionX) < 5 && Math.abs(this.motionY) < 5 && Math
                        .abs(this.motionZ) < 5) {

                    this.addVelocity((homindTarget.posX - this.posX) / 30, (homindTarget.posY + homindTarget.height / 2 - this.posY) / 30, (homindTarget.posZ - this.posZ) / 30);

                    // this.motionY += (target.posY + target.height - this.posY) / 30;

                }
            }
        }
    }

    Entity homindTarget = null;
    boolean setHomingTarget = false;

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity() {
        return 0.03F;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected abstract void onImpact(RayTraceResult result);

    public static void registerFixesThrowable(DataFixer fixer, String name) {
    }

    /**
     * (abstract) Protected helper method to write subclass entity dataInstance to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.putInt("xTile", this.xTile);
        compound.putInt("yTile", this.yTile);
        compound.putInt("zTile", this.zTile);

        compound.putByte("shake", (byte) this.throwableShake);
        compound.putByte("inGround", (byte) (this.inGround ? 1 : 0));

        if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer) {
            this.throwerName = this.thrower.getName().toString();
        }

        compound.putString("ownerName", this.throwerName == null ? "" : this.throwerName);
        compound.putBoolean("doGroundProc", this.getDoExpireProc());
        compound.putInt("airProcTime", this.getAirProcTime());
        compound.putInt("deathTime", this.getDeathTime());
    }

    /**
     * (abstract) Protected helper method to read subclass entity dataInstance from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.xTile = compound.getInt("xTile");
        this.yTile = compound.getInt("yTile");
        this.zTile = compound.getInt("zTile");

        this.throwableShake = compound.getByte("shake") & 255;
        this.inGround = compound.getByte("inGround") == 1;
        this.thrower = null;
        this.throwerName = compound.getString("ownerName");

        if (this.throwerName != null && this.throwerName.isEmpty()) {
            this.throwerName = null;
        }

        this.thrower = this.getThrower();

        this.setDoExpireProc(compound.getBoolean("doGroundProc"));
        this.setAirProcTime(compound.getInt("airProcTime"));
        this.setDeathTime(compound.getInt("deathTime"));
    }

    @Nullable
    public EntityLivingBase getThrower() {
        if (this.thrower == null && this.throwerName != null && !this.throwerName.isEmpty()) {
            this.thrower = this.world.getPlayerEntityByName(this.throwerName);

            if (this.thrower == null && this.world instanceof WorldServer) {
                try {
                    Entity entity = ((WorldServer) this.world).getEntityFromUuid(UUID.fromString(this.throwerName));

                    if (entity instanceof EntityLivingBase) {
                        this.thrower = (EntityLivingBase) entity;
                    }
                } catch (Throwable var2) {
                    this.thrower = null;
                }
            }
        }

        return this.thrower;
    }

}
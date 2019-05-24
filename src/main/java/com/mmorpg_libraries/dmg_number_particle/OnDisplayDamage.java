package com.mmorpg_libraries.dmg_number_particle;

import com.robertx22.network.DmgNumPacket;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class OnDisplayDamage {

    private static Minecraft mc = Minecraft.getInstance();
    private Entity pointedEntity;

    public static void displayParticle(DmgNumPacket data) {

        mc = Minecraft.getInstance();

        World world = mc.player.world;
        double motionX = world.rand.nextGaussian() * 0.02;
        double motionY = 0.5f;
        double motionZ = world.rand.nextGaussian() * 0.02;
        Particle damageIndicator = new DamageParticle(Elements.byNumber(data.element), data.string, world, data.x, data.y + data.height, data.z, motionX, motionY, motionZ);

        Minecraft.getInstance().particles.addEffect(damageIndicator);

        // TODO PROBABLY DOESNT WORK
        // Minecraft.getInstance().world.addParticle((IParticleData) damageIndicator, true, dataInstance.x, dataInstance.y, dataInstance.z, 1D, 1D, 1D);

    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public RayTraceResult rayTrace(Entity e, double blockReachDistance,
                                   float partialTicks) {
        Vec3d vec3d = e.getEyePosition(partialTicks);
        Vec3d vec3d1 = e.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return mc.world.rayTraceBlocks(vec3d, vec3d2);
    }

    public RayTraceResult getMouseOver(float partialTicks) {
        RayTraceResult objectMouseOver;
        Entity observer = this.mc.getRenderViewEntity();

        if (observer == null || this.mc.world == null) {
            return null;
        }

        this.mc.pointedEntity = null;
        double reachDistance = 50;

        objectMouseOver = rayTrace(observer, reachDistance, partialTicks);

        Vec3d observerPositionEyes = observer.getEyePosition(partialTicks);

        double distance = reachDistance;

        if (objectMouseOver != null) {
            distance = objectMouseOver.hitVec.distanceTo(observerPositionEyes);
        }

        Vec3d lookVector = observer.getLook(partialTicks);
        Vec3d lookVectorFromEyePosition = observerPositionEyes.add(lookVector.x * reachDistance, lookVector.y * reachDistance, lookVector.z * reachDistance);
        this.pointedEntity = null;
        Vec3d hitVector = null;
        List<Entity> list = this.mc.world.getEntitiesInAABBexcluding(observer, observer.getBoundingBox()
                .expand(lookVector.x * reachDistance, lookVector.y * reachDistance, lookVector.z * reachDistance)
                .expand(1.0D, 1.0D, 1.0D), EntitySelectors.NOT_SPECTATING);
        double d2 = distance;

        for (Entity entity1 : list) {
            AxisAlignedBB axisalignedbb = entity1.getBoundingBox()
                    .grow((double) entity1.getCollisionBorderSize());
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(observerPositionEyes, lookVectorFromEyePosition);

            if (axisalignedbb.contains(observerPositionEyes)) {
                if (d2 >= 0.0D) {
                    this.pointedEntity = entity1;
                    hitVector = raytraceresult == null ? observerPositionEyes : raytraceresult.hitVec;
                    d2 = 0.0D;
                }
            } else if (raytraceresult != null) {
                double d3 = observerPositionEyes.distanceTo(raytraceresult.hitVec);

                if (d3 < d2 || d2 == 0.0D) {
                    if (entity1.getLowestRidingEntity() == observer.getLowestRidingEntity() && !observer
                            .canRiderInteract()) {
                        if (d2 == 0.0D) {
                            this.pointedEntity = entity1;
                            hitVector = raytraceresult.hitVec;
                        }
                    } else {
                        this.pointedEntity = entity1;
                        hitVector = raytraceresult.hitVec;
                        d2 = d3;
                    }
                }
            }
        }

        objectMouseOver = new RayTraceResult(this.pointedEntity, hitVector);

        if (this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof EntityItemFrame) {
            this.mc.pointedEntity = this.pointedEntity;
        }

        return objectMouseOver;
    }

}

package com.robertx22.potion_effects;

import com.robertx22.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class SpellPotionBase extends Potion {

    public abstract void performEffectEverySetTime(EntityLivingBase entityLivingBaseIn,

                                                   int amplifier);

    public abstract String GUID();

    public abstract int performEachXTicks();

    public List<EntityLivingBase> getEntitiesAround(Entity en, float radius) {

        return en.world.getEntitiesWithinAABB(EntityLivingBase.class, en.getBoundingBox()
                .grow(radius));
    }

    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/status_effects/" + GUID() + ".png");
    }

    @Override
    public void performEffect(EntityLivingBase en, int amplifier) {

        if (en.ticksExisted % performEachXTicks() == 0)
            performEffectEverySetTime(en, amplifier);
    }

    protected SpellPotionBase(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        if (!isBadEffectIn) {
            setBeneficial();
        }
    }

    public boolean canSelfCast() {
        return false;
    }

    protected boolean isServerSideOnly() {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplitude) {
        // Controls whether performEffect is called
        // System.out.printf("SpellPotionBase - isReady %d %d\n", duration, amplitude);
        return duration >= 1;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    // Called when the potion is being applied by an
    // AreaEffect or thrown potion bottle
    @Override
    public void affectEntity(Entity applier, Entity caster,
                             @Nonnull EntityLivingBase target, int amplifier,
                             double health) {

        if (target.world.isRemote && isServerSideOnly())
            return;

        doEffect(applier, caster, target, amplifier);
    }

    @Override
    public void applyAttributesModifiersToEntity(EntityLivingBase target,
                                                 @Nonnull AbstractAttributeMap attributes,
                                                 int amplifier) {

        if (!target.world.isRemote || !isServerSideOnly()) {

            onPotionAdd(target, attributes, amplifier);

        }

        // Called on application
        super.applyAttributesModifiersToEntity(target, attributes, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase target,
                                                    @Nonnull AbstractAttributeMap attributes,
                                                    int amplifier) {

        // Called on removal
        super.removeAttributesModifiersFromEntity(target, attributes, amplifier);
    }

    public void onPotionAdd(EntityLivingBase target, AbstractAttributeMap attributes,
                            int amplifier) {
    }

    public void onPotionRemove(EntityLivingBase target, AbstractAttributeMap attributes,
                               int amplifier) {
    }

    public abstract void doEffect(Entity applier, Entity caster, EntityLivingBase target,
                                  int amplifier);

    protected boolean shouldShowParticles() {
        return true;
    }

    protected boolean isAmbient() {
        return false;
    }

    public PotionEffect toPotionEffect(int amplifier) {
        return toPotionEffect(1, amplifier);
    }

    public PotionEffect toPotionEffect(int duration, int amplifier) {
        return new PotionEffect(this, duration, amplifier, isAmbient(), shouldShowParticles());
    }

    @Override
    public boolean equals(Object other) {
        // Only exact class matches work
        return other != null && other.getClass() == getClass();

        // For subclasses, instead use
        // return other != null && other.getClass().isAssignableFrom(getClass());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + getLiquidColor();
        hash = 31 * hash + (isBadEffect() ? 1 : 0);
        return hash;
    }

    @OnlyIn(Dist.CLIENT)
    public void renderInventoryEffect(PotionEffect effect,
                                      net.minecraft.client.gui.Gui gui, int x, int y,
                                      float z) {

        if (gui != null && getIconTexture() != null) {
            Minecraft.getInstance().getTextureManager().bindTexture(getIconTexture());
            Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 16, 16, 16, 16);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void renderHUDEffect(PotionEffect effect, net.minecraft.client.gui.Gui gui,
                                int x, int y, float z, float alpha) {
        if (getIconTexture() != null) {
            Minecraft.getInstance().getTextureManager().bindTexture(getIconTexture());
            Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
        }
    }

}
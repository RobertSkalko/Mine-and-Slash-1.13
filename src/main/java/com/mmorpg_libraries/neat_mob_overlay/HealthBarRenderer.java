package com.mmorpg_libraries.neat_mob_overlay;

import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@EventBusSubscriber
public class HealthBarRenderer {

    static List<EntityLivingBase> renderedEntities = new ArrayList<>();

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if ((!NeatConfig.renderInF1 && !Minecraft.isGuiEnabled()) || !NeatConfig.draw)
            return;

        Entity cameraEntity = mc.getRenderViewEntity();
        BlockPos renderingVector = cameraEntity.getPosition();
        Frustum frustum = new Frustum();

        float partialTicks = event.getPartialTicks();
        double viewX = cameraEntity.lastTickPosX + (cameraEntity.posX - cameraEntity.lastTickPosX) * partialTicks;
        double viewY = cameraEntity.lastTickPosY + (cameraEntity.posY - cameraEntity.lastTickPosY) * partialTicks;
        double viewZ = cameraEntity.lastTickPosZ + (cameraEntity.posZ - cameraEntity.lastTickPosZ) * partialTicks;
        frustum.setPosition(viewX, viewY, viewZ);

        if (NeatConfig.showOnlyFocused) {
            Entity focused = getEntityLookedAt(mc.player);
            if (focused != null && focused instanceof EntityLivingBase && focused.isAlive())
                renderHealthBar((EntityLivingBase) focused, partialTicks, cameraEntity);
        } else {
            WorldClient client = mc.world;
            Set<Entity> entities = ObfuscationReflectionHelper.getPrivateValue(WorldClient.class, client, "entityList");
            for (Entity entity : entities)
                if (entity != null && entity instanceof EntityLivingBase && entity != mc.player && entity
                        .isInRangeToRender3d(renderingVector.getX(), renderingVector.getY(), renderingVector
                                .getZ()) && (entity.ignoreFrustumCheck || frustum.isBoundingBoxInFrustum(entity
                        .getBoundingBox())) && entity.isAlive() && entity.getRecursivePassengers()
                        .isEmpty())
                    renderHealthBar((EntityLivingBase) entity, partialTicks, cameraEntity);
        }
    }

    public static void renderHealthBar(EntityLivingBase passedEntity, float partialTicks,
                                       Entity viewPoint) {
        Stack<EntityLivingBase> ridingStack = new Stack<>();

        EntityLivingBase entity = passedEntity;
        ridingStack.push(entity);

        // MY CODE
        UnitData data = Load.Unit(entity);
        if (data == null) {
            return;
        }

        Unit unit = data.getUnit();
        if (unit == null) {
            return;
        }
        // MY CODE

        while (entity.getRidingEntity() != null && entity.getRidingEntity() instanceof EntityLivingBase) {
            entity = (EntityLivingBase) entity.getRidingEntity();
            ridingStack.push(entity);
        }

        Minecraft mc = Minecraft.getInstance();

        float pastTranslate = 0F;
        while (!ridingStack.isEmpty()) {
            entity = ridingStack.pop();
            boolean boss = !entity.isNonBoss();

            String entityID = entity.getEntityString();
            if (NeatConfig.blacklist.contains(entityID))
                continue;

            processing:
            {
                float distance = passedEntity.getDistance(viewPoint);
                if (distance > NeatConfig.maxDistance || !passedEntity.canEntityBeSeen(viewPoint) || entity
                        .isInvisible())
                    break processing;
                if (!NeatConfig.showOnBosses && !boss)
                    break processing;
                if (!NeatConfig.showOnPlayers && entity instanceof EntityPlayer)
                    break processing;

                double x = passedEntity.lastTickPosX + (passedEntity.posX - passedEntity.lastTickPosX) * partialTicks;
                double y = passedEntity.lastTickPosY + (passedEntity.posY - passedEntity.lastTickPosY) * partialTicks;
                double z = passedEntity.lastTickPosZ + (passedEntity.posZ - passedEntity.lastTickPosZ) * partialTicks;

                float scale = 0.026666672F;
                // MY CODE
                float maxHealth = unit.healthData().Value;
                float health = unit.health().CurrentValue(entity, unit);
                //MY CODE

                if (maxHealth <= 0)
                    break processing;

                float percent = (int) ((health / maxHealth) * 100F);
                RenderManager renderManager = Minecraft.getInstance().getRenderManager();

                GlStateManager.pushMatrix();
                GlStateManager.translatef((float) (x - renderManager.viewerPosX), (float) (y - renderManager.viewerPosY + passedEntity.height + NeatConfig.heightAbove), (float) (z - renderManager.viewerPosZ));
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                GlStateManager.scalef(-scale, -scale, scale);
                boolean lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(false);
                GlStateManager.disableDepthTest();
                GlStateManager.disableTexture2D();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();

                float padding = NeatConfig.backgroundPadding;
                int bgHeight = NeatConfig.backgroundHeight;
                int barHeight = NeatConfig.barHeight;
                float size = NeatConfig.plateSize;

                int r = 0;
                int g = 255;
                int b = 0;

                boolean useHue = !NeatConfig.colorByType;
                if (useHue) {
                    float hue = Math.max(0F, (health / maxHealth) / 3F - 0.07F);
                    Color color = Color.getHSBColor(hue, 1F, 1F);
                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();
                }

                GlStateManager.translatef(0F, pastTranslate, 0F);

                float s = 0.5F;
                ITextComponent name = data.getName(entity);

                String namestring = name.getFormattedText();

                float namel = mc.fontRenderer.getStringWidth(namestring) * s;
                if (namel + 20 > size * 2)
                    size = namel / 2F + 10F;
                float healthSize = size * (health / maxHealth);

                // Background
                if (NeatConfig.drawBackground) {
                    buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    buffer.pos(-size - padding, -bgHeight, 0.0D)
                            .color(0, 0, 0, 64)
                            .endVertex();
                    buffer.pos(-size - padding, barHeight + padding, 0.0D)
                            .color(0, 0, 0, 64)
                            .endVertex();
                    buffer.pos(size + padding, barHeight + padding, 0.0D)
                            .color(0, 0, 0, 64)
                            .endVertex();
                    buffer.pos(size + padding, -bgHeight, 0.0D)
                            .color(0, 0, 0, 64)
                            .endVertex();
                    tessellator.draw();
                }

                // Gray Space
                buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(-size, 0, 0.0D).color(127, 127, 127, 127).endVertex();
                buffer.pos(-size, barHeight, 0.0D).color(127, 127, 127, 127).endVertex();
                buffer.pos(size, barHeight, 0.0D).color(127, 127, 127, 127).endVertex();
                buffer.pos(size, 0, 0.0D).color(127, 127, 127, 127).endVertex();
                tessellator.draw();

                // Health Bar
                buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(-size, 0, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(-size, barHeight, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(healthSize * 2 - size, barHeight, 0.0D)
                        .color(r, g, b, 127)
                        .endVertex();
                buffer.pos(healthSize * 2 - size, 0, 0.0D)
                        .color(r, g, b, 127)
                        .endVertex();
                tessellator.draw();

                GlStateManager.enableTexture2D();

                GlStateManager.pushMatrix();
                GlStateManager.translatef(-size, -4.5F, 0F);
                GlStateManager.scalef(s, s, s);
                mc.fontRenderer.drawString(namestring, 0, 0, 0xFFFFFF);

                GlStateManager.pushMatrix();
                float s1 = 0.75F;
                GlStateManager.scalef(s1, s1, s1);

                int h = NeatConfig.hpTextHeight;
                String maxHpStr = TextFormatting.BOLD + "" + Math.round(maxHealth * 100.0) / 100.0;
                String hpStr = "" + Math.round(health * 100.0) / 100.0;
                String percStr = (int) percent + "%";

                if (maxHpStr.endsWith(".0"))
                    maxHpStr = maxHpStr.substring(0, maxHpStr.length() - 2);
                if (hpStr.endsWith(".0"))
                    hpStr = hpStr.substring(0, hpStr.length() - 2);

                if (NeatConfig.showCurrentHP)
                    mc.fontRenderer.drawString(hpStr, 2, h, 0xFFFFFF);
                if (NeatConfig.showMaxHP)
                    mc.fontRenderer.drawString(maxHpStr, (int) (size / (s * s1) * 2) - 2 - mc.fontRenderer
                            .getStringWidth(maxHpStr), h, 0xFFFFFF);
                if (NeatConfig.showPercentage)
                    mc.fontRenderer.drawString(percStr, (int) (size / (s * s1)) - mc.fontRenderer
                            .getStringWidth(percStr) / 2, h, 0xFFFFFFFF);
                if (NeatConfig.enableDebugInfo && mc.gameSettings.showDebugInfo)
                    mc.fontRenderer.drawString("GEAR_FACTORY_ID: \"" + entityID + "\"", 0, h + 16, 0xFFFFFFFF);
                GlStateManager.popMatrix();

                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

                s1 = 0.5F;
                GlStateManager.scalef(s1, s1, s1);
                GlStateManager.translatef(size / (s * s1) * 2 - 16, 0F, 0F);
                mc.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

                // SHOW ICONS HERE

                // MY CODE
                int off = 0;

                for (StatusEffectData statusdata : unit.statusEffects.values()) {
                    renderIcon(off, 0, new ItemStack(statusdata.GetEffect()
                            .ItemModel()), 16, 16);
                    off -= 16;

                }
                //MY CODE

                GlStateManager.popMatrix();

                GlStateManager.disableBlend();
                GlStateManager.enableDepthTest();
                GlStateManager.depthMask(true);
                if (lighting)
                    GlStateManager.enableLighting();
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();

                pastTranslate -= bgHeight + barHeight + padding;
            }
        }
    }

    private static void renderIcon(int vertexX, int vertexY, ItemStack stack, int intU,
                                   int intV) {
        try {
            Minecraft mc = Minecraft.getInstance();
            IBakedModel iBakedModel = mc.getItemRenderer()
                    .getItemModelMesher()
                    .getItemModel(stack);
            TextureAtlasSprite textureAtlasSprite = mc.getTextureMap()
                    .getAtlasSprite(iBakedModel.getParticleTexture()
                            .getName()
                            .toString());
            mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            buffer.pos((double) (vertexX), (double) (vertexY + intV), 0.0D)
                    .tex((double) textureAtlasSprite.getMinU(), (double) textureAtlasSprite
                            .getMaxV())
                    .endVertex();
            buffer.pos((double) (vertexX + intU), (double) (vertexY + intV), 0.0D)
                    .tex((double) textureAtlasSprite.getMaxU(), (double) textureAtlasSprite
                            .getMaxV())
                    .endVertex();
            buffer.pos((double) (vertexX + intU), (double) (vertexY), 0.0D)
                    .tex((double) textureAtlasSprite.getMaxU(), (double) textureAtlasSprite
                            .getMinV())
                    .endVertex();
            buffer.pos((double) (vertexX), (double) (vertexY), 0.0D)
                    .tex((double) textureAtlasSprite.getMinU(), (double) textureAtlasSprite
                            .getMinV())
                    .endVertex();
            tessellator.draw();
        } catch (Exception e) {
        }
    }

    public static Entity getEntityLookedAt(Entity e) {
        Entity foundEntity = null;

        final double finalDistance = 32;
        double distance = finalDistance;
        RayTraceResult pos = raycast(e, finalDistance);

        Vec3d positionVector = e.getPositionVector();
        if (e instanceof EntityPlayer)
            positionVector = positionVector.add(0, e.getEyeHeight(), 0);

        if (pos != null)
            distance = pos.hitVec.distanceTo(positionVector);

        Vec3d lookVector = e.getLookVec();
        Vec3d reachVector = positionVector.add(lookVector.x * finalDistance, lookVector.y * finalDistance, lookVector.z * finalDistance);

        Entity lookedEntity = null;
        List<Entity> entitiesInBoundingBox = e.getEntityWorld()
                .getEntitiesWithinAABBExcludingEntity(e, e.getBoundingBox()
                        .grow(lookVector.x * finalDistance, lookVector.y * finalDistance, lookVector.z * finalDistance)
                        .expand(1F, 1F, 1F));
        double minDistance = distance;

        for (Entity entity : entitiesInBoundingBox) {
            if (entity.canBeCollidedWith()) {
                float collisionBorderSize = entity.getCollisionBorderSize();
                AxisAlignedBB hitbox = entity.getBoundingBox()
                        .expand(collisionBorderSize, collisionBorderSize, collisionBorderSize);
                RayTraceResult interceptPosition = hitbox.calculateIntercept(positionVector, reachVector);

                if (hitbox.contains(positionVector)) {
                    if (0.0D < minDistance || minDistance == 0.0D) {
                        lookedEntity = entity;
                        minDistance = 0.0D;
                    }
                } else if (interceptPosition != null) {
                    double distanceToEntity = positionVector.distanceTo(interceptPosition.hitVec);

                    if (distanceToEntity < minDistance || minDistance == 0.0D) {
                        lookedEntity = entity;
                        minDistance = distanceToEntity;
                    }
                }
            }

            if (lookedEntity != null && (minDistance < distance || pos == null))
                foundEntity = lookedEntity;
        }

        return foundEntity;
    }

    public static RayTraceResult raycast(Entity e, double len) {
        Vec3d vec = new Vec3d(e.posX, e.posY, e.posZ);
        if (e instanceof EntityPlayer)
            vec = vec.add(new Vec3d(0, e.getEyeHeight(), 0));

        Vec3d look = e.getLookVec();
        if (look == null)
            return null;

        return raycast(e.getEntityWorld(), vec, look, len);
    }

    public static RayTraceResult raycast(World world, Vec3d origin, Vec3d ray,
                                         double len) {
        Vec3d end = origin.add(ray.normalize().scale(len));
        RayTraceResult pos = world.rayTraceBlocks(origin, end);
        return pos;
    }
}

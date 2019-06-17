package com.robertx22;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.items.spells.BaseSpellItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TomeRendererLayer<T extends PlayerEntity> extends LayerRenderer<T, PlayerModel<T>> {
    private final TomeModel model = new TomeModel();

    Minecraft mc;

    public TomeRendererLayer(IEntityRenderer<T, PlayerModel<T>> renderer) {
        super(renderer);
        this.mc = Minecraft.getInstance();
    }

    @Override
    public void render(T entity, float p_212842_2_, float p_212842_3_, float p_212842_4_,
                       float p_212842_5_, float p_212842_6_, float p_212842_7_,
                       float p_212842_8_) {

        ItemStack stack = entity.getHeldItemMainhand();

        Item item = stack.getItem();

        if (item instanceof BaseSpellItem) {

            BaseSpellItem tome = (BaseSpellItem) item;

            float scale = 1F;
            float rotation = 250;

            if (stack.hasTag() == false) {
                stack.setTag(new CompoundNBT());
            }

            float openPercent = stack.getTag().getFloat("openPercent");

            float changeOpenPercent = (float) 1 / (float) item.getUseDuration(stack) / 3.5F;

            Minecraft.getInstance().getTextureManager().bindTexture(tome.texture);
            GlStateManager.pushMatrix();
            GlStateManager.rotatef(rotation, 0.05F, 0.2F, 0);
            GlStateManager.scaled(scale, -scale, -scale);
            GlStateManager.translatef(-1.15F, -0.63F, -0.5F);
            GlStateManager.enableCull();

            if (isTheOneThatIsCurrentlyInUse(stack)) {
                openPercent += changeOpenPercent;
            } else {
                openPercent -= changeOpenPercent;

                if (ifIsNotTheItemBeingHeld(stack)) {
                    GlStateManager.rotatef(310, 0.2F, 0.5F, 0); // rotate it so icon is visible in inv gui
                }
            }

            openPercent = MathHelper.clamp(openPercent, 0, 1);

            stack.getTag().putFloat("openPercent", openPercent);

            float ticks = mc.getRenderPartialTicks() + mc.player.ticksExisted;

            model.render(ticks, 0, 0, openPercent, 0.0F, 0.0625F);
            GlStateManager.popMatrix();

        }

    }

    private boolean isTheOneThatIsCurrentlyInUse(ItemStack stack) {

        return mc.player.getActiveItemStack()
                .getItem() instanceof BaseSpellItem && mc.player.getActiveItemStack()
                .equals(stack);

    }

    private boolean ifIsNotTheItemBeingHeld(ItemStack stack) {

        if (mc.player.getHeldItemMainhand()
                .getItem() instanceof BaseSpellItem && mc.player.getHeldItemMainhand()
                .equals(stack)) {

            return false;
        }
        return true;

    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

}

package com.robertx22.items.spells;

import com.robertx22.TomeRenderer;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public abstract class BaseSpellItem extends Item implements IAutoLocName {

    public abstract String GUID();

    public TextFormatting color = TextFormatting.RED;

    public abstract BaseSpell Spell();

    public ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/tomes/" + this
            .Spell()
            .Element()
            .name()
            .toLowerCase() + ".png");

    public BaseSpellItem() {
        super(getSpellItemProp());

        this.setRegistryName(GUID().toLowerCase());

    }

    public static Item.Properties getSpellItemProp() {

        Properties prop = new Properties().maxStackSize(0).defaultMaxDamage(0);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            prop.setTEISR(TomeRenderer::new);
        });

        return prop;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Spells;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    final public int getUseDuration(ItemStack stack) {
        return Spell().useTimeTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     LivingEntity playerIn) {

        if (playerIn instanceof PlayerEntity) {
            try {
                SpellItemData data = Spell.Load(stack);

                if (Spell().CanCast((PlayerEntity) playerIn, data)) {
                    Spell().cast(worldIn, (PlayerEntity) playerIn, playerIn.getActiveHand(), 5, data);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player,
                                                    Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

}

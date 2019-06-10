package com.robertx22.items.bags.currency_bag;

import com.robertx22.items.bags.BaseBagGui;
import com.robertx22.mmorpg.Ref;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiCurrencyBag extends BaseBagGui<ContainerCurrencyBag> {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/loot_bag.png");

    public GuiCurrencyBag(ContainerCurrencyBag bag, PlayerInventory inv,
                          ITextComponent comp) {
        super(inv, bag);

        this.xSize = BaseBagGui.bagXSize;
        this.ySize = BaseBagGui.bagYSize;
    }

    @Override
    public ResourceLocation texture() {
        return texture;
    }

    @Override
    public int rows() {
        return 6;
    }

    @Override
    public String name() {
        return "Currency Bag";
    }

}
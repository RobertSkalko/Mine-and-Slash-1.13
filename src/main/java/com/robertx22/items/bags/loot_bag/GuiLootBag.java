package com.robertx22.items.bags.loot_bag;

import com.robertx22.items.bags.BaseBagGui;
import com.robertx22.mmorpg.Ref;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiLootBag extends BaseBagGui<ContainerLootBag> {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/loot_bag.png");

    public GuiLootBag(ContainerLootBag bag, PlayerInventory inv, ITextComponent comp) {
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
        return "Loot Bag";
    }

}
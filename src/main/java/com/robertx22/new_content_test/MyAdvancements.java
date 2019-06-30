package com.robertx22.new_content_test;

import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class MyAdvancements implements Consumer<Consumer<Advancement>> {

    public void accept(Consumer<Advancement> consumerAdv) {
        Advancement parent = Advancement.Builder.builder()
                .withDisplay(BlockRegister.BLOCK_MAP_DEVICE, trans("root.title"), trans("root.description"), new ResourceLocation("textures/gui/advancements/backgrounds/end.png"), FrameType.TASK, false, false, false)
                .withCriterion("crafting_table", InventoryChangeTrigger.Instance.forItems(Blocks.CRAFTING_TABLE))
                .register(consumerAdv, id("root"));

        Advancement repair = createStationAdv(BlockRegister.BLOCK_GEAR_REPAIR, "repair", parent, consumerAdv);
        Advancement modify = createStationAdv(BlockRegister.BLOCK_GEAR_MODIFY, "modify", parent, consumerAdv);
        Advancement salvage = createStationAdv(BlockRegister.BLOCK_GEAR_SALVAGE, "salvage", parent, consumerAdv);
        Advancement factory = createStationAdv(BlockRegister.BLOCK_GEAR_FACTORY, "factory", parent, consumerAdv);

    }
    
    private Advancement createStationAdv(Block block, String id, Advancement parent,
                                         Consumer<Advancement> consumerAdv) {
        return Advancement.Builder.builder()
                .withParent(parent)
                .withDisplay(block, trans(id + ".title"), trans(id + ".description"), null, FrameType.TASK, true, true, false)
                .withCriterion("placed_" + id, InventoryChangeTrigger.Instance.forItems(block))
                .register(consumerAdv, id(id));
    }

    private TranslationTextComponent trans(String str) {
        return new TranslationTextComponent("mmorpg.advancements." + str);
    }

    private String id(String str) {
        return Ref.MODID + ":mine_and_slash/" + str;
    }

}


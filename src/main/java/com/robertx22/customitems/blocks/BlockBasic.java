package com.robertx22.customitems.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

    public BlockBasic(Material material, String name) {

	super(Block.Properties.create(material).hardnessAndResistance(2F));

    }

}

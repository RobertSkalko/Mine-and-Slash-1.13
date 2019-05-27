package com.robertx22.world_gen.structures;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

public class SingularPiece extends TemplateStructurePiece {
    private ResourceLocation resource;
    private Rotation rotation;

    public SingularPiece() {
    }

    public SingularPiece(TemplateManager templateManager,
                         ResourceLocation resourceLocation, BlockPos pos,
                         Rotation rotation) {
        super(0);
        this.resource = resourceLocation;
        this.templatePosition = pos;
        this.rotation = rotation;
        this.setupTemplate(templateManager);
    }

    private void setupTemplate(TemplateManager templateManager) {
        Template template = templateManager.getTemplateDefaulted(this.resource);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
                .setMirror(Mirror.NONE)
                .setCenterOffset(this.templatePosition);
        this.setup(template, this.templatePosition, placementsettings);
    }

    /**
     * (abstract) Helper method to write subclass data to NBT
     */
    protected void writeAdditional(NBTTagCompound tagCompound) {
        super.writeAdditional(tagCompound);
        tagCompound.putString("Template", this.resource.toString());
        tagCompound.putString("Rot", this.rotation.name());
    }

    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void readAdditional(NBTTagCompound tagCompound,
                                  TemplateManager templateManager) {
        super.readAdditional(tagCompound, templateManager);
        this.resource = new ResourceLocation(tagCompound.getString("Template"));
        this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
        this.setupTemplate(templateManager);
    }

    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn,
                                    Random rand, MutableBoundingBox sbb) {
        if ("chest".equals(function)) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            TileEntity tileentity = worldIn.getTileEntity(pos.down());
            if (tileentity instanceof TileEntityChest) {
                ((TileEntityChest) tileentity).setLootTable(LootTableList.CHESTS_IGLOO_CHEST, rand
                        .nextLong());
            }

        }
    }
}
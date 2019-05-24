package com.robertx22.deobsfucation;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IglooConfig;
import net.minecraft.world.gen.feature.structure.StructureIO;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class IglooPieces {
    private static final ResourceLocation IGLOO_TOP_RES = new ResourceLocation("igloo/top");
    private static final ResourceLocation IGLOO_MIDDLE_RES = new ResourceLocation("igloo/middle");
    private static final ResourceLocation IGLOO_BOTTOM_RES = new ResourceLocation("igloo/bottom");
    private static final Map<ResourceLocation, BlockPos> map1 = ImmutableMap.of(IGLOO_TOP_RES, new BlockPos(3, 5, 5), IGLOO_MIDDLE_RES, new BlockPos(1, 3, 1), IGLOO_BOTTOM_RES, new BlockPos(3, 6, 7));
    private static final Map<ResourceLocation, BlockPos> map2 = ImmutableMap.of(IGLOO_TOP_RES, new BlockPos(0, 0, 0), IGLOO_MIDDLE_RES, new BlockPos(2, -3, 4), IGLOO_BOTTOM_RES, new BlockPos(0, -3, -2));

    public static void registerPieces() {
        StructureIO.registerStructureComponent(Piece.class, "Iglu");
    }

    public static void addPieces(TemplateManager templateManager, BlockPos pos,
                                 Rotation rotation, List<StructurePiece> pieceList,
                                 Random rand, IglooConfig config) {
        int randInt = rand.nextInt(8) + 3; //4

        pieceList.add(new Piece(templateManager, IGLOO_BOTTOM_RES, pos, rotation, randInt * 3));

        for (int i = 0; i < randInt - 1; ++i) {
            pieceList.add(new Piece(templateManager, IGLOO_MIDDLE_RES, pos, rotation, i * 3));
        }

        pieceList.add(new Piece(templateManager, IGLOO_TOP_RES, pos, rotation, 0));
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resource;
        private Rotation rotation;

        public Piece() {
        }

        public Piece(TemplateManager templateManager, ResourceLocation resourceLocation,
                     BlockPos pos, Rotation rotation, int lowerBy) {
            super(0);
            this.resource = resourceLocation;
            BlockPos blockpos = IglooPieces.map2.get(resourceLocation);
            this.templatePosition = pos.add(blockpos.getX(), blockpos.getY() - lowerBy, blockpos
                    .getZ());
            this.rotation = rotation;
            this.setupTemplate(templateManager);
        }

        private void setupTemplate(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.resource);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(map1.get(this.resource));
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to write subclass dataInstance to NBT
         */
        protected void writeAdditional(NBTTagCompound tagCompound) {
            super.writeAdditional(tagCompound);
            tagCompound.putString("Template", this.resource.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        /**
         * (abstract) Helper method to read subclass dataInstance from NBT
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

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
         * the end, it adds Fences...
         */
        public boolean addComponentParts(IWorld worldIn, Random randomIn,
                                         MutableBoundingBox structureBoundingBoxIn,
                                         ChunkPos chunkPos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(map1.get(this.resource));
            BlockPos blockpos = map2.get(this.resource);
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos
                    .getX(), 0, 0 - blockpos.getZ())));
            int i = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1
                    .getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
            boolean flag = super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn, chunkPos);
            if (this.resource.equals(IGLOO_TOP_RES)) {
                BlockPos blockpos3 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3, 0, 5)));
                IBlockState iblockstate = worldIn.getBlockState(blockpos3.down());
                if (!iblockstate.isAir() && iblockstate.getBlock() != Blocks.LADDER) {
                    worldIn.setBlockState(blockpos3, Blocks.SNOW_BLOCK.getDefaultState(), 3);
                }
            }

            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
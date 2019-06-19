package com.robertx22.world_gen.structures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class TowerPieces {
    private static final ResourceLocation TOP_LOC = new ResourceLocation("igloo/top");
    private static final ResourceLocation MIDDLE_LOC = new ResourceLocation("igloo/middle");
    private static final ResourceLocation BOTTOM_LOC = new ResourceLocation("igloo/bottom");

    public static void init(TemplateManager manager, BlockPos pos, Rotation rotation,
                            List<StructurePiece> pieces, Random ran,
                            NoFeatureConfig config) {

        int middleAmount = ran.nextInt(5);

        pieces.add(new Piece(manager, BOTTOM_LOC, pos, rotation, middleAmount * 3));

        for (int i = 0; i < middleAmount - 1; ++i) {
            pieces.add(new Piece(manager, MIDDLE_LOC, pos, rotation, i * 3));
        }

        pieces.add(new Piece(manager, TOP_LOC, pos, rotation, 0));

    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;

        public Piece(TemplateManager templateManager, ResourceLocation resourceLocation,
                     BlockPos blockPos, Rotation rotation, int heightDifference) {
            super(IStructurePieceType.IGLU, 0);
            this.resourceLocation = resourceLocation;
            BlockPos lvt_6_1_ = (BlockPos) field_207622_e.get(resourceLocation);
            this.templatePosition = blockPos.add(lvt_6_1_.getX(), lvt_6_1_.getY() - heightDifference, lvt_6_1_
                    .getZ());
            this.rotation = rotation;
            this.setupTemplateManager(templateManager);
        }

        public Piece(TemplateManager p_i50566_1_, CompoundNBT p_i50566_2_) {
            super(IStructurePieceType.IGLU, p_i50566_2_);
            this.resourceLocation = new ResourceLocation(p_i50566_2_.getString("Template"));
            this.rotation = Rotation.valueOf(p_i50566_2_.getString("Rot"));
            this.setupTemplateManager(p_i50566_1_);
        }

        private void setupTemplateManager(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementSettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset((BlockPos) field_207621_d.get(this.resourceLocation))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementSettings);
        }

        @Override
        protected void readAdditional(CompoundNBT compoundNBT) {
            super.readAdditional(compoundNBT);
            compoundNBT.putString("Template", this.resourceLocation.toString());
            compoundNBT.putString("Rot", this.rotation.name());
        }

        public boolean addComponentParts(IWorld iworld, Random ran,
                                         MutableBoundingBox boundingbox,
                                         ChunkPos chunkPos) {
            PlacementSettings placeSettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset((BlockPos) field_207621_d.get(this.resourceLocation))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos lvt_6_1_ = (BlockPos) field_207622_e.get(this.resourceLocation);
            BlockPos lvt_7_1_ = this.templatePosition.add(Template.transformedBlockPos(placeSettings, new BlockPos(3 - lvt_6_1_
                    .getX(), 0, 0 - lvt_6_1_.getZ())));
            int lvt_8_1_ = iworld.getHeight(Heightmap.Type.WORLD_SURFACE_WG, lvt_7_1_.getX(), lvt_7_1_
                    .getZ());

            //int y = WorldUtils.getSurface()

            BlockPos templatePosition = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, lvt_8_1_ - 90 - 1, 0);
            boolean addedParts = super.addComponentParts(iworld, ran, boundingbox, chunkPos);

            this.templatePosition = templatePosition;
            return addedParts;
        }

        @Override
        protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld,
                                        Random random,
                                        MutableBoundingBox mutableBoundingBox) {

        }
    }
}

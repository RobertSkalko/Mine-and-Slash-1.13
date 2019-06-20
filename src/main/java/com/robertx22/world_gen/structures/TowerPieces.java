package com.robertx22.world_gen.structures;

import com.robertx22.database.world_providers.IWP;
import com.robertx22.db_lists.WorldProviders;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.registers.common.StructurePieceRegisters;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import com.robertx22.world_gen.processors.BiomeProcessor;
import com.robertx22.world_gen.processors.ChestProcessor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class TowerPieces {
    private static final ResourceLocation TOP_LOC = new ResourceLocation(Ref.MODID, "tower_roof0");
    private static final ResourceLocation MIDDLE_LOC = new ResourceLocation(Ref.MODID, "tower_middle0");
    private static final ResourceLocation BOTTOM_LOC = new ResourceLocation(Ref.MODID, "tower_entrance");
    private static final ResourceLocation FOUNDATION_LOC = new ResourceLocation(Ref.MODID, "tower_foundation");

    static int FOUNDATION_HEIGHT = 3;

    public static int height(TemplateManager manager, ResourceLocation loc) {
        return manager.getTemplateDefaulted(loc).getSize().getY();

    }

    public static void init(TemplateManager manager, BlockPos pos, Rotation rotation,
                            List<StructurePiece> pieces, Random ran, Biome biome) {

        int middleAmount = ran.nextInt(2) + 1;
        int height = 0;

        for (int i = 0; i < 2; i++) {
            pieces.add(new Piece(manager, FOUNDATION_LOC, pos, rotation, height, biome));
            height += height(manager, FOUNDATION_LOC);
        }

        pieces.add(new Piece(manager, BOTTOM_LOC, pos, rotation, height, biome));
        height += height(manager, BOTTOM_LOC);

        for (int i = 0; i < middleAmount; ++i) {
            pieces.add(new Piece(manager, MIDDLE_LOC, pos, rotation, height, biome));
            height += height(manager, MIDDLE_LOC);
        }

        pieces.add(new Piece(manager, TOP_LOC, pos, rotation, height, biome));

    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;
        public int height = 0;
        public IWP iwp;

        public Piece(TemplateManager templateManager, ResourceLocation resourceLocation,
                     BlockPos blockPos, Rotation rotation, int height, Biome biome) {
            super(StructurePieceRegisters.TOWER, 0);
            this.resourceLocation = resourceLocation;
            this.height = height;
            BlockPos pos = new BlockPos(0, 0, 0);
            this.templatePosition = blockPos.add(pos.getX(), pos.getY() + height, pos.getZ());
            this.rotation = rotation;
            this.iwp = WorldProviders.byBiome(biome);
            this.setupTemplateManager(templateManager, iwp);
        }

        public Piece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(StructurePieceRegisters.TOWER, compoundNBT);
            this.resourceLocation = new ResourceLocation(compoundNBT.getString("Template"));
            this.rotation = Rotation.valueOf(compoundNBT.getString("Rot"));
            this.height = compoundNBT.getInt("num");
            this.iwp = WorldProviders.All.get(compoundNBT.getString("iwp"));

            this.setupTemplateManager(templateManager, iwp);

        }

        private void setupTemplateManager(TemplateManager templateManager, IWP iwp) {
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementSettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(new BlockPos(0, height, 0))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                    .addProcessor(new ChestProcessor(30))
                    .addProcessor(new BiomeProcessor(iwp));

            this.setup(template, this.templatePosition, placementSettings);
        }

        @Override
        protected void readAdditional(CompoundNBT compoundNBT) {
            super.readAdditional(compoundNBT);
            compoundNBT.putString("Template", this.resourceLocation.toString());
            compoundNBT.putString("Rot", this.rotation.name());
            compoundNBT.putInt("num", this.height);
            compoundNBT.putString("iwp", this.iwp.GUID());

        }

        @Override
        public boolean addComponentParts(IWorld iworld, Random ran,
                                         MutableBoundingBox boundingbox,
                                         ChunkPos chunkPos) {

            IWP iwp = WorldUtils.getIWP(iworld.getWorld());

            if (iwp != null) {

                PlacementSettings placeSettings = (new PlacementSettings()).setRotation(this.rotation)
                        .setMirror(Mirror.NONE)
                        .setCenterOffset(new BlockPos(0, height, 0))
                        .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                        .addProcessor(new ChestProcessor(30))
                        .addProcessor(new BiomeProcessor(iwp));

                BlockPos pos = this.templatePosition.add(Template.transformedBlockPos(placeSettings, new BlockPos(0, 0, 0)));

                int y = WorldUtils.getSurface(iworld, pos).getY();

                BlockPos templatePosition = this.templatePosition;
                this.templatePosition = this.templatePosition.add(0, y - 90 - FOUNDATION_HEIGHT * 2, 0);

                boolean addedParts = super.addComponentParts(iworld, ran, boundingbox, chunkPos);

                this.templatePosition = templatePosition;
                return addedParts;
            }

            return false;
        }

        @Override
        protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld,
                                        Random random,
                                        MutableBoundingBox mutableBoundingBox) {

        }
    }
}

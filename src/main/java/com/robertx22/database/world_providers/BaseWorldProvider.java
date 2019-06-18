package com.robertx22.database.world_providers;

import com.robertx22.mmorpg.Ref;
import com.robertx22.world_gen.types.FeatureType;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ModDimension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static com.robertx22.db_lists.Templates.*;

public abstract class BaseWorldProvider extends Dimension implements IWP {

    public ModDimension moddim;
    private DimensionType type;

    @Override
    public List<FeatureType> smallSurfaceDecorations() {
        return Arrays.asList(bigWoodPillar, smallWoodPillar);
    }

    @Override
    public List<FeatureType> smallTreasures() {
        return Arrays.asList(smallTreasure0);
    }

    public ResourceLocation res(String str) {

        return new ResourceLocation(Ref.MODID, str);

    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".world_type." + formattedGUID();
    }

    @Override
    public void setModDimension(ModDimension mod) {
        this.moddim = mod;
    }

    public abstract BiFunction<World, DimensionType, ? extends Dimension> classFactory();

    @Override
    public float getBonusLootMulti() {
        return 1;
    }

    @Override
    public ModDimension newModDimension() {
        return new ModDimension() {
            @Override
            public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
                return classFactory();
            }
        };
    }

    @Override
    public boolean hasSkyLight() {
        return true;
    }

    @Nullable
    public BlockPos findSpawn(ChunkPos p_206920_1_, boolean checkValid) {
        for (int i = p_206920_1_.getXStart(); i <= p_206920_1_.getXEnd(); ++i) {
            for (int j = p_206920_1_.getZStart(); j <= p_206920_1_.getZEnd(); ++j) {
                BlockPos blockpos = this.findSpawn(i, j, checkValid);
                if (blockpos != null) {
                    return blockpos;
                }
            }
        }

        return null;
    }

    @Nullable
    public BlockPos findSpawn(int p_206921_1_, int p_206921_2_, boolean checkValid) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_206921_1_, 0, p_206921_2_);
        Biome biome = this.world.getBiome(blockpos$mutableblockpos);
        BlockState iblockstate = biome.getSurfaceBuilderConfig().getTop();
        if (checkValid && !iblockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            Chunk chunk = this.world.getChunk(p_206921_1_ >> 4, p_206921_2_ >> 4);
            int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, p_206921_1_ & 15, p_206921_2_ & 15);
            if (i < 0) {
                return null;
            } else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, p_206921_1_ & 15, p_206921_2_ & 15) > chunk
                    .getTopBlockY(Heightmap.Type.OCEAN_FLOOR, p_206921_1_ & 15, p_206921_2_ & 15)) {
                return null;
            } else {
                for (int j = i + 1; j >= 0; --j) {
                    blockpos$mutableblockpos.setPos(p_206921_1_, j, p_206921_2_);
                    BlockState iblockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
                    if (!iblockstate1.getFluidState().isEmpty()) {
                        break;
                    }

                    if (iblockstate1.equals(iblockstate)) {
                        return blockpos$mutableblockpos.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    public static final String RESETTABLE = "resettable";

    @Override
    public ResourceLocation getResourceLoc() {
        return new ResourceLocation(Ref.MODID, RESETTABLE + "_" + this.GUID());
    }

    public BaseWorldProvider(World world, DimensionType type) {
        super(world, type);
        this.type = type;
        this.setModDim();

    }

    @Override
    public ModDimension getModDim() {
        return moddim;
    }

    public void setModDim() {
        this.moddim = this.newModDimension();
    }

    BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeType = BiomeProviderType.FIXED;
    ChunkGeneratorType chunkType = ChunkGeneratorType.SURFACE;

    @Nonnull
    @Override
    public ChunkGenerator<?> createChunkGenerator() {

        OverworldGenSettings settings = (OverworldGenSettings) chunkType.createSettings();

        return chunkType.create(this.world, biomeType.create(biomeType.createSettings()
                .setBiome(this.getBiome())), settings);
    }

    @Override
    public int Weight() {
        return UncommonWeight;

    }

    /**
     * Do not override this.
     * <p>
     * Returns true on clients (to allow rendering of sky etc, maybe even clouds).
     * Returns false on servers (to disable Nether Portal mob spawning and sleeping
     * in beds).
     */
    @Override
    public boolean isSurfaceWorld() {
        return (this.world != null) && this.world.isRemote;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {

        double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float) (d0 * 2.0D + d1) / 3.0F;
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {

        float f = MathHelper.cos(p_76562_1_ * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.7529412F;
        float f2 = 0.84705883F;
        float f3 = 1.0F;
        f1 = f1 * (f * 0.94F + 0.06F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.09F);
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public DimensionType getType() {
        return type;
    }

}
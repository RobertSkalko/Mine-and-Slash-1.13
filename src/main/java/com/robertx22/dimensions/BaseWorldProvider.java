package com.robertx22.dimensions;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.DebugGenSettings;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ModDimension;

import javax.annotation.Nullable;

public abstract class BaseWorldProvider extends Dimension implements IWP {

    public ModDimension moddim;
    private DimensionType type;
    public BlockPos spawn = new BlockPos(111, 111, 111);

    public void onInit(BlockPos pos, DimensionType type) {
        this.spawn = pos;
        this.type = type;
    }

    public BaseWorldProvider() {
        super();
        this.setModDim();
    }

    public BaseWorldProvider(DimensionType type) {
        this.type = type;
        this.setModDim();
    }

    @Override
    public ModDimension getModDim() {
        return moddim;
    }

    protected abstract ModDimension newModDimension();

    public void setModDim() {
        this.moddim = this.newModDimension();
    }

    BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;

    @Override
    public IChunkGenerator<? extends IChunkGenSettings> createChunkGenerator() {

        SingleBiomeProviderSettings setting = biomeprovidertype.createSettings()
                .setBiome(getBiome());

        return ChunkGeneratorType.DEBUG.create(this.world, biomeprovidertype.create(setting), new DebugGenSettings());

    }

    @Override
    public int Weight() {
        return UncommonWeight;

    }

    @Override
    public boolean canDropChunk(int x, int z) {
        return true;
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
        return (this.world == null) ? false : this.world.isRemote;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    protected void init() {
        hasSkyLight = true;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos p_206920_1_, boolean checkValid) {
        return spawn;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int p_206921_1_, int p_206921_2_, boolean checkValid) {
        return spawn;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        int i = (int) (worldTime % 24000L);
        float f = ((float) i + partialTicks) / 24000.0F - 0.25F;
        if (f < 0.0F) {
            ++f;
        }

        if (f > 1.0F) {
            --f;
        }

        float f1 = 1.0F - (float) ((Math.cos((double) f * Math.PI) + 1.0D) / 2.0D);
        f = f + (f1 - f) / 3.0F;
        return f;
    }

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
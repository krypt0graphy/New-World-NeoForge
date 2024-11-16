package com.kryptography.newworld.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LoamSnowFeature extends Feature<NoneFeatureConfiguration> {
    public LoamSnowFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        BlockPos pos = pContext.origin();
        WorldGenLevel level = pContext.level();
        if (!level.getBlockState(pos.above()).isAir() && !level.getBlockState(pos.above(2)).isAir()) {
            return false;
        }

        RandomSource random = pContext.random();
        int range = random.nextIntBetweenInclusive(3, 6);
        float f = (range * 3) * 0.333f + 0.5f;
        int blocksPlaced = 0;
        BlockState state = random.nextFloat() >= 0.8 ? Blocks.POWDER_SNOW.defaultBlockState() : Blocks.SNOW_BLOCK.defaultBlockState();

        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-range, -range, -range), pos.offset(range, range, range))) {
            if (blockPos.distToCenterSqr(pos.getX(), pos.getY(), pos.getZ()) <= (f * f) && shouldPlaceSnow(random, pos, level)) {
                level.setBlock(blockPos, state, 2);
                blocksPlaced++;
            }
        }
        return blocksPlaced > 0;
    }

    private boolean shouldPlaceSnow(RandomSource random, BlockPos pos, WorldGenLevel level) {
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-2, -2, -2) , pos.offset(2, 2, 2))) {
            if (level.getBlockState(blockPos).getFluidState().is(FluidTags.LAVA)) {
                return false;
            }
        }

        return random.nextInt(9) > 7
                && pos.getY() < level.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ())
                && pos.getY() > level.getMinBuildHeight()
                && level.getBlockState(pos).is(Blocks.CALCITE);
    }
}

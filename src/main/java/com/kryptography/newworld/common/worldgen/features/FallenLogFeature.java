package com.kryptography.newworld.common.worldgen.features;

import com.kryptography.newworld.init.NWBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FallenLogFeature extends Feature<NoneFeatureConfiguration>{


    public FallenLogFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(context.random());
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();

        int logLength = 2 + context.random().nextInt(2, 4);
        if(level.canSeeSky(pos) || level.getBlockState(pos.below()).isSolid()) {
            if (!level.getBlockState(pos.below()).is(Blocks.WATER) && !level.getBlockState(pos.below()).is(Blocks.LAVA)) {
                for (int i = 0; i < logLength; i++) {
                        this.setBlock(level, pos.relative(direction, i), NWBlocks.FIR_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
                }
                return true;
            }
        }
        return false;
    }
}

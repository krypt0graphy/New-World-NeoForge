package com.kryptography.newworld.common.worldgen.features;

import com.kryptography.newworld.init.NWBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
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
        if(level.canSeeSky(pos) || level.getBlockState(pos.below()).isSolid() && !level.getBlockState(pos.below()).is(Blocks.WATER) && !level.getBlockState(pos.below()).is(Blocks.LAVA)) {
                for (int i = 0; i < logLength; i++) {
                    placeLog(level, pos, direction, i);

                }
                return true;
        }
        return false;
    }

    public void placeLog(WorldGenLevel level, BlockPos pos, Direction direction, int currentLoop) {
        BlockPos currentPos = pos.relative(direction, currentLoop);
        if(level.getBlockState(currentPos.below()).isSolid() && level.getBlockState(currentPos).is(BlockTags.REPLACEABLE)) {
            level.setBlock(currentPos, NWBlocks.FIR_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 64);
        } else {
            boolean foundValid = false;
            for (int j = -6; j < 3; j++) {
                if (level.getBlockState(currentPos.below(j)).isSolid() && level.getBlockState(currentPos.below(j - 1)).is(BlockTags.REPLACEABLE) && !foundValid) {
                    level.setBlock(currentPos.below(j - 1), NWBlocks.FIR_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 64);
                    foundValid = true;
                }
            }
        }
    }
}
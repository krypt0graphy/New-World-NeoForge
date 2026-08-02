package com.kryptography.newworld.common.worldgen.features;

import com.kryptography.newworld.core.registry.NWBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FallenLogFeature extends Feature<NoneFeatureConfiguration> {

	private static final int MIN_LOG_LENGTH = 5;
	private static final int MAX_LOG_LENGTH = 9;
	private static final int MAX_GROUND_GAP = 2;
	private static final float MUSHROOM_CHANCE = 0.1F;

	public FallenLogFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin();
		RandomSource random = context.random();

		if (!isValidGround(level, origin)) {
			return false;
		}

		level.setBlock(origin, NWBlocks.FIR_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y), 2);

		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
		int gap = 2 + random.nextInt(2);
		BlockPos.MutableBlockPos pos = origin.relative(direction, gap).mutable();

		setGroundHeightForFallenLogStartPos(level, pos);

		int length = MIN_LOG_LENGTH + random.nextInt(MAX_LOG_LENGTH - MIN_LOG_LENGTH + 1);

		if (canPlaceEntireFallenLog(level, pos, direction, length)) {
			placeFallenLog(level, random, pos, direction, length);
		}

		return true;
	}

	private void setGroundHeightForFallenLogStartPos(WorldGenLevel level, BlockPos.MutableBlockPos pos) {
		pos.move(Direction.UP, 1);

		for (int i = 0; i < 6; i++) {
			BlockState below = level.getBlockState(pos.below());
			BlockState at = level.getBlockState(pos);
			if (below.isSolid() && at.is(BlockTags.REPLACEABLE)) {
				return;
			}
			pos.move(Direction.DOWN);
		}
	}

	private boolean canPlaceEntireFallenLog(WorldGenLevel level, BlockPos.MutableBlockPos pos, Direction direction, int length) {
		BlockPos.MutableBlockPos checkPos = pos.mutable();
		int consecutiveGaps = 0;

		for (int i = 0; i < length; i++) {
			BlockState at = level.getBlockState(checkPos);
			if (!at.is(BlockTags.REPLACEABLE)) {
				return false;
			}

			BlockState below = level.getBlockState(checkPos.below());
			if (!below.isSolid() || below.is(Blocks.WATER) || below.is(Blocks.LAVA)) {
				if (++consecutiveGaps > MAX_GROUND_GAP) {
					return false;
				}
			} else {
				consecutiveGaps = 0;
			}

			checkPos.move(direction);
		}

		return true;
	}

	private void placeFallenLog(WorldGenLevel level, RandomSource random, BlockPos.MutableBlockPos pos, Direction direction, int length) {
		BlockState logState = NWBlocks.FIR_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis());

		for (int i = 0; i < length; i++) {
			level.setBlock(pos, logState, 2);

			if (random.nextFloat() < MUSHROOM_CHANCE) {
				BlockPos abovePos = pos.above();
				if (level.getBlockState(abovePos).is(BlockTags.REPLACEABLE)) {
					level.setBlock(abovePos, Blocks.RED_MUSHROOM.defaultBlockState(), 2);
				}
			}

			pos.move(direction);
		}
	}

	private boolean isValidGround(WorldGenLevel level, BlockPos pos) {
		BlockState below = level.getBlockState(pos.below());
		BlockState at = level.getBlockState(pos);
		return level.canSeeSky(pos) && below.isSolid() && !below.is(Blocks.WATER) && !below.is(Blocks.LAVA) && at.is(BlockTags.REPLACEABLE);
	}
}
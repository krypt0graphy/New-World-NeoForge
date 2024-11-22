package com.kryptography.newworld.common.worldgen.features;

import com.kryptography.newworld.common.blocks.TombstoneBlock;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BurialSiteFeature extends Feature<NoneFeatureConfiguration> {
    public BurialSiteFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldGenLevel = pContext.level();
        BlockPos origin = pContext.origin();
        RandomSource random = pContext.random();

        int width = random.nextIntBetweenInclusive(5,10);

        BlockState bricks = Blocks.DEEPSLATE_BRICKS.defaultBlockState();
        BlockState crackedBricks = Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState();
        BlockState tiles = Blocks.DEEPSLATE_TILES.defaultBlockState();
        BlockState crackedTiles = Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState();

        List<BlockPos> towerPositions = new ArrayList<>();

        for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-width, 1, -width), origin.offset(width, 5, width))) {
            if (worldGenLevel.getBlockState(pos).isSolidRender(worldGenLevel, pos)) {
                return false;
            }
        }

        for (BlockPos pos :  BlockPos.betweenClosed(origin.offset(-width, 0, -width), origin.offset(width, 0, width))){
            if (pos.getX() == origin.getX() || pos.getZ() == origin.getZ()){
                placeBlock(worldGenLevel, pos, bricks, crackedBricks);
            }
            else{
                placeBlock(worldGenLevel, pos, tiles, crackedTiles);
            }
        }

        for (int i = -width; i <= width; i += 3) {
            towerPositions.add(origin.offset(-width, 0, -i));
            towerPositions.add(origin.offset(width, 0, i));
            towerPositions.add(origin.offset(i, 0, -width));
            towerPositions.add(origin.offset(-i, 0, width));

            if (Math.abs(i) < width){
                for (int k = -width; k < width; k += 3) {
                    BlockPos currentPos = origin.offset(-i, 1, -k);
                    if (worldGenLevel.getRandom().nextFloat() < 0.3F){
                        placeBlock(worldGenLevel, currentPos, NWBlocks.TOMBSTONE.get().defaultBlockState().setValue(TombstoneBlock.FACING, Direction.from2DDataValue(worldGenLevel.getRandom().nextIntBetweenInclusive(2, 5))));
                        worldGenLevel.getBlockEntity(currentPos, NWBlockEntityTypes.TOMBSTONE.get()).ifPresent((blockEntity) ->
                                blockEntity.setLootTable(BuiltInLootTables.UNDERWATER_RUIN_SMALL, currentPos.asLong()));
                    }
                    if (worldGenLevel.getRandom().nextFloat() > 0.6){
                        worldGenLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(CaveFeatures.SCULK_PATCH_DEEP_DARK).ifPresent((feature) -> {
                            feature.value().place(worldGenLevel, pContext.chunkGenerator(), random, currentPos);
                        });
                    }
                }
            }
        }
        placeBlock(worldGenLevel, origin.above(), NWBlocks.TOMBSTONE.get().defaultBlockState());

        towerPositions.forEach(currentPos -> {
            if (random.nextFloat() < 0.4){
                int length = worldGenLevel.getRandom().nextIntBetweenInclusive(4, 7);

                for (BlockPos pos : BlockPos.betweenClosed(currentPos.above(), currentPos.above(length))){
                    placeBlock(worldGenLevel, pos, Blocks.POLISHED_DEEPSLATE_WALL.defaultBlockState());
                }
                placeBlock(worldGenLevel, currentPos.above(length+1), Blocks.REDSTONE_LAMP.defaultBlockState());
                placeBlock(worldGenLevel, currentPos.above(length+2), Blocks.SCULK_SENSOR.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true));

                Direction.stream().filter(direction -> direction.getAxis() != Direction.Axis.Y).forEach(direction -> {
                    placeBlock(worldGenLevel, currentPos.above(length+2).offset(direction.getNormal()), Blocks.DEEPSLATE_BRICKS.defaultBlockState());
                    placeBlock(worldGenLevel, currentPos.above(length+1).offset(direction.getNormal()), Blocks.DEEPSLATE_BRICK_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP));
                });

                
            }
        });

        return true;
    }



    private static void placeBlock(WorldGenLevel level, BlockPos pos, BlockState... states){
        if (level.getBlockState(pos).isAir()){
            level.setBlock(pos, Arrays.stream(states).toList().get(level.getRandom().nextInt(Arrays.stream(states).toList().size())), 3);
        }
    }
}
